
package schemas.saml.authenticationcontext;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthenticatorBaseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthenticatorBaseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:oasis:names:tc:SAML:2.0:ac}AuthenticatorChoiceGroup"/>
 *         &lt;group ref="{urn:oasis:names:tc:SAML:2.0:ac}AuthenticatorSequenceGroup"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthenticatorBaseType", propOrder = {
    "content"
})
public class AuthenticatorBaseType {

    @XmlElementRefs({
        @XmlElementRef(name = "DigSig", namespace = "urn:oasis:names:tc:SAML:2.0:ac", type = JAXBElement.class),
        @XmlElementRef(name = "RestrictedPassword", namespace = "urn:oasis:names:tc:SAML:2.0:ac", type = JAXBElement.class),
        @XmlElementRef(name = "AsymmetricKeyAgreement", namespace = "urn:oasis:names:tc:SAML:2.0:ac", type = JAXBElement.class),
        @XmlElementRef(name = "PreviousSession", namespace = "urn:oasis:names:tc:SAML:2.0:ac", type = JAXBElement.class),
        @XmlElementRef(name = "SharedSecretChallengeResponse", namespace = "urn:oasis:names:tc:SAML:2.0:ac", type = JAXBElement.class),
        @XmlElementRef(name = "SubscriberLineNumber", namespace = "urn:oasis:names:tc:SAML:2.0:ac", type = JAXBElement.class),
        @XmlElementRef(name = "Extension", namespace = "urn:oasis:names:tc:SAML:2.0:ac", type = JAXBElement.class),
        @XmlElementRef(name = "ResumeSession", namespace = "urn:oasis:names:tc:SAML:2.0:ac", type = JAXBElement.class),
        @XmlElementRef(name = "AsymmetricDecryption", namespace = "urn:oasis:names:tc:SAML:2.0:ac", type = JAXBElement.class),
        @XmlElementRef(name = "IPAddress", namespace = "urn:oasis:names:tc:SAML:2.0:ac", type = JAXBElement.class),
        @XmlElementRef(name = "ZeroKnowledge", namespace = "urn:oasis:names:tc:SAML:2.0:ac", type = JAXBElement.class),
        @XmlElementRef(name = "SharedSecretDynamicPlaintext", namespace = "urn:oasis:names:tc:SAML:2.0:ac", type = JAXBElement.class),
        @XmlElementRef(name = "ComplexAuthenticator", namespace = "urn:oasis:names:tc:SAML:2.0:ac", type = JAXBElement.class),
        @XmlElementRef(name = "Password", namespace = "urn:oasis:names:tc:SAML:2.0:ac", type = JAXBElement.class),
        @XmlElementRef(name = "UserSuffix", namespace = "urn:oasis:names:tc:SAML:2.0:ac", type = JAXBElement.class)
    })
    protected List<JAXBElement<?>> content;

    /**
     * Gets the rest of the content model. 
     * 
     * <p>
     * You are getting this "catch-all" property because of the following reason: 
     * The field name "PreviousSession" is used by two different parts of a schema. See: 
     * line 575 of file:/D:/Maxcode/BVN%20-%20BankID%20Software%20Libraries/Common/SAML%20Schemas/saml-schema-authn-context-types-2.0.xsd
     * line 556 of file:/D:/Maxcode/BVN%20-%20BankID%20Software%20Libraries/Common/SAML%20Schemas/saml-schema-authn-context-types-2.0.xsd
     * <p>
     * To get rid of this property, apply a property customization to one 
     * of both of the following declarations to change their names: 
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link PublicKeyType }{@code >}
     * {@link JAXBElement }{@code <}{@link RestrictedPasswordType }{@code >}
     * {@link JAXBElement }{@code <}{@link PublicKeyType }{@code >}
     * {@link JAXBElement }{@code <}{@link ExtensionOnlyType }{@code >}
     * {@link JAXBElement }{@code <}{@link SharedSecretChallengeResponseType }{@code >}
     * {@link JAXBElement }{@code <}{@link ExtensionOnlyType }{@code >}
     * {@link JAXBElement }{@code <}{@link ExtensionType }{@code >}
     * {@link JAXBElement }{@code <}{@link ExtensionOnlyType }{@code >}
     * {@link JAXBElement }{@code <}{@link PublicKeyType }{@code >}
     * {@link JAXBElement }{@code <}{@link ExtensionOnlyType }{@code >}
     * {@link JAXBElement }{@code <}{@link ExtensionOnlyType }{@code >}
     * {@link JAXBElement }{@code <}{@link ExtensionOnlyType }{@code >}
     * {@link JAXBElement }{@code <}{@link ComplexAuthenticatorType }{@code >}
     * {@link JAXBElement }{@code <}{@link PasswordType }{@code >}
     * {@link JAXBElement }{@code <}{@link ExtensionOnlyType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getContent() {
        if (content == null) {
            content = new ArrayList<JAXBElement<?>>();
        }
        return this.content;
    }

}
