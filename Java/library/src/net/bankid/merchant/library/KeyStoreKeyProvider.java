package net.bankid.merchant.library;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class KeyStoreKeyProvider implements IKeyProvider {
    private Configuration config;
    private ILogger logger;

    public KeyStoreKeyProvider(Configuration config) {
        this.config = config;
        this.logger = config.getLoggerFactory().create();
    }

    @Override
    public ISigningKeyPair getMerchantSigningKeyPair() throws CertificateException, UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException, IOException {
        KeyStore ks = getKeyStore();
        KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry)
                ks.getEntry(config.getMerchantCertificateAlias(),
                        new KeyStore.PasswordProtection(config.getMerchantCertificatePassword().toCharArray()));
        logger.Log(config, "found key entry");
        return new ISigningKeyPair(keyEntry.getPrivateKey(), (X509Certificate) keyEntry.getCertificate());
    }

    @Override
    public X509Certificate getAcquirerCertificate(String acquirerCertificateAlias) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        KeyStore ks = getKeyStore();
        return (X509Certificate) ks.getCertificate(acquirerCertificateAlias);
    }

    private KeyStore getKeyStore() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream is = config.getKeyStore();
        is.reset();
        ks.load(is, config.getKeyStorePassword().toCharArray());
        logger.Log(config, "loaded key store");
        return ks;
    }
}
