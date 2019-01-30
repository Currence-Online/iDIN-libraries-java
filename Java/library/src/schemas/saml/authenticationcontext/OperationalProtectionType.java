
package schemas.saml.authenticationcontext;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OperationalProtectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OperationalProtectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:ac}SecurityAudit" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:ac}DeactivationCallCenter" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:ac}Extension" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperationalProtectionType", propOrder = {
    "securityAudit",
    "deactivationCallCenter",
    "extension"
})
public class OperationalProtectionType {

    @XmlElement(name = "SecurityAudit")
    protected SecurityAuditType securityAudit;
    @XmlElement(name = "DeactivationCallCenter")
    protected ExtensionOnlyType deactivationCallCenter;
    @XmlElement(name = "Extension")
    protected List<ExtensionType> extension;

    /**
     * Gets the value of the securityAudit property.
     * 
     * @return
     *     possible object is
     *     {@link SecurityAuditType }
     *     
     */
    public SecurityAuditType getSecurityAudit() {
        return securityAudit;
    }

    /**
     * Sets the value of the securityAudit property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityAuditType }
     *     
     */
    public void setSecurityAudit(SecurityAuditType value) {
        this.securityAudit = value;
    }

    /**
     * Gets the value of the deactivationCallCenter property.
     * 
     * @return
     *     possible object is
     *     {@link ExtensionOnlyType }
     *     
     */
    public ExtensionOnlyType getDeactivationCallCenter() {
        return deactivationCallCenter;
    }

    /**
     * Sets the value of the deactivationCallCenter property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtensionOnlyType }
     *     
     */
    public void setDeactivationCallCenter(ExtensionOnlyType value) {
        this.deactivationCallCenter = value;
    }

    /**
     * Gets the value of the extension property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extension property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtension().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtensionType }
     * 
     * 
     */
    public List<ExtensionType> getExtension() {
        if (extension == null) {
            extension = new ArrayList<ExtensionType>();
        }
        return this.extension;
    }

}
