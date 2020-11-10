package net.bankid.merchant.library;

import net.bankid.merchant.library.tests.utils.resources.ResourcesUtils;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import java.io.InputStream;

import static net.bankid.merchant.library.tests.constants.Certificates.*;
import static org.mockito.Mockito.lenient;

public abstract class TestBase {

	@Mock
	protected Configuration config;
	@Mock
	protected IMessenger iMessenger;
	@Mock
	protected ILogger logger;
	@Mock
	protected ILoggerFactory loggerFactory;

	protected Communicator communicator;

	@BeforeEach
	protected void init() {
		setupConfig();
		communicator = new Communicator(config);
	}

	protected void setupConfig() {
		String keyStorePath = CERTIFICATES_DIRECTORY + "/" + KEYSTORE_NAME;
		InputStream keystore = ResourcesUtils.getResourceStream(keyStorePath);
		keystore.mark(Integer.MAX_VALUE);

		lenient().when(config.getMerchantID()).thenReturn("1234567890");
		lenient().when(config.getMerchantSubID()).thenReturn(0);

		lenient().when(config.getKeyStoreLocation()).thenReturn(keyStorePath);
		lenient().when(config.getKeyStorePassword()).thenReturn(KEYSTORE_PASS);
		lenient().when(config.getKeyStore()).thenReturn(keystore);

		lenient().when(config.getMerchantCertificateAlias()).thenReturn(CERTIFICATE_ALIAS);
		lenient().when(config.getMerchantCertificatePassword()).thenReturn(KEYSTORE_PASS);
		lenient().when(config.getAcquirerCertificateAlias()).thenReturn(ACQUIRER_CERTIFICATE_ALIAS);

		lenient().when(config.getSamlCertificateAlias()).thenReturn(CERTIFICATE_ALIAS);
		lenient().when(config.getSamlCertificatePassword()).thenReturn(KEYSTORE_PASS);

		lenient().when(config.getMerchantReturnUrl()).thenReturn("http://localhost");
		lenient().when(config.getAcquirerDirectoryURL()).thenReturn("http://example.com/directory");
		lenient().when(config.getAcquirerStatusURL()).thenReturn("http://example.com/status");
		lenient().when(config.getAcquirerTransactionURL()).thenReturn("http://example.com/transaction");

		lenient().when(config.isLogsEnabled()).thenReturn(false);
		lenient().when(config.isServiceLogsEnabled()).thenReturn(false);

		lenient().when(config.getMessenger()).thenReturn(iMessenger);
		lenient().when(config.getLoggerFactory()).thenReturn(loggerFactory);
		lenient().when(loggerFactory.create()).thenReturn(logger);
	}
}
