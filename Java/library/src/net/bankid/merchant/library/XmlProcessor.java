package net.bankid.merchant.library;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.crypto.AlgorithmMethod;
import javax.xml.crypto.KeySelector;
import javax.xml.crypto.KeySelectorException;
import javax.xml.crypto.KeySelectorResult;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyName;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

class XmlProcessor {
    protected ILogger logger;
    
    public XmlProcessor(Configuration config) {
        this.logger = config.getLoggerFactory().create();
    }
    
    public String AddSignature(Configuration config, String xml)
            throws CommunicatorException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException,
                   UnrecoverableEntryException, InvalidAlgorithmParameterException, ParserConfigurationException, MarshalException,
                   SAXException, XMLSignatureException,
                   TransformerException {
        logger.Log(config, "adding signature...");
        SigningKeyPair keyEntry = config.getKeyProvider().getMerchantSigningKeyPair();
        X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        
        List<Transform> transforms = new ArrayList<>();
        transforms.add(fac.newTransform("http://www.w3.org/2000/09/xmldsig#enveloped-signature", (TransformParameterSpec) null));
        transforms.add(fac.newTransform("http://www.w3.org/2001/10/xml-exc-c14n#", (TransformParameterSpec) null));

        Reference ref = fac.newReference(
            "",
            fac.newDigestMethod(DigestMethod.SHA256, null),
            transforms,
            null,
            null
        );

        SignedInfo si = fac.newSignedInfo(
            fac.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE, (C14NMethodParameterSpec) null),
            fac.newSignatureMethod("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256", null),
            Collections.singletonList(ref)
        );

        KeyInfoFactory kif = fac.getKeyInfoFactory();
        KeyName kn = kif.newKeyName(Utils.sha1Hex(cert.getEncoded()));
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(kn));

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes()));

        DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement());

        logger.Log(config, "signing xml");
        XMLSignature signature = fac.newXMLSignature(si, ki);
        signature.sign(dsc);

        return Utils.serialize(doc);
    }
    
    public boolean VerifySchema(final Configuration config, String xml) throws SAXException, ParserConfigurationException, IOException {
        logger.Log(config, "verifying schema...");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        dbf.setNamespaceAware(true);

        logger.Log(config, "building schema set");
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        dbf.setSchema(sf.newSchema(new Source[] {
            new StreamSource(XmlProcessor.class.getResourceAsStream("/schemas/idx/xmldsig-core-schema.xsd")),
            new StreamSource(XmlProcessor.class.getResourceAsStream("/schemas/idx/idx.merchant-acquirer.1.0.xsd")),
            new StreamSource(XmlProcessor.class.getResourceAsStream("/schemas/saml/assertion/xenc-schema.xsd")),
            new StreamSource(XmlProcessor.class.getResourceAsStream("/schemas/saml/assertion/saml-schema-assertion-2.0.xsd")),
            new StreamSource(XmlProcessor.class.getResourceAsStream("/schemas/saml/authenticationcontext/saml-schema-authn-context-2.0.xsd")),
            new StreamSource(XmlProcessor.class.getResourceAsStream("/schemas/saml/metadata/xml.xsd")),
            new StreamSource(XmlProcessor.class.getResourceAsStream("/schemas/saml/metadata/saml-schema-metadata-2.0.xsd")),
            new StreamSource(XmlProcessor.class.getResourceAsStream("/schemas/saml/protocol/saml-schema-protocol-2.0.xsd"))
        }));

        DocumentBuilder db = dbf.newDocumentBuilder();
        db.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
            }
            @Override
            public void error(SAXParseException exception) throws SAXException {
                logger.Log(config, "schema error: " + exception.getMessage());
                throw new SAXException(exception);
            }
            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                logger.Log(config, "schema error: " + exception.getMessage());
                throw new SAXException(exception);
            }
        });

        db.parse(new ByteArrayInputStream(xml.getBytes()));

        logger.Log(config, "schema is valid");
        return true;
    }
    
    public boolean VerifySignature(Configuration config, String xml) throws ParserConfigurationException, SAXException, IOException, MarshalException, XMLSignatureException {
        logger.Log(config, "verifying signature...");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);

        Document doc = dbf.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes()));

        doc.getDocumentElement().normalize();
        
        NodeList signatures = doc.getElementsByTagNameNS("*", "Signature");
        if (signatures.getLength() == 2) {
            Element assertion = (Element) (doc.getElementsByTagNameNS("urn:oasis:names:tc:SAML:2.0:assertion", "Assertion").item(0));
            if (VerifyBankIdSignature(config, assertion)) {
                logger.Log(config, "bankid signature is valid");
                Element signature = (Element) (signatures.item(signatures.getLength() - 1));
                boolean b = CheckIdxSignature(config, doc, signature);
                logger.Log(config, "idx signature is valid: " + b);
                return b;
            } else {
                logger.Log(config, "bankid signature is not valid");
                return false;
            }
        }
        else {
            if (signatures.getLength() == 1) {
                Element signature = (Element) (signatures.item(signatures.getLength() - 1));
                boolean b = CheckIdxSignature(config, doc, signature);
                logger.Log(config, "idx signature is valid: " + b);
                return b;
            }
            else {
                logger.Log(config, "signatures in document: " + signatures.getLength());
                return false;
            }
        }
    }
    
    private boolean VerifyBankIdSignature(Configuration config, Element assertion) throws ParserConfigurationException, MarshalException, XMLSignatureException, SAXException, IOException {
       Element signature = (Element) (assertion.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "Signature").item(0));

        return CheckBankIdSignature(config, assertion,signature);
    }
    
    private boolean CheckBankIdSignature(Configuration config, Element assertion ,Element signature) throws MarshalException, XMLSignatureException {
        
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        DOMValidateContext valContext = new DOMValidateContext(new BankIdKeySelector(config), signature);
       
        valContext.setIdAttributeNS(assertion, null, "ID");
        
        XMLSignature sig = fac.unmarshalXMLSignature(valContext);
        
        boolean b = sig.validate(valContext);
        
        // Check core validation status
        if (b == false) {
            try
            {
            logger.Log(config, "bankid core validation failed");
            boolean sv = sig.getSignatureValue().validate(valContext);
            logger.Log(config,"bankid cryptographic signature validation is: " + sv);
            
            // check the validation status of each Reference
            Iterator i = sig.getSignedInfo().getReferences().iterator();
            for (int j=0; i.hasNext(); j++) {
                Reference nextReference = (Reference) i.next();
                
                boolean refValid = nextReference.validate(valContext);
                logger.Log(config,"Reference " + nextReference.getURI() + " validation is " + refValid);
            }
            }
            catch(Exception ex)
            {
                logger.Log(config,"Exception occured during signature validation", ex);
            }
        }
        return b;
    }
    
    private boolean CheckIdxSignature(Configuration config, Document doc, Element signature) throws MarshalException, XMLSignatureException, IOException {
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        try
        {
            if(CheckIdxSignature(config, config.getAcquirerCertificateAlias(), doc, signature, fac))
            {
                return true;
            }
            return CheckIdxSignature(config, config.getAcquirerAlternateCertificateAlias(), doc, signature, fac);
        }
        catch(NullPointerException npe)
        {
            logger.Log(config,"Failed to use acquirer cetificate. Trying to use acquirer alternate certificate", npe);
            return CheckIdxSignature(config, config.getAcquirerAlternateCertificateAlias(), doc, signature, fac);
        }
    }
    
    private boolean CheckIdxSignature(Configuration config, String acquirerCertificateAlias, Document doc, Element signature, XMLSignatureFactory fac) throws MarshalException, XMLSignatureException, IOException 
    {
        if(acquirerCertificateAlias == null || acquirerCertificateAlias.isEmpty())
        {
            logger.Log(config,"When checking idx signature, acquirer certificate was null or empty!");
            return false;
        }
        DOMValidateContext valContext = new DOMValidateContext(new idxKeySelector(config, acquirerCertificateAlias), doc);
        XMLSignature sig = fac.unmarshalXMLSignature(new DOMStructure(signature));

        valContext.setProperty("javax.xml.crypto.dsig.cacheReference", Boolean.TRUE);

        return sig.validate(valContext);
    }
    
    private class BankIdKeySelector extends KeySelector {
        private Configuration config;
        
        public BankIdKeySelector(Configuration config){
            this.config = config;
        }
        
        private X509Certificate extractCertificate(X509Data struct) throws KeySelectorException {        
            Iterator iterator = struct.getContent().iterator();

            while (iterator.hasNext()) {
                Object element = iterator.next();

                if (element instanceof X509Certificate)
                    return (X509Certificate)element;
            }

            throw new KeySelectorException();
        }
        
        @Override
        public KeySelectorResult select(KeyInfo keyInfo, Purpose purpose, AlgorithmMethod method, XMLCryptoContext context) throws KeySelectorException {
            XMLStructure ki = (XMLStructure) keyInfo.getContent().iterator().next();
            if (!(ki instanceof X509Data)) {
                throw new KeySelectorException("KeyName not found!");
            }
            X509Data data = (X509Data) ki;
            final X509Certificate cert = extractCertificate(data);
            
            logger.Log(config, "checking BankId signature with certificate:");
            try {
                logger.Log(config, "  fingerprint: " + Utils.sha1Hex(cert.getEncoded()));
            } catch (CertificateEncodingException | NoSuchAlgorithmException ex) {
                logger.Log(config, "  (cannot get fingerprint): " + ex.getMessage());
            }
            logger.Log(config, "  subject    : " + cert.getSubjectDN().getName());
            logger.Log(config, "  issuer     : " + cert.getIssuerDN().getName());
            
            return new KeySelectorResult() {
                @Override
                public Key getKey() {
                    return cert.getPublicKey();
                }
            };
        }
    }
    
    private class idxKeySelector extends KeySelector {
        
        private Configuration config;
        private String acquirerCertificateAlias;
        
        public idxKeySelector(Configuration config, String acquirerCertificateAlias){
            this.config = config;
            this.acquirerCertificateAlias = acquirerCertificateAlias;
        }
        
        @Override
        public KeySelectorResult select(KeyInfo keyInfo, Purpose purpose, AlgorithmMethod method, XMLCryptoContext context) throws KeySelectorException {
            try {
                XMLStructure ki = (XMLStructure) keyInfo.getContent().iterator().next();
                if (!(ki instanceof KeyName)) {
                    throw new KeySelectorException("KeyName not found!");
                }
                KeyName kn = (KeyName) ki;
                String thumbprint = kn.getName();

                X509Certificate cert = config.getKeyProvider().getAcquirerCertificate(acquirerCertificateAlias);
                final PublicKey pk = cert.getPublicKey();
                
                logger.Log(config, "checking iDx signature with certificate:");
                logger.Log(config, "  fingerprint: " + thumbprint);
                logger.Log(config, "  subject    : " + cert.getSubjectDN().getName());
                logger.Log(config, "  issuer     : " + cert.getIssuerDN().getName());
                
                return new KeySelectorResult() {
                    @Override
                    public Key getKey() {
                        return pk;
                    }
                };
            } catch (IOException | NoSuchAlgorithmException | CertificateException | KeyStoreException ex) {
                throw new KeySelectorException(ex);
            }
        }
    }
}
