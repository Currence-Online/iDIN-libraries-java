package net.bankid.merchant.library;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import net.bankid.merchant.library.validation.Validator;
import schemas.saml.assertion.AttributeType;
import schemas.saml.protocol.AuthnContextComparisonType;
import schemas.saml.protocol.AuthnRequestType;
import schemas.saml.protocol.ConditionsType;
import schemas.saml.protocol.ExtensionsType;
import schemas.saml.protocol.NameIDType;
import schemas.saml.protocol.ObjectFactory;
import schemas.saml.protocol.RequestedAuthnContextType;
import schemas.saml.protocol.ScopingType;

class BankIdMessageBuilder {
    
    public Element getTransaction(final Configuration config, final AuthenticationRequest transactionReq)
        throws DatatypeConfigurationException, JAXBException, ParserConfigurationException, SAXException, IOException, CommunicatorException {
        
        AuthnRequestType authnReq = new AuthnRequestType() {{
            id = transactionReq.getMerchantReference();
            version = "2.0";
            issueInstant = Utils.toUtc(transactionReq.getCreated());
            consent = "true";
            forceAuthn = true;
            protocolBinding = "nl:bvn:bankid:1.0:protocol:iDx";
            assertionConsumerServiceURL = config.getMerchantReturnUrl();
            attributeConsumingServiceIndex = transactionReq.getRequestedServiceID().getValue();
            issuer = new NameIDType() {{
                value = config.getMerchantID();
                conditions = new ConditionsType() {{     
                }};
                requestedAuthnContext = new RequestedAuthnContextType() {{
                    getAuthnContextClassRef().add(transactionReq.getAssuranceLevel().stringValue());
                    comparison = AuthnContextComparisonType.MINIMUM;
                }};
                scoping = new ScopingType() {{
                    // ???
                }};
                extensions = getAuthnRequestExtensions(transactionReq);
            }};
        }};
        
        Validator.validate(authnReq);
        JAXBElement<AuthnRequestType> jaxbElementAuthReq = new ObjectFactory().createAuthnRequest(authnReq);
        String s = Utils.serialize(jaxbElementAuthReq, AuthnRequestType.class);
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(s.getBytes()));
        
        return doc.getDocumentElement();
    }
    
    private static ExtensionsType getAuthnRequestExtensions(AuthenticationRequest authenticationRequest) {
    	ExtensionsType result = new ExtensionsType();
    	String documentId = authenticationRequest.documentID;
    	
		if (documentId != null && !"".equals(documentId)) {
			Element documentIdAttribute = getDocumentIdElement(authenticationRequest.documentID);

			if (documentIdAttribute != null) {
				result.getAny().add(documentIdAttribute);
			}
			return result;
		}
    	   	
    	return null;
    }
    
    private static Element getDocumentIdElement(String documentID) {
		AttributeType documentIdAttribute = new AttributeType();
		documentIdAttribute.setName(SamlAttribute.DocumentId);
		documentIdAttribute.getAttributeValue()
						   .add(documentID);
		JAXBElement<AttributeType> attribute = new schemas.saml.assertion.ObjectFactory().createAttribute(documentIdAttribute);
		try {
			String xml = Utils.serialize(attribute, AttributeType.class);
			return getXmlElement(xml);
		} catch (CommunicatorException | JAXBException e) {
			e.printStackTrace();
		}

		return null;
    }
    
	private static Element getXmlElement(String xml) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xml)));
			return doc.getDocumentElement();
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
    
}
