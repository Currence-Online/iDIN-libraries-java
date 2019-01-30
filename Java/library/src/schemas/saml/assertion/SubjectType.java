
package schemas.saml.assertion;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubjectType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubjectType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;choice>
 *             &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}BaseID"/>
 *             &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}NameID"/>
 *             &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}EncryptedID"/>
 *           &lt;/choice>
 *           &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}SubjectConfirmation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}SubjectConfirmation" maxOccurs="unbounded"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectType", propOrder = {
    "content"
})
public class SubjectType {

    @XmlElementRefs({
        @XmlElementRef(name = "BaseID", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = JAXBElement.class),
        @XmlElementRef(name = "SubjectConfirmation", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = JAXBElement.class),
        @XmlElementRef(name = "EncryptedID", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = JAXBElement.class),
        @XmlElementRef(name = "NameID", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = JAXBElement.class)
    })
    protected List<JAXBElement<?>> content;

    /**
     * Gets the rest of the content model. 
     * 
     * <p>
     * You are getting this "catch-all" property because of the following reason: 
     * The field name "SubjectConfirmation" is used by two different parts of a schema. See: 
     * line 87 of file:/D:/Maxcode/BVN%20-%20BankID%20Software%20Libraries/Common/SAML%20Schemas/saml-schema-assertion-2.0.xsd
     * line 85 of file:/D:/Maxcode/BVN%20-%20BankID%20Software%20Libraries/Common/SAML%20Schemas/saml-schema-assertion-2.0.xsd
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
     * {@link JAXBElement }{@code <}{@link EncryptedElementType }{@code >}
     * {@link JAXBElement }{@code <}{@link BaseIDAbstractType }{@code >}
     * {@link JAXBElement }{@code <}{@link NameIDType }{@code >}
     * {@link JAXBElement }{@code <}{@link SubjectConfirmationType }{@code >}
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
