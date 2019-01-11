
package schemas.saml.authenticationcontext;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KeyStorageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KeyStorageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="medium" use="required" type="{urn:oasis:names:tc:SAML:2.0:ac}mediumType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KeyStorageType")
public class KeyStorageType {

    @XmlAttribute(required = true)
    protected MediumType medium;

    /**
     * Gets the value of the medium property.
     * 
     * @return
     *     possible object is
     *     {@link MediumType }
     *     
     */
    public MediumType getMedium() {
        return medium;
    }

    /**
     * Sets the value of the medium property.
     * 
     * @param value
     *     allowed object is
     *     {@link MediumType }
     *     
     */
    public void setMedium(MediumType value) {
        this.medium = value;
    }

}
