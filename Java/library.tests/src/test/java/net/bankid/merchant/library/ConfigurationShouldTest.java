package net.bankid.merchant.library;

import net.bankid.merchant.library.keyProviders.CustomKeyProvider;
import net.bankid.merchant.library.keyProviders.CustomKeyProviderFactory;
import net.bankid.merchant.library.tests.utils.resources.ResourcesUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static net.bankid.merchant.library.tests.constants.Configs.*;
import static net.bankid.merchant.library.tests.utils.resources.ResourcesUtils.getResourceStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ConfigurationShouldTest {
	private Configuration config;

	@BeforeEach
	public void beforeEach() {
		config = new Configuration();
	}

	@Test
	@DisplayName("Test Configuration loads the default key provider if no Key Provider Factory is specified")
	public void testConfigurationLoadDefault() throws IOException, SAXException, ParserConfigurationException {
		config.Load(getResourceStream(CONFIGS_DIRECTORY + "/" + DEFAULT_CONFIG));
		assertTrue(config.getKeyProvider() instanceof KeyStoreKeyProvider);
		assertTrue(config.getKeyProviderFactory() instanceof KeyStoreKeyProviderFactory);
	}

	@Test
	@DisplayName("Test Configuration loads the specified Key Provider Factory")
	public void testConfigurationLoadCustom() throws ParserConfigurationException, SAXException, IOException {
		config.Load(getResourceStream(CONFIGS_DIRECTORY + "/" + CUSTOM_KEY_PROVIDER));
		assertTrue(config.getKeyProvider() instanceof CustomKeyProvider);
		assertTrue(config.getKeyProviderFactory() instanceof CustomKeyProviderFactory);
	}

	@Test
	@DisplayName("Test Configuration loads default key provider for the defalut constructor")
	public void testConfigurationDefaultConstructorKeyProvider() {
		assertTrue(config.getKeyProvider() instanceof KeyStoreKeyProvider);
		assertTrue(config.getKeyProviderFactory() instanceof KeyStoreKeyProviderFactory);
	}

	@Test
	@DisplayName("Test Configuration Setup is done correctly")
	public void testConfigurationSetupIsDoneCorrectly() throws IOException, SAXException, ParserConfigurationException {
		Configuration setup = new Configuration();
		setup.Load(ResourcesUtils.getResourceStream(CONFIGS_DIRECTORY + "/" + CUSTOM_KEY_PROVIDER));
		config.Setup(setup);

		assertEquals(setup.getMerchantID(), config.getMerchantID());
		assertEquals(setup.getMerchantSubID(), config.getMerchantSubID());

		assertEquals(setup.getMerchantReturnUrl(), config.getMerchantReturnUrl());
		assertEquals(setup.getAcquirerDirectoryURL(), config.getAcquirerDirectoryURL());
		assertEquals(setup.getAcquirerTransactionURL(), config.getAcquirerTransactionURL());
		assertEquals(setup.getAcquirerStatusURL(), config.getAcquirerStatusURL());

		assertEquals(setup.getKeyStoreLocation(), config.getKeyStoreLocation());
		assertEquals(setup.getKeyStorePassword(), config.getKeyStorePassword());

		assertEquals(setup.getMerchantCertificateAlias(), config.getMerchantCertificateAlias());
		assertEquals(setup.getMerchantCertificatePassword(), config.getMerchantCertificatePassword());

		assertEquals(setup.getAcquirerCertificateAlias(), config.getAcquirerCertificateAlias());
		assertEquals(setup.getAcquirerAlternateCertificateAlias(), config.getAcquirerAlternateCertificateAlias());

		assertEquals(setup.getSamlCertificateAlias(), config.getSamlCertificateAlias());
		assertEquals(setup.getSamlCertificatePassword(), config.getSamlCertificatePassword());

		assertEquals(setup.isLogsEnabled(), config.isLogsEnabled());
		assertEquals(setup.isServiceLogsEnabled(), config.isServiceLogsEnabled());
		assertEquals(setup.getServiceLogsLocation(), config.getServiceLogsLocation());
		assertEquals(setup.getServiceLogsPattern(), config.getServiceLogsPattern());

		assertEquals(setup.getLoggerFactory(), config.getLoggerFactory());
		assertEquals(setup.getMessenger()
						  .getClass(), config.getMessenger()
											 .getClass());

		assertEquals(setup.getKeyProviderFactory(), config.getKeyProviderFactory());
		assertEquals(config.getKeyProviderFactory()
						   .getClass(), CustomKeyProviderFactory.class);
		assertEquals(setup.getKeyProvider()
						  .getClass(), config.getKeyProvider()
											 .getClass());
		assertEquals(config.getKeyProvider()
						   .getClass(), CustomKeyProvider.class);

	}

}
