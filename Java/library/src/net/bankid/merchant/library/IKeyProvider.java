package net.bankid.merchant.library;

import org.w3c.dom.Document;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Interface to obtain merchant and acquirer keys for cryptographic operations
 */
public interface IKeyProvider {
    /**
     * Method to retrieve the merchant {@link SigningKeyPair} to use for signing or decryption
     * @return The merchant {@link SigningKeyPair}
     * @throws CertificateException
     * @throws UnrecoverableEntryException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws IOException
     */
    SigningKeyPair getMerchantSigningKeyPair() throws CertificateException, UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException, IOException;

    /**
     * Method to retrieve the acquirer {@link X509Certificate} to use for signature validation
     * @param acquirerCertificateAlias Alias for the certificate, should match either
     * {@link Configuration#getAcquirerCertificateAlias()} or
     * {@link Configuration#getAcquirerAlternateCertificateAlias()}
     * @return The requested certificate
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws IOException
     */
    X509Certificate getAcquirerCertificate(String acquirerCertificateAlias) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException;

    /**
     * Method to initialize this IKeyProvider from the configuration file.
     * This method is invoked from {@link Configuration#Load(InputStream)}.
     * @param doc The configuration file
     * @throws IOException
     */
    void Load(Document doc) throws IOException;

    /**
     * Method to initialize this IKeyProvider from the given IKeyProvider.
     * The method is invoked from {@link Configuration#Setup(Configuration)}, which will ensure that the value passed
     * in the values parameter is of the same instance.
     * @param values The given IKeyProvider
     * @throws IOException
     */
    void Setup(IKeyProvider values) throws IOException;
}
