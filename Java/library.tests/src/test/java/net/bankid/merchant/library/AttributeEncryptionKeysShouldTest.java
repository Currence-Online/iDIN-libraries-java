package net.bankid.merchant.library;

import net.bankid.merchant.library.tests.constants.Messages;
import net.bankid.merchant.library.tests.utils.messages.TestMessage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static net.bankid.merchant.library.tests.constants.AttributeNames.*;
import static net.bankid.merchant.library.xml.XmlUtils.decryptElement;
import static net.bankid.merchant.library.xml.XmlUtils.parse;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AttributeEncryptionKeysShouldTest extends TestBase {

	private static final String ENCRYPTED_DATA = "EncryptedData";
	private static final String SUBJECT_VALUE = "Some Subject";

	@BeforeEach
	protected void init() throws ParserConfigurationException, IOException, SAXException {
		setupConfig();
		communicator = new Communicator(config);
	}

	@Test
	@DisplayName("Test that Status Response has no errors")
	public void testNoErrorInSampleMessage() throws CommunicatorException {
		StatusResponse response = invokeStatusResponse();
		checkStatusResponse(response);
	}

	@Test
	@DisplayName("Test that SAML Response Attributes are parsed correctly")
	public void testAttributesParsedCorrectly() throws CommunicatorException {
		StatusResponse response = invokeStatusResponse();
		checkStatusResponse(response);

		Map<String, String> attributes = response.getSamlResponse()
												 .getAttributes();

		assertFalse("Attributes Map should not contain attribute with key name " + AGE_18_OR_OLDER, attributes.containsKey(AGE_18_OR_OLDER));
		assertEquals(9, attributes.size());
		assertEquals("SJĆ", attributes.get(CONSUMER_INITIALS));
		assertEquals("Ja", attributes.get(PARTNER_LAST_NAME_PREFIX));
		assertEquals("Sm", attributes.get(LEGAL_LAST_NAME_PREFIX));
		assertEquals(SUBJECT_VALUE, attributes.get(CONSUMER_BIN));
		assertEquals("Smith", attributes.get(LEGAL_LAST_NAME));
		assertEquals("4096", attributes.get(DELIVERED_SERVICE_ID));
		assertEquals("John", attributes.get(PREFERRED_LAST_NAME));
		assertEquals("Jane", attributes.get(PARTNER_LAST_NAME));
		assertEquals("Jo", attributes.get(PREFERRED_LAST_NAME_PREFIX));
	}

	@Test
	@DisplayName("Test that the subject can be decrypted from the Raw XML Message using the the new Attributes Encryption Keys")
	public void testRecomputeSubjectBasedOnAttributeKeys() throws Exception {
		StatusResponse response = invokeStatusResponse();
		checkStatusResponse(response);

		Map<String, String> attributes = response.getSamlResponse()
												 .getAttributes();
		assertEquals(attributes.get(CONSUMER_BIN), SUBJECT_VALUE);

		SamlAttributesEncryptionKey subjectKey = response.getSamlResponse()
														 .getAttributesEncryptionKeys()
														 .get(0);
		Document doc = parse(response.getRawMessage());
		Element encryptedSubject = (Element) doc.getDocumentElement()
												.getElementsByTagName(ENCRYPTED_DATA)
												.item(0);

		Element decryptedSubject = parse(decryptElement(encryptedSubject, subjectKey.getAesKey())).getDocumentElement();
		assertEquals(SUBJECT_VALUE, decryptedSubject.getTextContent());
	}

	@TestFactory
	@DisplayName("Test that all the attributes can be decrypted from the Raw XML Message using the the new Attributes Encryption Keys")
	public Stream<DynamicNode> testRecomputeAttributesBasedOnAttributeKeys() throws Exception {
		StatusResponse response = invokeStatusResponse();
		checkStatusResponse(response);

		NodeList encryptedDataList = parse(response.getRawMessage()).getDocumentElement()
																	.getElementsByTagName(ENCRYPTED_DATA);
		List<SamlAttributesEncryptionKey> keys = response.getSamlResponse()
														 .getAttributesEncryptionKeys();
		Map<String, String> attributes = response.getSamlResponse()
												 .getAttributes();

		String testNameTemplate = "Recompute Attribute with index %s based on Attribute Keys";
		return IntStream.range(1, encryptedDataList.getLength())
						.mapToObj(index -> dynamicTest(String.format(testNameTemplate, index), () -> {
							String decryptedAttribute = decryptElement((Element) encryptedDataList.item(index), keys.get(index)
																													.getAesKey());
							Element decryptedAttributeElement = parse(decryptedAttribute).getDocumentElement();
							String name = decryptedAttributeElement.getAttribute("Name");

							assertTrue("Decrypted Attribute Name does not exist in Attributes Map.", attributes.containsKey(name));
							String elementText = decryptedAttributeElement.getTextContent().trim();
							assertEquals(elementText, attributes.get(name));
						}));
	}

	private void checkStatusResponse(StatusResponse response) {
		assertFalse("The Status Response should have been successful.", response.getIsError());
		assertNotNull("The SAML Response should not have been null.", response.getSamlResponse());
		assertEquals("The Status of the response should had been 'Success'.", response.getStatus(), "Success");
	}

	private StatusResponse invokeStatusResponse() throws CommunicatorException {
		when(iMessenger.sendMessage(any(Configuration.class), anyString(), any(URI.class))).thenReturn(TestMessage.getMessage(Messages.STATUS_RESPONSE_SAMPLE));
		return communicator.getResponse(new StatusRequest("1234000000002144"));
	}

}
