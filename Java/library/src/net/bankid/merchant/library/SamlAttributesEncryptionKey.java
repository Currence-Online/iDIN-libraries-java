package net.bankid.merchant.library;

/**
 * Class responsible for handling Saml Attributes Encryption Keys
 */
public class SamlAttributesEncryptionKey {

	private String attributeName;
	private byte[] aesKey;

	/**
	 *
	 * @return The attribute name
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 *
	 * @param attributeName The attribute name
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 *
	 * @return The aes key
	 */

	public byte[] getAesKey() {
		return aesKey;
	}

	/**
	 *
	 * @param aesKey The aes key
	 */
	public void setAesKey(byte[] aesKey) {
		this.aesKey = aesKey;
	}

}
