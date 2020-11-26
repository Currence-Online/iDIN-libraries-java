package net.bankid.merchant.library;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import net.bankid.merchant.library.internal.AcceptanceReportBase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import schemas.saml.protocol.*;

/**
 * SAML response: fields which are returned after a successful status request.
 * This class also handles decryption of attributes (e.g. NameID, urn:nl:bvn:bankid:1.0:consumer.bin, etc).
 */
public class SamlResponse extends AcceptanceReportBase {

    private String transactionID;
    private String merchantReference;
    private String acquirerID;
    private String version;
    private Map<String, String> attributes;
    private SamlStatus status;
    private List<SamlAttributesEncryptionKey> attributesEncryptionKeys;
    
    private static final String NAMESPACE_PROTOCOL_URL = "urn:oasis:names:tc:SAML:2.0:protocol";
    private static final String NAMESPACE_ASSERTION_URL = "urn:oasis:names:tc:SAML:2.0:assertion";
    private static final String NAMESPACE_XMLENC_URL = "http://www.w3.org/2001/04/xmlenc#";
    
    /**
     * Construct a SamlResponse from a SAML assertion (Response)
     * @param config the configuration object
     * @param response the saml assertion returned in a status response
     * @throws CommunicatorException
     */
    public SamlResponse(Configuration config, ResponseType response) throws CommunicatorException {
        try {                        
            this.transactionID = response.getID();
            this.merchantReference = response.getInResponseTo();
            this.acquirerID = response.getIssuer().getValue();
            this.version = response.getVersion();
            List<SamlAttributesEncryptionKey> encryptionKeys = new ArrayList<>();

            if (response.getStatus() != null)
            {
                StatusCodeType statusCodeLevel2 = response.getStatus().getStatusCode().getStatusCode();
                if (statusCodeLevel2 == null) {
                    throw new CommunicatorException("Missing second level status code");
                }
                status = new SamlStatus(
                            response.getStatus().getStatusMessage(), 
                            response.getStatus().getStatusCode().getValue(), 
                            statusCodeLevel2.getValue());
            }            
            
            if (response.getAssertionOrEncryptedAssertion().isEmpty())
                return;
            
            AssertionType assertion = (AssertionType) response.getAssertionOrEncryptedAssertion().get(0);            
            
            if (assertion.getSubject().getContent().isEmpty())
                return;
            
            EncryptedElementType nameIDEncrypted = EncryptedElementType.class.cast(assertion.getSubject().getContent().get(0).getValue());
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            
            attributes = new HashMap<>();
            {
                byte[] decryptedKey = getDecryptedKey(config, nameIDEncrypted);
                Document doc = db.parse(new ByteArrayInputStream(decryptElement(nameIDEncrypted, decryptedKey).getBytes()));
                Node node = doc.getDocumentElement().getFirstChild();
                String value = node.getTextContent();

                SamlAttributesEncryptionKey samlAttributesEncryptionKey = new SamlAttributesEncryptionKey();
                samlAttributesEncryptionKey.setAesKey(decryptedKey);

                if(value.startsWith("TRANS")) {
                    attributes.put(SamlAttribute.ConsumerTransientID, value);
                    samlAttributesEncryptionKey.setAttributeName(SamlAttribute.ConsumerTransientID);
                } else {
                    attributes.put(SamlAttribute.ConsumerBin, value);
                    samlAttributesEncryptionKey.setAttributeName(SamlAttribute.ConsumerBin);
                }

                encryptionKeys.add(samlAttributesEncryptionKey);
            }
            
            List<StatementAbstractType> list = assertion.getStatementOrAuthnStatementOrAuthzDecisionStatement();
            
            for (StatementAbstractType obj : list) {
                if (obj instanceof AttributeStatementType) {
                    AttributeStatementType attributeStatement = (AttributeStatementType) obj;
                    for (Object attr : attributeStatement.getAttributeOrEncryptedAttribute()) {
                        if (attr instanceof EncryptedElementType) {
                            EncryptedElementType element = (EncryptedElementType) attr;
                            byte[] decryptedKey = getDecryptedKey(config, element);
                            String xml = decryptElement(element, decryptedKey);
                            Document doc = db.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
                            
                            String key = doc.getDocumentElement().getAttribute("Name");
                            
                            Node node = doc.getDocumentElement().getFirstChild();
                            while (node != null && node.getNodeType() != Node.ELEMENT_NODE)
                            {
                                node = node.getNextSibling();
                            }
                            String value = node.getTextContent();
                            
                            attributes.put(key, value);

                            SamlAttributesEncryptionKey samlAttributesEncryptionKey = new SamlAttributesEncryptionKey();
                            samlAttributesEncryptionKey.setAttributeName(key);
                            samlAttributesEncryptionKey.setAesKey(decryptedKey);
                            encryptionKeys.add(samlAttributesEncryptionKey);
                        }
                        else if (attr instanceof AttributeType) {
                            AttributeType element = (AttributeType) attr;
                            String key = element.getName();
                            String value = "";
                            for (Object o : element.getAttributeValue())
                            {
                                if (o instanceof Element)
                                    value += ((Element) o).getTextContent();
                            }
                            
                            attributes.put(key, value);
                        }
                    }
                }
            }

            this.attributesEncryptionKeys = Collections.unmodifiableList(encryptionKeys);
        }
        catch (Exception e) {
            throw new CommunicatorException(e);
        }
    }

    private byte[] getDecryptedKey(Configuration config, EncryptedElementType encryptedElement) throws Exception {
        JAXBElement<EncryptedKeyType> encryptedKeyJaxb = JAXBElement.class.cast(encryptedElement.getEncryptedData()
                                                                                                .getKeyInfo()
                                                                                                .getContent()
                                                                                                .get(0));
        EncryptedKeyType encryptedKey = encryptedKeyJaxb.getValue();

        return decryptKey(config, encryptedKey);
    }

    private String decryptElement(EncryptedElementType encryptedElement, byte[] decryptedKey) throws Exception
    {
        byte[] bytes = encryptedElement.getEncryptedData().getCipherData().getCipherValue();
        
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
        if (decrypted[decrypted.length - 1] <= 16) {
            dataLength = decrypted.length - decrypted[decrypted.length - 1];
        }
        
        return new String(decrypted, 0, dataLength, StandardCharsets.UTF_8).trim();
    }
    
    private byte[] decryptKey(Configuration config, EncryptedKeyType encryptedKey) throws Exception {
        SigningKeyPair keyEntry = config.getKeyProvider().getMerchantSigningKeyPair();
        X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

        byte[] bytes = encryptedKey.getCipherData().getCipherValue();

        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, keyEntry.getPrivateKey());

        return cipher.doFinal(bytes);
    }
    
    static SamlResponse Parse(Configuration config, Object obj) throws JAXBException, CommunicatorException {
        ResponseType response = Utils.deserialize((Node) obj, ResponseType.class);
        
        return new SamlResponse(config, response);
    }
    
    /**
     * @return the transactionID
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * @return the attributes
     */
    public Map<String, String> getAttributes() {
        if (attributes == null)
            attributes = new HashMap<>();
        return attributes;
    }

    /**
     * @return the merchant reference
     */
    public String getMerchantReference() {
        return merchantReference;
    }

    /**
     * @return the acquirerID
     */
    public String getAcquirerID() {
        return acquirerID;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }
    
    /**
     * @return the status
     */
    public SamlStatus getStatus() {
        return status;
    }

    /**
     *
     * @return the attributes encryption keys
     */
    public List<SamlAttributesEncryptionKey> getAttributesEncryptionKeys(){
        return attributesEncryptionKeys;
    }
}
