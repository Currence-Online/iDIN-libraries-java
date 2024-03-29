package net.bankid.merchant.library;

import org.w3c.dom.Document;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Provide merchant and acquirer keys from a {@link KeyStore}.
 * This is the default {@link IKeyProvider} implementation, it reads the keystore details from the {@link Configuration}
 * object. After construction, this object MUST be activated by calling either {@link #Load(Document)} or
 * {@link #Setup(IKeyProvider)}, these are invoked by respectively {@link Configuration#Load(InputStream)} and
 * {@link Configuration#Setup(Configuration)}.
 */
public class KeyStoreKeyProvider implements IKeyProvider {
    /**
     * Lazily initialized by either {@link #Load(Document)} or {@link #Setup(IKeyProvider)}
     */
    private InputStream keyStore;
    /**
     * The configuration object.
     */
    private final Configuration config;
    /**
     * Lazily initialized by {@link #getLogger()}
     */
    private ILogger logger;

    public KeyStoreKeyProvider(Configuration config) {
        this.config = config;
    }

    @Override
    public SigningKeyPair getMerchantSigningKeyPair() throws CertificateException, UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException, IOException {
        KeyStore ks = getJavaKeyStore();
        KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry)
                ks.getEntry(config.getMerchantCertificateAlias(),
                        new KeyStore.PasswordProtection(config.getMerchantCertificatePassword().toCharArray()));
        getLogger().Log(config, "found key entry");
        return new SigningKeyPair(keyEntry.getPrivateKey(), (X509Certificate) keyEntry.getCertificate());
    }

    @Override
    public X509Certificate getAcquirerCertificate(String acquirerCertificateAlias) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        KeyStore ks = getJavaKeyStore();
        return (X509Certificate) ks.getCertificate(acquirerCertificateAlias);
    }

    @Override
    public void Load(Document doc) throws IOException {
        openKeyStoreInputStream();
    }

    @Override
    public void Setup(IKeyProvider values) throws IOException {
        openKeyStoreInputStream();
    }

    /**
     * @return the keyStore
     */
    InputStream getKeyStore() {
        return keyStore;
    }

    private void openKeyStoreInputStream() throws IOException {
        URL url = ClassLoader.getSystemClassLoader().getResource(config.getKeyStoreLocation());
        if (url == null) {
            url = Configuration.class.getClassLoader().getResource(config.getKeyStoreLocation());
        }
        keyStore = url.openStream();
        getKeyStore().mark(Integer.MAX_VALUE);
    }

    private KeyStore getJavaKeyStore() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        if (keyStore == null) {
            keyStore = config.getKeyStore();
        }
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream is = getKeyStore();
        is.reset();
        ks.load(is, config.getKeyStorePassword().toCharArray());
        getLogger().Log(config, "loaded key store");
        return ks;
    }

    private ILogger getLogger() {
        if (logger == null) {
            logger = config.getLoggerFactory().create();
        }
        return logger;
    }
}
