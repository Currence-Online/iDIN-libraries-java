package net.bankid.merchant.library;

import mockit.Mocked;
import mockit.NonStrictExpectations;
import net.bankid.merchant.library.tests.TestMessenger;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.w3c.dom.Document;

import java.io.InputStream;
import java.net.URL;
import java.util.regex.Pattern;

public abstract class TestBaseClass {
	protected TestMessenger messenger;
	protected InputStream keystore;
	protected String dateTimeRegexp = "[\\d]{4}-[\\d]{2}-[\\d]{2}T[\\d]{2}:[\\d]{2}:[\\d]{2}.[\\d]{3}Z";
	protected URL merchantReturnUrl;
	@Mocked public Configuration config;

	protected static final String NameBVN = "http://www.betaalvereniging.nl/iDx/messages/Merchant-Acquirer/1.0.0";

	protected TestBaseClass() throws Exception {
		this.merchantReturnUrl = new URL("http://localhost");
	}

	protected void setupConfiguration() throws Exception {
		URL url = ClassLoader.getSystemClassLoader().getResource("bankid.library.jks");
		if (url == null) {
			url = Configuration.class.getClassLoader().getResource("bankid.library.jks");
		}
		keystore = url.openStream();
		keystore.mark(Integer.MAX_VALUE);

		new NonStrictExpectations() {{
			config.getMerchantID(); result = "1234567890";
			config.getMerchantReturnUrl(); result = "http://localhost";
			config.getAcquirerDirectoryURL(); result = "http://example.com/directory";
			config.getAcquirerTransactionURL(); result = "http://example.com/transaction";
			config.getAcquirerStatusURL(); result = "http://example.com/status";
			config.getAcquirerCertificateAlias(); result = "bankid.server.test";
			config.getMerchantCertificateAlias(); result = "bankid.library";
			config.getMerchantCertificatePassword(); result = "123456";
			config.getKeyStoreLocation(); result = "bankid.library.jks";
			config.getKeyStorePassword(); result = "123456";
			config.getKeyStore(); result = keystore;
			config.getMessenger(); result = messenger;
		}};

		Configuration.defaultInstance().Setup(config);
	}

	public static class RegexpMatcher extends BaseMatcher<String> {
		Pattern p;
		String s;
		public RegexpMatcher(String regexp) {
			p = Pattern.compile(regexp);
		}
		@Override
		public boolean matches(Object o) {
			s = (String) o;
			return p.matcher(s).matches();
		}
		@Override
		public void describeTo(Description d) {
			d.appendText("'" + p.pattern() + "'");
			//d.appendDescriptionOf(this);
		}
	}

	protected String getTextContent(Document doc, String elementNS, String elementName) {
		return doc.getDocumentElement().getElementsByTagNameNS(elementNS, elementName).item(0).getTextContent();
	}

	public static RegexpMatcher matchesRegexp(String regexp) {
		return new RegexpMatcher(regexp);
	}
}
