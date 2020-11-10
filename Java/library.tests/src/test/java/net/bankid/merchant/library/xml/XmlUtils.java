package net.bankid.merchant.library.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class XmlUtils {

	private static final String CIPHER_VALUE = "CipherValue";
	private static final String CIPHER_DATA = "CipherData";

	/**
	 * Converts a String representation of an xml to a Document
	 *
	 * @param rawXml the String representation of the xml
	 *
	 * @return the parsed document
	 */

	public static Document parse(String rawXml) throws ParserConfigurationException, IOException, SAXException {
		ByteArrayInputStream is = new ByteArrayInputStream(rawXml.getBytes(StandardCharsets.UTF_8));
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		return dbf.newDocumentBuilder()
				  .parse(is);
	}

	/**
	 * Decrypts an element of type EncryptedData
	 *
	 * @param encryptedData the encrypted data Element
	 * @param decryptedKey the key to ues for decryption
	 *
	 * @return String representation of the decrypted element
	 */
	public static String decryptElement(Element encryptedData, byte[] decryptedKey) throws Exception {
		byte[] bytes = getEncryptedDataCipherValue(encryptedData);

		// first 16 bytes = Initialization vector
		byte[] IV = new byte[16];

		// next X bytes = data
		byte[] data = new byte[bytes.length - 16];

		System.arraycopy(bytes, 0, IV, 0, IV.length);
		System.arraycopy(bytes, IV.length, data, 0, bytes.length - IV.length);

		SecretKeySpec secretAesKey = new SecretKeySpec(decryptedKey, "AES");
		Cipher aes = Cipher.getInstance("AES/CBC/NoPadding");
		aes.init(Cipher.DECRYPT_MODE, secretAesKey, new IvParameterSpec(IV));

		byte[] decrypted = aes.doFinal(data);

		int dataLength = decrypted.length;
		// Verify padding and adjust data length accordingly:
		// If the last byte is non-ascii and it's value is <16, this indicates
		// a padding was added with the number of bytes equal to it's value.
		// This padding mode is called ISO10126 in .NET
		if(decrypted[decrypted.length - 1] <= 16) {
			dataLength = decrypted.length - decrypted[decrypted.length - 1];
		}
		return new String(decrypted, 0, dataLength, StandardCharsets.UTF_8).trim();
	}

	private static byte[] getEncryptedDataCipherValue(Element encryptedData) {
		for(Node child = encryptedData.getFirstChild(); child != null; child = child.getNextSibling()) {
			if(child instanceof Element && CIPHER_DATA.equals(child.getNodeName())) {
				String cipherValue = ((Element) child).getElementsByTagName(CIPHER_VALUE)
													  .item(0)
													  .getTextContent();
				return Base64.getDecoder()
							 .decode(cipherValue);
			}
		}
		return new byte[]{ };
	}

}
