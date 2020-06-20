package net.bankid.merchant.library;

import org.w3c.dom.Document;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public interface IKeyProvider {
    public ISigningKeyPair getMerchantSigningKeyPair() throws CertificateException, UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException, IOException;
    public X509Certificate getAcquirerCertificate(String acquirerCertificateAlias) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException;

    public void Load(Document doc) throws IOException;
    public void Setup(IKeyProvider values) throws IOException;
}
