
package schemas.saml.metadata;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the schemas.saml.metadata package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PGPData_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "PGPData");
    private final static QName _EncryptionProperty_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "EncryptionProperty");
    private final static QName _EncryptedKey_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "EncryptedKey");
    private final static QName _SPSSODescriptor_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "SPSSODescriptor");
    private final static QName _SPKIData_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SPKIData");
    private final static QName _CanonicalizationMethod_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "CanonicalizationMethod");
    private final static QName _Assertion_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Assertion");
    private final static QName _IDPSSODescriptor_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "IDPSSODescriptor");
    private final static QName _SingleLogoutService_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "SingleLogoutService");
    private final static QName _Manifest_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Manifest");
    private final static QName _Transforms_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Transforms");
    private final static QName _EntitiesDescriptor_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "EntitiesDescriptor");
    private final static QName _AttributeService_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "AttributeService");
    private final static QName _RequestedAttribute_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "RequestedAttribute");
    private final static QName _AuthnContext_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AuthnContext");
    private final static QName _SignatureMethod_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignatureMethod");
    private final static QName _AuthzService_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "AuthzService");
    private final static QName _KeyInfo_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "KeyInfo");
    private final static QName _AuthnQueryService_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "AuthnQueryService");
    private final static QName _DigestMethod_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "DigestMethod");
    private final static QName _MgmtData_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "MgmtData");
    private final static QName _ManageNameIDService_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "ManageNameIDService");
    private final static QName _EncryptedID_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "EncryptedID");
    private final static QName _SurName_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "SurName");
    private final static QName _Issuer_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Issuer");
    private final static QName _AuthnAuthorityDescriptor_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "AuthnAuthorityDescriptor");
    private final static QName _NameID_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "NameID");
    private final static QName _AdditionalMetadataLocation_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "AdditionalMetadataLocation");
    private final static QName _Company_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "Company");
    private final static QName _AttributeConsumingService_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "AttributeConsumingService");
    private final static QName _OrganizationDisplayName_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "OrganizationDisplayName");
    private final static QName _Extensions_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "Extensions");
    private final static QName _ServiceDescription_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "ServiceDescription");
    private final static QName _EncryptedAssertion_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "EncryptedAssertion");
    private final static QName _CipherData_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "CipherData");
    private final static QName _OrganizationURL_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "OrganizationURL");
    private final static QName _SignedInfo_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignedInfo");
    private final static QName _SubjectLocality_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "SubjectLocality");
    private final static QName _Object_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Object");
    private final static QName _Evidence_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Evidence");
    private final static QName _Statement_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Statement");
    private final static QName _X509Data_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509Data");
    private final static QName _Conditions_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Conditions");
    private final static QName _AttributeValue_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AttributeValue");
    private final static QName _AssertionURIRef_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AssertionURIRef");
    private final static QName _Organization_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "Organization");
    private final static QName _AssertionIDRef_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AssertionIDRef");
    private final static QName _EncryptionMethod_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "EncryptionMethod");
    private final static QName _SignatureProperties_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignatureProperties");
    private final static QName _ProxyRestriction_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "ProxyRestriction");
    private final static QName _KeyName_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "KeyName");
    private final static QName _AttributeProfile_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "AttributeProfile");
    private final static QName _SingleSignOnService_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "SingleSignOnService");
    private final static QName _TelephoneNumber_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "TelephoneNumber");
    private final static QName _AffiliationDescriptor_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "AffiliationDescriptor");
    private final static QName _ContactPerson_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "ContactPerson");
    private final static QName _RetrievalMethod_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "RetrievalMethod");
    private final static QName _CipherReference_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "CipherReference");
    private final static QName _AudienceRestriction_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AudienceRestriction");
    private final static QName _SignatureProperty_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignatureProperty");
    private final static QName _EntityDescriptor_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "EntityDescriptor");
    private final static QName _Condition_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Condition");
    private final static QName _AuthnStatement_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AuthnStatement");
    private final static QName _SubjectConfirmation_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "SubjectConfirmation");
    private final static QName _EncryptedData_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "EncryptedData");
    private final static QName _AffiliateMember_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "AffiliateMember");
    private final static QName _GivenName_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "GivenName");
    private final static QName _AuthenticatingAuthority_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AuthenticatingAuthority");
    private final static QName _Reference_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Reference");
    private final static QName _ServiceName_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "ServiceName");
    private final static QName _RoleDescriptor_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "RoleDescriptor");
    private final static QName _AttributeAuthorityDescriptor_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "AttributeAuthorityDescriptor");
    private final static QName _AttributeStatement_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AttributeStatement");
    private final static QName _RSAKeyValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "RSAKeyValue");
    private final static QName _Attribute_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Attribute");
    private final static QName _AuthnContextDecl_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AuthnContextDecl");
    private final static QName _Subject_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Subject");
    private final static QName _Signature_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Signature");
    private final static QName _DSAKeyValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "DSAKeyValue");
    private final static QName _PDPDescriptor_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "PDPDescriptor");
    private final static QName _OrganizationName_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "OrganizationName");
    private final static QName _Advice_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Advice");
    private final static QName _NameIDFormat_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "NameIDFormat");
    private final static QName _SignatureValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignatureValue");
    private final static QName _Transform_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Transform");
    private final static QName _EncryptionProperties_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "EncryptionProperties");
    private final static QName _NameIDMappingService_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "NameIDMappingService");
    private final static QName _ArtifactResolutionService_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "ArtifactResolutionService");
    private final static QName _AssertionConsumerService_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "AssertionConsumerService");
    private final static QName _SubjectConfirmationData_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "SubjectConfirmationData");
    private final static QName _AgreementMethod_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "AgreementMethod");
    private final static QName _DigestValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "DigestValue");
    private final static QName _AuthnContextClassRef_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AuthnContextClassRef");
    private final static QName _Audience_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Audience");
    private final static QName _KeyDescriptor_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "KeyDescriptor");
    private final static QName _Action_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "Action");
    private final static QName _KeyValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "KeyValue");
    private final static QName _AuthzDecisionStatement_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AuthzDecisionStatement");
    private final static QName _AuthnContextDeclRef_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "AuthnContextDeclRef");
    private final static QName _OneTimeUse_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "OneTimeUse");
    private final static QName _EncryptedAttribute_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "EncryptedAttribute");
    private final static QName _EmailAddress_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "EmailAddress");
    private final static QName _AssertionIDRequestService_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:metadata", "AssertionIDRequestService");
    private final static QName _BaseID_QNAME = new QName("urn:oasis:names:tc:SAML:2.0:assertion", "BaseID");
    private final static QName _PGPDataTypePGPKeyID_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "PGPKeyID");
    private final static QName _PGPDataTypePGPKeyPacket_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "PGPKeyPacket");
    private final static QName _SPKIDataTypeSPKISexp_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SPKISexp");
    private final static QName _EncryptionMethodTypeKeySize_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "KeySize");
    private final static QName _EncryptionMethodTypeOAEPparams_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "OAEPparams");
    private final static QName _TransformTypeXPath_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "XPath");
    private final static QName _AgreementMethodTypeOriginatorKeyInfo_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "OriginatorKeyInfo");
    private final static QName _AgreementMethodTypeRecipientKeyInfo_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "RecipientKeyInfo");
    private final static QName _AgreementMethodTypeKANonce_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "KA-Nonce");
    private final static QName _SignatureMethodTypeHMACOutputLength_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "HMACOutputLength");
    private final static QName _ReferenceListDataReference_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "DataReference");
    private final static QName _ReferenceListKeyReference_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "KeyReference");
    private final static QName _X509DataTypeX509IssuerSerial_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509IssuerSerial");
    private final static QName _X509DataTypeX509Certificate_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509Certificate");
    private final static QName _X509DataTypeX509SKI_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509SKI");
    private final static QName _X509DataTypeX509SubjectName_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509SubjectName");
    private final static QName _X509DataTypeX509CRL_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509CRL");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: schemas.saml.metadata
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EntitiesDescriptorType }
     * 
     */
    public EntitiesDescriptorType createEntitiesDescriptorType() {
        return new EntitiesDescriptorType();
    }

    /**
     * Create an instance of {@link SignedInfoType }
     * 
     */
    public SignedInfoType createSignedInfoType() {
        return new SignedInfoType();
    }

    /**
     * Create an instance of {@link RequestedAttributeType }
     * 
     */
    public RequestedAttributeType createRequestedAttributeType() {
        return new RequestedAttributeType();
    }

    /**
     * Create an instance of {@link OrganizationType }
     * 
     */
    public OrganizationType createOrganizationType() {
        return new OrganizationType();
    }

    /**
     * Create an instance of {@link ProxyRestrictionType }
     * 
     */
    public ProxyRestrictionType createProxyRestrictionType() {
        return new ProxyRestrictionType();
    }

    /**
     * Create an instance of {@link AttributeConsumingServiceType }
     * 
     */
    public AttributeConsumingServiceType createAttributeConsumingServiceType() {
        return new AttributeConsumingServiceType();
    }

    /**
     * Create an instance of {@link SignatureValueType }
     * 
     */
    public SignatureValueType createSignatureValueType() {
        return new SignatureValueType();
    }

    /**
     * Create an instance of {@link SubjectConfirmationType }
     * 
     */
    public SubjectConfirmationType createSubjectConfirmationType() {
        return new SubjectConfirmationType();
    }

    /**
     * Create an instance of {@link TransformsType }
     * 
     */
    public TransformsType createTransformsType() {
        return new TransformsType();
    }

    /**
     * Create an instance of {@link AssertionType }
     * 
     */
    public AssertionType createAssertionType() {
        return new AssertionType();
    }

    /**
     * Create an instance of {@link ManifestType }
     * 
     */
    public ManifestType createManifestType() {
        return new ManifestType();
    }

    /**
     * Create an instance of {@link DigestMethodType }
     * 
     */
    public DigestMethodType createDigestMethodType() {
        return new DigestMethodType();
    }

    /**
     * Create an instance of {@link NameIDType }
     * 
     */
    public NameIDType createNameIDType() {
        return new NameIDType();
    }

    /**
     * Create an instance of {@link SignaturePropertiesType }
     * 
     */
    public SignaturePropertiesType createSignaturePropertiesType() {
        return new SignaturePropertiesType();
    }

    /**
     * Create an instance of {@link AffiliationDescriptorType }
     * 
     */
    public AffiliationDescriptorType createAffiliationDescriptorType() {
        return new AffiliationDescriptorType();
    }

    /**
     * Create an instance of {@link IndexedEndpointType }
     * 
     */
    public IndexedEndpointType createIndexedEndpointType() {
        return new IndexedEndpointType();
    }

    /**
     * Create an instance of {@link AudienceRestrictionType }
     * 
     */
    public AudienceRestrictionType createAudienceRestrictionType() {
        return new AudienceRestrictionType();
    }

    /**
     * Create an instance of {@link EntityDescriptorType }
     * 
     */
    public EntityDescriptorType createEntityDescriptorType() {
        return new EntityDescriptorType();
    }

    /**
     * Create an instance of {@link SubjectType }
     * 
     */
    public SubjectType createSubjectType() {
        return new SubjectType();
    }

    /**
     * Create an instance of {@link ContactType }
     * 
     */
    public ContactType createContactType() {
        return new ContactType();
    }

    /**
     * Create an instance of {@link LocalizedNameType }
     * 
     */
    public LocalizedNameType createLocalizedNameType() {
        return new LocalizedNameType();
    }

    /**
     * Create an instance of {@link X509IssuerSerialType }
     * 
     */
    public X509IssuerSerialType createX509IssuerSerialType() {
        return new X509IssuerSerialType();
    }

    /**
     * Create an instance of {@link KeyInfoType }
     * 
     */
    public KeyInfoType createKeyInfoType() {
        return new KeyInfoType();
    }

    /**
     * Create an instance of {@link EncryptionPropertiesType }
     * 
     */
    public EncryptionPropertiesType createEncryptionPropertiesType() {
        return new EncryptionPropertiesType();
    }

    /**
     * Create an instance of {@link DSAKeyValueType }
     * 
     */
    public DSAKeyValueType createDSAKeyValueType() {
        return new DSAKeyValueType();
    }

    /**
     * Create an instance of {@link X509DataType }
     * 
     */
    public X509DataType createX509DataType() {
        return new X509DataType();
    }

    /**
     * Create an instance of {@link CipherDataType }
     * 
     */
    public CipherDataType createCipherDataType() {
        return new CipherDataType();
    }

    /**
     * Create an instance of {@link AuthnAuthorityDescriptorType }
     * 
     */
    public AuthnAuthorityDescriptorType createAuthnAuthorityDescriptorType() {
        return new AuthnAuthorityDescriptorType();
    }

    /**
     * Create an instance of {@link PGPDataType }
     * 
     */
    public PGPDataType createPGPDataType() {
        return new PGPDataType();
    }

    /**
     * Create an instance of {@link EncryptedDataType }
     * 
     */
    public EncryptedDataType createEncryptedDataType() {
        return new EncryptedDataType();
    }

    /**
     * Create an instance of {@link ObjectType }
     * 
     */
    public ObjectType createObjectType() {
        return new ObjectType();
    }

    /**
     * Create an instance of {@link EncryptedElementType }
     * 
     */
    public EncryptedElementType createEncryptedElementType() {
        return new EncryptedElementType();
    }

    /**
     * Create an instance of {@link SubjectConfirmationDataType }
     * 
     */
    public SubjectConfirmationDataType createSubjectConfirmationDataType() {
        return new SubjectConfirmationDataType();
    }

    /**
     * Create an instance of {@link SPSSODescriptorType }
     * 
     */
    public SPSSODescriptorType createSPSSODescriptorType() {
        return new SPSSODescriptorType();
    }

    /**
     * Create an instance of {@link KeyValueType }
     * 
     */
    public KeyValueType createKeyValueType() {
        return new KeyValueType();
    }

    /**
     * Create an instance of {@link CanonicalizationMethodType }
     * 
     */
    public CanonicalizationMethodType createCanonicalizationMethodType() {
        return new CanonicalizationMethodType();
    }

    /**
     * Create an instance of {@link ActionType }
     * 
     */
    public ActionType createActionType() {
        return new ActionType();
    }

    /**
     * Create an instance of {@link EncryptionMethodType }
     * 
     */
    public EncryptionMethodType createEncryptionMethodType() {
        return new EncryptionMethodType();
    }

    /**
     * Create an instance of {@link LocalizedURIType }
     * 
     */
    public LocalizedURIType createLocalizedURIType() {
        return new LocalizedURIType();
    }

    /**
     * Create an instance of {@link SignaturePropertyType }
     * 
     */
    public SignaturePropertyType createSignaturePropertyType() {
        return new SignaturePropertyType();
    }

    /**
     * Create an instance of {@link CipherReferenceType }
     * 
     */
    public CipherReferenceType createCipherReferenceType() {
        return new CipherReferenceType();
    }

    /**
     * Create an instance of {@link ReferenceType }
     * 
     */
    public ReferenceType createReferenceType() {
        return new ReferenceType();
    }

    /**
     * Create an instance of {@link TransformsType2 }
     * 
     */
    public TransformsType2 createTransformsType2() {
        return new TransformsType2();
    }

    /**
     * Create an instance of {@link AttributeType }
     * 
     */
    public AttributeType createAttributeType() {
        return new AttributeType();
    }

    /**
     * Create an instance of {@link SignatureType }
     * 
     */
    public SignatureType createSignatureType() {
        return new SignatureType();
    }

    /**
     * Create an instance of {@link SignatureMethodType }
     * 
     */
    public SignatureMethodType createSignatureMethodType() {
        return new SignatureMethodType();
    }

    /**
     * Create an instance of {@link AuthnContextType }
     * 
     */
    public AuthnContextType createAuthnContextType() {
        return new AuthnContextType();
    }

    /**
     * Create an instance of {@link RSAKeyValueType }
     * 
     */
    public RSAKeyValueType createRSAKeyValueType() {
        return new RSAKeyValueType();
    }

    /**
     * Create an instance of {@link AuthzDecisionStatementType }
     * 
     */
    public AuthzDecisionStatementType createAuthzDecisionStatementType() {
        return new AuthzDecisionStatementType();
    }

    /**
     * Create an instance of {@link ReferenceType2 }
     * 
     */
    public ReferenceType2 createReferenceType2() {
        return new ReferenceType2();
    }

    /**
     * Create an instance of {@link AdditionalMetadataLocationType }
     * 
     */
    public AdditionalMetadataLocationType createAdditionalMetadataLocationType() {
        return new AdditionalMetadataLocationType();
    }

    /**
     * Create an instance of {@link KeyDescriptorType }
     * 
     */
    public KeyDescriptorType createKeyDescriptorType() {
        return new KeyDescriptorType();
    }

    /**
     * Create an instance of {@link PDPDescriptorType }
     * 
     */
    public PDPDescriptorType createPDPDescriptorType() {
        return new PDPDescriptorType();
    }

    /**
     * Create an instance of {@link SPKIDataType }
     * 
     */
    public SPKIDataType createSPKIDataType() {
        return new SPKIDataType();
    }

    /**
     * Create an instance of {@link IDPSSODescriptorType }
     * 
     */
    public IDPSSODescriptorType createIDPSSODescriptorType() {
        return new IDPSSODescriptorType();
    }

    /**
     * Create an instance of {@link AttributeStatementType }
     * 
     */
    public AttributeStatementType createAttributeStatementType() {
        return new AttributeStatementType();
    }

    /**
     * Create an instance of {@link ExtensionsType }
     * 
     */
    public ExtensionsType createExtensionsType() {
        return new ExtensionsType();
    }

    /**
     * Create an instance of {@link AgreementMethodType }
     * 
     */
    public AgreementMethodType createAgreementMethodType() {
        return new AgreementMethodType();
    }

    /**
     * Create an instance of {@link RetrievalMethodType }
     * 
     */
    public RetrievalMethodType createRetrievalMethodType() {
        return new RetrievalMethodType();
    }

    /**
     * Create an instance of {@link AdviceType }
     * 
     */
    public AdviceType createAdviceType() {
        return new AdviceType();
    }

    /**
     * Create an instance of {@link EndpointType }
     * 
     */
    public EndpointType createEndpointType() {
        return new EndpointType();
    }

    /**
     * Create an instance of {@link AttributeAuthorityDescriptorType }
     * 
     */
    public AttributeAuthorityDescriptorType createAttributeAuthorityDescriptorType() {
        return new AttributeAuthorityDescriptorType();
    }

    /**
     * Create an instance of {@link EncryptionPropertyType }
     * 
     */
    public EncryptionPropertyType createEncryptionPropertyType() {
        return new EncryptionPropertyType();
    }

    /**
     * Create an instance of {@link EncryptedKeyType }
     * 
     */
    public EncryptedKeyType createEncryptedKeyType() {
        return new EncryptedKeyType();
    }

    /**
     * Create an instance of {@link EvidenceType }
     * 
     */
    public EvidenceType createEvidenceType() {
        return new EvidenceType();
    }

    /**
     * Create an instance of {@link ConditionsType }
     * 
     */
    public ConditionsType createConditionsType() {
        return new ConditionsType();
    }

    /**
     * Create an instance of {@link KeyInfoConfirmationDataType }
     * 
     */
    public KeyInfoConfirmationDataType createKeyInfoConfirmationDataType() {
        return new KeyInfoConfirmationDataType();
    }

    /**
     * Create an instance of {@link TransformType }
     * 
     */
    public TransformType createTransformType() {
        return new TransformType();
    }

    /**
     * Create an instance of {@link AuthnStatementType }
     * 
     */
    public AuthnStatementType createAuthnStatementType() {
        return new AuthnStatementType();
    }

    /**
     * Create an instance of {@link ReferenceList }
     * 
     */
    public ReferenceList createReferenceList() {
        return new ReferenceList();
    }

    /**
     * Create an instance of {@link SubjectLocalityType }
     * 
     */
    public SubjectLocalityType createSubjectLocalityType() {
        return new SubjectLocalityType();
    }

    /**
     * Create an instance of {@link OneTimeUseType }
     * 
     */
    public OneTimeUseType createOneTimeUseType() {
        return new OneTimeUseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PGPDataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "PGPData")
    public JAXBElement<PGPDataType> createPGPData(PGPDataType value) {
        return new JAXBElement<PGPDataType>(_PGPData_QNAME, PGPDataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptionPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "EncryptionProperty")
    public JAXBElement<EncryptionPropertyType> createEncryptionProperty(EncryptionPropertyType value) {
        return new JAXBElement<EncryptionPropertyType>(_EncryptionProperty_QNAME, EncryptionPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptedKeyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "EncryptedKey")
    public JAXBElement<EncryptedKeyType> createEncryptedKey(EncryptedKeyType value) {
        return new JAXBElement<EncryptedKeyType>(_EncryptedKey_QNAME, EncryptedKeyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SPSSODescriptorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "SPSSODescriptor")
    public JAXBElement<SPSSODescriptorType> createSPSSODescriptor(SPSSODescriptorType value) {
        return new JAXBElement<SPSSODescriptorType>(_SPSSODescriptor_QNAME, SPSSODescriptorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SPKIDataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SPKIData")
    public JAXBElement<SPKIDataType> createSPKIData(SPKIDataType value) {
        return new JAXBElement<SPKIDataType>(_SPKIData_QNAME, SPKIDataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CanonicalizationMethodType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "CanonicalizationMethod")
    public JAXBElement<CanonicalizationMethodType> createCanonicalizationMethod(CanonicalizationMethodType value) {
        return new JAXBElement<CanonicalizationMethodType>(_CanonicalizationMethod_QNAME, CanonicalizationMethodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssertionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Assertion")
    public JAXBElement<AssertionType> createAssertion(AssertionType value) {
        return new JAXBElement<AssertionType>(_Assertion_QNAME, AssertionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IDPSSODescriptorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "IDPSSODescriptor")
    public JAXBElement<IDPSSODescriptorType> createIDPSSODescriptor(IDPSSODescriptorType value) {
        return new JAXBElement<IDPSSODescriptorType>(_IDPSSODescriptor_QNAME, IDPSSODescriptorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndpointType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "SingleLogoutService")
    public JAXBElement<EndpointType> createSingleLogoutService(EndpointType value) {
        return new JAXBElement<EndpointType>(_SingleLogoutService_QNAME, EndpointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ManifestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Manifest")
    public JAXBElement<ManifestType> createManifest(ManifestType value) {
        return new JAXBElement<ManifestType>(_Manifest_QNAME, ManifestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransformsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Transforms")
    public JAXBElement<TransformsType> createTransforms(TransformsType value) {
        return new JAXBElement<TransformsType>(_Transforms_QNAME, TransformsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntitiesDescriptorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "EntitiesDescriptor")
    public JAXBElement<EntitiesDescriptorType> createEntitiesDescriptor(EntitiesDescriptorType value) {
        return new JAXBElement<EntitiesDescriptorType>(_EntitiesDescriptor_QNAME, EntitiesDescriptorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndpointType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "AttributeService")
    public JAXBElement<EndpointType> createAttributeService(EndpointType value) {
        return new JAXBElement<EndpointType>(_AttributeService_QNAME, EndpointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestedAttributeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "RequestedAttribute")
    public JAXBElement<RequestedAttributeType> createRequestedAttribute(RequestedAttributeType value) {
        return new JAXBElement<RequestedAttributeType>(_RequestedAttribute_QNAME, RequestedAttributeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthnContextType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AuthnContext")
    public JAXBElement<AuthnContextType> createAuthnContext(AuthnContextType value) {
        return new JAXBElement<AuthnContextType>(_AuthnContext_QNAME, AuthnContextType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignatureMethodType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SignatureMethod")
    public JAXBElement<SignatureMethodType> createSignatureMethod(SignatureMethodType value) {
        return new JAXBElement<SignatureMethodType>(_SignatureMethod_QNAME, SignatureMethodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndpointType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "AuthzService")
    public JAXBElement<EndpointType> createAuthzService(EndpointType value) {
        return new JAXBElement<EndpointType>(_AuthzService_QNAME, EndpointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeyInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "KeyInfo")
    public JAXBElement<KeyInfoType> createKeyInfo(KeyInfoType value) {
        return new JAXBElement<KeyInfoType>(_KeyInfo_QNAME, KeyInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndpointType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "AuthnQueryService")
    public JAXBElement<EndpointType> createAuthnQueryService(EndpointType value) {
        return new JAXBElement<EndpointType>(_AuthnQueryService_QNAME, EndpointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DigestMethodType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "DigestMethod")
    public JAXBElement<DigestMethodType> createDigestMethod(DigestMethodType value) {
        return new JAXBElement<DigestMethodType>(_DigestMethod_QNAME, DigestMethodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "MgmtData")
    public JAXBElement<String> createMgmtData(String value) {
        return new JAXBElement<String>(_MgmtData_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndpointType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "ManageNameIDService")
    public JAXBElement<EndpointType> createManageNameIDService(EndpointType value) {
        return new JAXBElement<EndpointType>(_ManageNameIDService_QNAME, EndpointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptedElementType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "EncryptedID")
    public JAXBElement<EncryptedElementType> createEncryptedID(EncryptedElementType value) {
        return new JAXBElement<EncryptedElementType>(_EncryptedID_QNAME, EncryptedElementType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "SurName")
    public JAXBElement<String> createSurName(String value) {
        return new JAXBElement<String>(_SurName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NameIDType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Issuer")
    public JAXBElement<NameIDType> createIssuer(NameIDType value) {
        return new JAXBElement<NameIDType>(_Issuer_QNAME, NameIDType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthnAuthorityDescriptorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "AuthnAuthorityDescriptor")
    public JAXBElement<AuthnAuthorityDescriptorType> createAuthnAuthorityDescriptor(AuthnAuthorityDescriptorType value) {
        return new JAXBElement<AuthnAuthorityDescriptorType>(_AuthnAuthorityDescriptor_QNAME, AuthnAuthorityDescriptorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NameIDType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "NameID")
    public JAXBElement<NameIDType> createNameID(NameIDType value) {
        return new JAXBElement<NameIDType>(_NameID_QNAME, NameIDType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdditionalMetadataLocationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "AdditionalMetadataLocation")
    public JAXBElement<AdditionalMetadataLocationType> createAdditionalMetadataLocation(AdditionalMetadataLocationType value) {
        return new JAXBElement<AdditionalMetadataLocationType>(_AdditionalMetadataLocation_QNAME, AdditionalMetadataLocationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "Company")
    public JAXBElement<String> createCompany(String value) {
        return new JAXBElement<String>(_Company_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttributeConsumingServiceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "AttributeConsumingService")
    public JAXBElement<AttributeConsumingServiceType> createAttributeConsumingService(AttributeConsumingServiceType value) {
        return new JAXBElement<AttributeConsumingServiceType>(_AttributeConsumingService_QNAME, AttributeConsumingServiceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocalizedNameType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "OrganizationDisplayName")
    public JAXBElement<LocalizedNameType> createOrganizationDisplayName(LocalizedNameType value) {
        return new JAXBElement<LocalizedNameType>(_OrganizationDisplayName_QNAME, LocalizedNameType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtensionsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "Extensions")
    public JAXBElement<ExtensionsType> createExtensions(ExtensionsType value) {
        return new JAXBElement<ExtensionsType>(_Extensions_QNAME, ExtensionsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocalizedNameType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "ServiceDescription")
    public JAXBElement<LocalizedNameType> createServiceDescription(LocalizedNameType value) {
        return new JAXBElement<LocalizedNameType>(_ServiceDescription_QNAME, LocalizedNameType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptedElementType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "EncryptedAssertion")
    public JAXBElement<EncryptedElementType> createEncryptedAssertion(EncryptedElementType value) {
        return new JAXBElement<EncryptedElementType>(_EncryptedAssertion_QNAME, EncryptedElementType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CipherDataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "CipherData")
    public JAXBElement<CipherDataType> createCipherData(CipherDataType value) {
        return new JAXBElement<CipherDataType>(_CipherData_QNAME, CipherDataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocalizedURIType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "OrganizationURL")
    public JAXBElement<LocalizedURIType> createOrganizationURL(LocalizedURIType value) {
        return new JAXBElement<LocalizedURIType>(_OrganizationURL_QNAME, LocalizedURIType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignedInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SignedInfo")
    public JAXBElement<SignedInfoType> createSignedInfo(SignedInfoType value) {
        return new JAXBElement<SignedInfoType>(_SignedInfo_QNAME, SignedInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectLocalityType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "SubjectLocality")
    public JAXBElement<SubjectLocalityType> createSubjectLocality(SubjectLocalityType value) {
        return new JAXBElement<SubjectLocalityType>(_SubjectLocality_QNAME, SubjectLocalityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObjectType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Object")
    public JAXBElement<ObjectType> createObject(ObjectType value) {
        return new JAXBElement<ObjectType>(_Object_QNAME, ObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvidenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Evidence")
    public JAXBElement<EvidenceType> createEvidence(EvidenceType value) {
        return new JAXBElement<EvidenceType>(_Evidence_QNAME, EvidenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatementAbstractType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Statement")
    public JAXBElement<StatementAbstractType> createStatement(StatementAbstractType value) {
        return new JAXBElement<StatementAbstractType>(_Statement_QNAME, StatementAbstractType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link X509DataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "X509Data")
    public JAXBElement<X509DataType> createX509Data(X509DataType value) {
        return new JAXBElement<X509DataType>(_X509Data_QNAME, X509DataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConditionsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Conditions")
    public JAXBElement<ConditionsType> createConditions(ConditionsType value) {
        return new JAXBElement<ConditionsType>(_Conditions_QNAME, ConditionsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AttributeValue")
    public JAXBElement<Object> createAttributeValue(Object value) {
        return new JAXBElement<Object>(_AttributeValue_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AssertionURIRef")
    public JAXBElement<String> createAssertionURIRef(String value) {
        return new JAXBElement<String>(_AssertionURIRef_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrganizationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "Organization")
    public JAXBElement<OrganizationType> createOrganization(OrganizationType value) {
        return new JAXBElement<OrganizationType>(_Organization_QNAME, OrganizationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AssertionIDRef")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssertionIDRef(String value) {
        return new JAXBElement<String>(_AssertionIDRef_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptionMethodType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "EncryptionMethod")
    public JAXBElement<EncryptionMethodType> createEncryptionMethod(EncryptionMethodType value) {
        return new JAXBElement<EncryptionMethodType>(_EncryptionMethod_QNAME, EncryptionMethodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignaturePropertiesType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SignatureProperties")
    public JAXBElement<SignaturePropertiesType> createSignatureProperties(SignaturePropertiesType value) {
        return new JAXBElement<SignaturePropertiesType>(_SignatureProperties_QNAME, SignaturePropertiesType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProxyRestrictionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "ProxyRestriction")
    public JAXBElement<ProxyRestrictionType> createProxyRestriction(ProxyRestrictionType value) {
        return new JAXBElement<ProxyRestrictionType>(_ProxyRestriction_QNAME, ProxyRestrictionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "KeyName")
    public JAXBElement<String> createKeyName(String value) {
        return new JAXBElement<String>(_KeyName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "AttributeProfile")
    public JAXBElement<String> createAttributeProfile(String value) {
        return new JAXBElement<String>(_AttributeProfile_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndpointType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "SingleSignOnService")
    public JAXBElement<EndpointType> createSingleSignOnService(EndpointType value) {
        return new JAXBElement<EndpointType>(_SingleSignOnService_QNAME, EndpointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "TelephoneNumber")
    public JAXBElement<String> createTelephoneNumber(String value) {
        return new JAXBElement<String>(_TelephoneNumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AffiliationDescriptorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "AffiliationDescriptor")
    public JAXBElement<AffiliationDescriptorType> createAffiliationDescriptor(AffiliationDescriptorType value) {
        return new JAXBElement<AffiliationDescriptorType>(_AffiliationDescriptor_QNAME, AffiliationDescriptorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContactType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "ContactPerson")
    public JAXBElement<ContactType> createContactPerson(ContactType value) {
        return new JAXBElement<ContactType>(_ContactPerson_QNAME, ContactType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrievalMethodType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "RetrievalMethod")
    public JAXBElement<RetrievalMethodType> createRetrievalMethod(RetrievalMethodType value) {
        return new JAXBElement<RetrievalMethodType>(_RetrievalMethod_QNAME, RetrievalMethodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CipherReferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "CipherReference")
    public JAXBElement<CipherReferenceType> createCipherReference(CipherReferenceType value) {
        return new JAXBElement<CipherReferenceType>(_CipherReference_QNAME, CipherReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AudienceRestrictionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AudienceRestriction")
    public JAXBElement<AudienceRestrictionType> createAudienceRestriction(AudienceRestrictionType value) {
        return new JAXBElement<AudienceRestrictionType>(_AudienceRestriction_QNAME, AudienceRestrictionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignaturePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SignatureProperty")
    public JAXBElement<SignaturePropertyType> createSignatureProperty(SignaturePropertyType value) {
        return new JAXBElement<SignaturePropertyType>(_SignatureProperty_QNAME, SignaturePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntityDescriptorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "EntityDescriptor")
    public JAXBElement<EntityDescriptorType> createEntityDescriptor(EntityDescriptorType value) {
        return new JAXBElement<EntityDescriptorType>(_EntityDescriptor_QNAME, EntityDescriptorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConditionAbstractType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Condition")
    public JAXBElement<ConditionAbstractType> createCondition(ConditionAbstractType value) {
        return new JAXBElement<ConditionAbstractType>(_Condition_QNAME, ConditionAbstractType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthnStatementType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AuthnStatement")
    public JAXBElement<AuthnStatementType> createAuthnStatement(AuthnStatementType value) {
        return new JAXBElement<AuthnStatementType>(_AuthnStatement_QNAME, AuthnStatementType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectConfirmationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "SubjectConfirmation")
    public JAXBElement<SubjectConfirmationType> createSubjectConfirmation(SubjectConfirmationType value) {
        return new JAXBElement<SubjectConfirmationType>(_SubjectConfirmation_QNAME, SubjectConfirmationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptedDataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "EncryptedData")
    public JAXBElement<EncryptedDataType> createEncryptedData(EncryptedDataType value) {
        return new JAXBElement<EncryptedDataType>(_EncryptedData_QNAME, EncryptedDataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "AffiliateMember")
    public JAXBElement<String> createAffiliateMember(String value) {
        return new JAXBElement<String>(_AffiliateMember_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "GivenName")
    public JAXBElement<String> createGivenName(String value) {
        return new JAXBElement<String>(_GivenName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AuthenticatingAuthority")
    public JAXBElement<String> createAuthenticatingAuthority(String value) {
        return new JAXBElement<String>(_AuthenticatingAuthority_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Reference")
    public JAXBElement<ReferenceType> createReference(ReferenceType value) {
        return new JAXBElement<ReferenceType>(_Reference_QNAME, ReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocalizedNameType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "ServiceName")
    public JAXBElement<LocalizedNameType> createServiceName(LocalizedNameType value) {
        return new JAXBElement<LocalizedNameType>(_ServiceName_QNAME, LocalizedNameType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RoleDescriptorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "RoleDescriptor")
    public JAXBElement<RoleDescriptorType> createRoleDescriptor(RoleDescriptorType value) {
        return new JAXBElement<RoleDescriptorType>(_RoleDescriptor_QNAME, RoleDescriptorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttributeAuthorityDescriptorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "AttributeAuthorityDescriptor")
    public JAXBElement<AttributeAuthorityDescriptorType> createAttributeAuthorityDescriptor(AttributeAuthorityDescriptorType value) {
        return new JAXBElement<AttributeAuthorityDescriptorType>(_AttributeAuthorityDescriptor_QNAME, AttributeAuthorityDescriptorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttributeStatementType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AttributeStatement")
    public JAXBElement<AttributeStatementType> createAttributeStatement(AttributeStatementType value) {
        return new JAXBElement<AttributeStatementType>(_AttributeStatement_QNAME, AttributeStatementType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RSAKeyValueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "RSAKeyValue")
    public JAXBElement<RSAKeyValueType> createRSAKeyValue(RSAKeyValueType value) {
        return new JAXBElement<RSAKeyValueType>(_RSAKeyValue_QNAME, RSAKeyValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttributeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Attribute")
    public JAXBElement<AttributeType> createAttribute(AttributeType value) {
        return new JAXBElement<AttributeType>(_Attribute_QNAME, AttributeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AuthnContextDecl")
    public JAXBElement<Object> createAuthnContextDecl(Object value) {
        return new JAXBElement<Object>(_AuthnContextDecl_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Subject")
    public JAXBElement<SubjectType> createSubject(SubjectType value) {
        return new JAXBElement<SubjectType>(_Subject_QNAME, SubjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignatureType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Signature")
    public JAXBElement<SignatureType> createSignature(SignatureType value) {
        return new JAXBElement<SignatureType>(_Signature_QNAME, SignatureType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DSAKeyValueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "DSAKeyValue")
    public JAXBElement<DSAKeyValueType> createDSAKeyValue(DSAKeyValueType value) {
        return new JAXBElement<DSAKeyValueType>(_DSAKeyValue_QNAME, DSAKeyValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PDPDescriptorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "PDPDescriptor")
    public JAXBElement<PDPDescriptorType> createPDPDescriptor(PDPDescriptorType value) {
        return new JAXBElement<PDPDescriptorType>(_PDPDescriptor_QNAME, PDPDescriptorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocalizedNameType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "OrganizationName")
    public JAXBElement<LocalizedNameType> createOrganizationName(LocalizedNameType value) {
        return new JAXBElement<LocalizedNameType>(_OrganizationName_QNAME, LocalizedNameType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdviceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Advice")
    public JAXBElement<AdviceType> createAdvice(AdviceType value) {
        return new JAXBElement<AdviceType>(_Advice_QNAME, AdviceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "NameIDFormat")
    public JAXBElement<String> createNameIDFormat(String value) {
        return new JAXBElement<String>(_NameIDFormat_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignatureValueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SignatureValue")
    public JAXBElement<SignatureValueType> createSignatureValue(SignatureValueType value) {
        return new JAXBElement<SignatureValueType>(_SignatureValue_QNAME, SignatureValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransformType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Transform")
    public JAXBElement<TransformType> createTransform(TransformType value) {
        return new JAXBElement<TransformType>(_Transform_QNAME, TransformType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptionPropertiesType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "EncryptionProperties")
    public JAXBElement<EncryptionPropertiesType> createEncryptionProperties(EncryptionPropertiesType value) {
        return new JAXBElement<EncryptionPropertiesType>(_EncryptionProperties_QNAME, EncryptionPropertiesType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndpointType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "NameIDMappingService")
    public JAXBElement<EndpointType> createNameIDMappingService(EndpointType value) {
        return new JAXBElement<EndpointType>(_NameIDMappingService_QNAME, EndpointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IndexedEndpointType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "ArtifactResolutionService")
    public JAXBElement<IndexedEndpointType> createArtifactResolutionService(IndexedEndpointType value) {
        return new JAXBElement<IndexedEndpointType>(_ArtifactResolutionService_QNAME, IndexedEndpointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IndexedEndpointType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "AssertionConsumerService")
    public JAXBElement<IndexedEndpointType> createAssertionConsumerService(IndexedEndpointType value) {
        return new JAXBElement<IndexedEndpointType>(_AssertionConsumerService_QNAME, IndexedEndpointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectConfirmationDataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "SubjectConfirmationData")
    public JAXBElement<SubjectConfirmationDataType> createSubjectConfirmationData(SubjectConfirmationDataType value) {
        return new JAXBElement<SubjectConfirmationDataType>(_SubjectConfirmationData_QNAME, SubjectConfirmationDataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgreementMethodType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "AgreementMethod")
    public JAXBElement<AgreementMethodType> createAgreementMethod(AgreementMethodType value) {
        return new JAXBElement<AgreementMethodType>(_AgreementMethod_QNAME, AgreementMethodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "DigestValue")
    public JAXBElement<byte[]> createDigestValue(byte[] value) {
        return new JAXBElement<byte[]>(_DigestValue_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AuthnContextClassRef")
    public JAXBElement<String> createAuthnContextClassRef(String value) {
        return new JAXBElement<String>(_AuthnContextClassRef_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Audience")
    public JAXBElement<String> createAudience(String value) {
        return new JAXBElement<String>(_Audience_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeyDescriptorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "KeyDescriptor")
    public JAXBElement<KeyDescriptorType> createKeyDescriptor(KeyDescriptorType value) {
        return new JAXBElement<KeyDescriptorType>(_KeyDescriptor_QNAME, KeyDescriptorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "Action")
    public JAXBElement<ActionType> createAction(ActionType value) {
        return new JAXBElement<ActionType>(_Action_QNAME, ActionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeyValueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "KeyValue")
    public JAXBElement<KeyValueType> createKeyValue(KeyValueType value) {
        return new JAXBElement<KeyValueType>(_KeyValue_QNAME, KeyValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthzDecisionStatementType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AuthzDecisionStatement")
    public JAXBElement<AuthzDecisionStatementType> createAuthzDecisionStatement(AuthzDecisionStatementType value) {
        return new JAXBElement<AuthzDecisionStatementType>(_AuthzDecisionStatement_QNAME, AuthzDecisionStatementType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "AuthnContextDeclRef")
    public JAXBElement<String> createAuthnContextDeclRef(String value) {
        return new JAXBElement<String>(_AuthnContextDeclRef_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OneTimeUseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "OneTimeUse")
    public JAXBElement<OneTimeUseType> createOneTimeUse(OneTimeUseType value) {
        return new JAXBElement<OneTimeUseType>(_OneTimeUse_QNAME, OneTimeUseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptedElementType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "EncryptedAttribute")
    public JAXBElement<EncryptedElementType> createEncryptedAttribute(EncryptedElementType value) {
        return new JAXBElement<EncryptedElementType>(_EncryptedAttribute_QNAME, EncryptedElementType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "EmailAddress")
    public JAXBElement<String> createEmailAddress(String value) {
        return new JAXBElement<String>(_EmailAddress_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EndpointType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:metadata", name = "AssertionIDRequestService")
    public JAXBElement<EndpointType> createAssertionIDRequestService(EndpointType value) {
        return new JAXBElement<EndpointType>(_AssertionIDRequestService_QNAME, EndpointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BaseIDAbstractType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SAML:2.0:assertion", name = "BaseID")
    public JAXBElement<BaseIDAbstractType> createBaseID(BaseIDAbstractType value) {
        return new JAXBElement<BaseIDAbstractType>(_BaseID_QNAME, BaseIDAbstractType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "PGPKeyID", scope = PGPDataType.class)
    public JAXBElement<byte[]> createPGPDataTypePGPKeyID(byte[] value) {
        return new JAXBElement<byte[]>(_PGPDataTypePGPKeyID_QNAME, byte[].class, PGPDataType.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "PGPKeyPacket", scope = PGPDataType.class)
    public JAXBElement<byte[]> createPGPDataTypePGPKeyPacket(byte[] value) {
        return new JAXBElement<byte[]>(_PGPDataTypePGPKeyPacket_QNAME, byte[].class, PGPDataType.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SPKISexp", scope = SPKIDataType.class)
    public JAXBElement<byte[]> createSPKIDataTypeSPKISexp(byte[] value) {
        return new JAXBElement<byte[]>(_SPKIDataTypeSPKISexp_QNAME, byte[].class, SPKIDataType.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "KeySize", scope = EncryptionMethodType.class)
    public JAXBElement<BigInteger> createEncryptionMethodTypeKeySize(BigInteger value) {
        return new JAXBElement<BigInteger>(_EncryptionMethodTypeKeySize_QNAME, BigInteger.class, EncryptionMethodType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "OAEPparams", scope = EncryptionMethodType.class)
    public JAXBElement<byte[]> createEncryptionMethodTypeOAEPparams(byte[] value) {
        return new JAXBElement<byte[]>(_EncryptionMethodTypeOAEPparams_QNAME, byte[].class, EncryptionMethodType.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "XPath", scope = TransformType.class)
    public JAXBElement<String> createTransformTypeXPath(String value) {
        return new JAXBElement<String>(_TransformTypeXPath_QNAME, String.class, TransformType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeyInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "OriginatorKeyInfo", scope = AgreementMethodType.class)
    public JAXBElement<KeyInfoType> createAgreementMethodTypeOriginatorKeyInfo(KeyInfoType value) {
        return new JAXBElement<KeyInfoType>(_AgreementMethodTypeOriginatorKeyInfo_QNAME, KeyInfoType.class, AgreementMethodType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeyInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "RecipientKeyInfo", scope = AgreementMethodType.class)
    public JAXBElement<KeyInfoType> createAgreementMethodTypeRecipientKeyInfo(KeyInfoType value) {
        return new JAXBElement<KeyInfoType>(_AgreementMethodTypeRecipientKeyInfo_QNAME, KeyInfoType.class, AgreementMethodType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "KA-Nonce", scope = AgreementMethodType.class)
    public JAXBElement<byte[]> createAgreementMethodTypeKANonce(byte[] value) {
        return new JAXBElement<byte[]>(_AgreementMethodTypeKANonce_QNAME, byte[].class, AgreementMethodType.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "HMACOutputLength", scope = SignatureMethodType.class)
    public JAXBElement<BigInteger> createSignatureMethodTypeHMACOutputLength(BigInteger value) {
        return new JAXBElement<BigInteger>(_SignatureMethodTypeHMACOutputLength_QNAME, BigInteger.class, SignatureMethodType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceType2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "DataReference", scope = ReferenceList.class)
    public JAXBElement<ReferenceType2> createReferenceListDataReference(ReferenceType2 value) {
        return new JAXBElement<ReferenceType2>(_ReferenceListDataReference_QNAME, ReferenceType2 .class, ReferenceList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceType2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "KeyReference", scope = ReferenceList.class)
    public JAXBElement<ReferenceType2> createReferenceListKeyReference(ReferenceType2 value) {
        return new JAXBElement<ReferenceType2>(_ReferenceListKeyReference_QNAME, ReferenceType2 .class, ReferenceList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link X509IssuerSerialType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "X509IssuerSerial", scope = X509DataType.class)
    public JAXBElement<X509IssuerSerialType> createX509DataTypeX509IssuerSerial(X509IssuerSerialType value) {
        return new JAXBElement<X509IssuerSerialType>(_X509DataTypeX509IssuerSerial_QNAME, X509IssuerSerialType.class, X509DataType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "X509Certificate", scope = X509DataType.class)
    public JAXBElement<byte[]> createX509DataTypeX509Certificate(byte[] value) {
        return new JAXBElement<byte[]>(_X509DataTypeX509Certificate_QNAME, byte[].class, X509DataType.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "X509SKI", scope = X509DataType.class)
    public JAXBElement<byte[]> createX509DataTypeX509SKI(byte[] value) {
        return new JAXBElement<byte[]>(_X509DataTypeX509SKI_QNAME, byte[].class, X509DataType.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "X509SubjectName", scope = X509DataType.class)
    public JAXBElement<String> createX509DataTypeX509SubjectName(String value) {
        return new JAXBElement<String>(_X509DataTypeX509SubjectName_QNAME, String.class, X509DataType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "X509CRL", scope = X509DataType.class)
    public JAXBElement<byte[]> createX509DataTypeX509CRL(byte[] value) {
        return new JAXBElement<byte[]>(_X509DataTypeX509CRL_QNAME, byte[].class, X509DataType.class, ((byte[]) value));
    }

}
