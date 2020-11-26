package net.bankid.merchant.library;

import net.bankid.merchant.library.tests.utils.resources.ResourcesUtils;
import org.mockito.Mock;
import org.mockito.Spy;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static net.bankid.merchant.library.tests.constants.Configs.CONFIGS_DIRECTORY;
import static net.bankid.merchant.library.tests.constants.Configs.DEFAULT_CONFIG;
import static org.mockito.Mockito.lenient;

public abstract class TestBase {
	@Spy
	protected Configuration config = new Configuration();
	@Mock
	protected IMessenger iMessenger;

	protected Communicator communicator;

	protected void setupConfig(String configPath) throws IOException, ParserConfigurationException, SAXException {
		config.Load(ResourcesUtils.getResourceStream(configPath));
		lenient().when(config.getMessenger()).thenReturn(iMessenger);
	}

	protected void setupConfig() throws ParserConfigurationException, SAXException, IOException {
		setupConfig(CONFIGS_DIRECTORY + "/" + DEFAULT_CONFIG);
	}
}
