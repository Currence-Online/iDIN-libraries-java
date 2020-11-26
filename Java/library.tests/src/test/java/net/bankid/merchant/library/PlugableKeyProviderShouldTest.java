package net.bankid.merchant.library;

import net.bankid.merchant.library.keyProviders.CustomKeyProvider;
import net.bankid.merchant.library.keyProviders.CustomKeyProviderFactory;
import net.bankid.merchant.library.tests.constants.Messages;
import net.bankid.merchant.library.tests.utils.messages.TestMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URI;

import static net.bankid.merchant.library.tests.constants.Configs.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlugableKeyProviderShouldTest extends TestBase {

	@Test
	@DisplayName("Test that the specified Key Provider Factory is loaded and works properly")
	public void testPlugableKeyProviderWorksProperly() throws ParserConfigurationException, IOException, SAXException, CommunicatorException {
		setupConfig(CONFIGS_DIRECTORY + "/" + CUSTOM_KEY_PROVIDER);
		assertTrue(config.getKeyProvider() instanceof CustomKeyProvider);
		assertTrue(config.getKeyProviderFactory() instanceof CustomKeyProviderFactory);

		communicator = new Communicator(config);
		StatusResponse response = invokeStatusResponse();

		assertFalse(response.getIsError());
	}

	@Test
	@DisplayName("Test that the specified Key Provider Factory is loaded and works properly with alternate certificate")
	public void testPlugableKeyProviderLoadProperlyWithAlternateCertificate() throws ParserConfigurationException, SAXException, IOException, CommunicatorException {
		setupConfig(CONFIGS_DIRECTORY + "/" + CUSTOM_KEY_PROVIDER_ALTERNATE);
		assertTrue(config.getKeyProvider() instanceof CustomKeyProvider);
		assertTrue(config.getKeyProviderFactory() instanceof CustomKeyProviderFactory);

		communicator = new Communicator(config);
		StatusResponse response = invokeStatusResponse();

		assertFalse(response.getIsError());
	}

	private StatusResponse invokeStatusResponse() throws CommunicatorException {
		when(iMessenger.sendMessage(any(Configuration.class), anyString(), any(URI.class))).thenReturn(TestMessage.getMessage(Messages.STATUS_RESPONSE_SAMPLE));
		return communicator.getResponse(new StatusRequest("1234000000002144"));
	}
}
