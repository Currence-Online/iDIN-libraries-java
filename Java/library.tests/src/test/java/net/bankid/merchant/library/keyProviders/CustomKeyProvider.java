package net.bankid.merchant.library.keyProviders;

import net.bankid.merchant.library.Configuration;
import net.bankid.merchant.library.IKeyProvider;
import net.bankid.merchant.library.SigningKeyPair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class CustomKeyProvider implements IKeyProvider {

	private InputStream merchantKeyStore;
	private String merchantAlias;
	private String merchantPassword;

	private InputStream acquirerKeyStore;
	private String acquirerKeyPassword;

	CustomKeyProvider() {}

	@Override
	public SigningKeyPair getMerchantSigningKeyPair() throws CertificateException, UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException, IOException {
		KeyStore ks = getJavaKeystore(merchantKeyStore, merchantPassword);
		KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(merchantAlias, new KeyStore.PasswordProtection(merchantPassword.toCharArray()));
		return new SigningKeyPair(keyEntry.getPrivateKey(), (X509Certificate) keyEntry.getCertificate());
	}

	@Override
	public X509Certificate getAcquirerCertificate(String acquirerCertificateAlias) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
		KeyStore ks = getJavaKeystore(acquirerKeyStore, acquirerKeyPassword);
		return (X509Certificate) ks.getCertificate(acquirerCertificateAlias);
	}

	@Override
	public void Load(Document doc) throws IOException {
		String merchantKeyLocation = getConfigValue(doc, "BankId.CustomKeyProvider.Merchant.Key.Location");
		this.merchantPassword = getConfigValue(doc, "BankId.CustomKeyProvider.Merchant.Key.Password");
		this.merchantAlias = getConfigValue(doc, "BankId.CustomKeyProvider.Merchant.Key.Alias");
		this.merchantKeyStore = openKeyStoreInputStream(merchantKeyLocation);

		String acquirerKeyLocation = getConfigValue(doc, "BankId.CustomKeyProvider.Acquirer.Key.Location");
		this.acquirerKeyPassword = getConfigValue(doc, "BankId.CustomKeyProvider.Acquirer.Key.Password");
		this.acquirerKeyStore = openKeyStoreInputStream(acquirerKeyLocation);
	}

	@Override
	public void Setup(IKeyProvider values) throws IOException {
	}

	private InputStream openKeyStoreInputStream(String location) throws IOException {
		URL url = ClassLoader.getSystemClassLoader()
							 .getResource(location);
		if(url == null) {
			url = Configuration.class.getClassLoader()
									 .getResource(location);
		}
		InputStream is = url.openStream();
		is.mark(Integer.MAX_VALUE);
		return is;
	}

	private KeyStore getJavaKeystore(InputStream is, String password) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		is.reset();
		ks.load(is, password.toCharArray());
		return ks;
	}

	private String getConfigValue(Document doc, String key) {
		NodeList nl = doc.getElementsByTagName("add");
		for(int i = 0; i < nl.getLength(); i++) {
			Element e = (Element) nl.item(i);
			if(e.getAttribute("key")
				.equalsIgnoreCase(key)) {
				return e.getAttribute("value");
			}
		}
		return null;
	}
}
