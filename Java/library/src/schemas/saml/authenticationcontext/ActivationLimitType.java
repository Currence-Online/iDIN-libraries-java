
package schemas.saml.authenticationcontext;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActivationLimitType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActivationLimitType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:ac}ActivationLimitDuration"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:ac}ActivationLimitUsages"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:ac}ActivationLimitSession"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActivationLimitType", propOrder = {
    "activationLimitDuration",
    "activationLimitUsages",
    "activationLimitSession"
})
public class ActivationLimitType {

    @XmlElement(name = "ActivationLimitDuration")
    protected ActivationLimitDurationType activationLimitDuration;
    @XmlElement(name = "ActivationLimitUsages")
    protected ActivationLimitUsagesType activationLimitUsages;
    @XmlElement(name = "ActivationLimitSession")
    protected ActivationLimitSessionType activationLimitSession;

    /**
     * Gets the value of the activationLimitDuration property.
     * 
     * @return
     *     possible object is
     *     {@link ActivationLimitDurationType }
     *     
     */
    public ActivationLimitDurationType getActivationLimitDuration() {
        return activationLimitDuration;
    }

    /**
     * Sets the value of the activationLimitDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivationLimitDurationType }
     *     
     */
    public void setActivationLimitDuration(ActivationLimitDurationType value) {
        this.activationLimitDuration = value;
    }

    /**
     * Gets the value of the activationLimitUsages property.
     * 
     * @return
     *     possible object is
     *     {@link ActivationLimitUsagesType }
     *     
     */
    public ActivationLimitUsagesType getActivationLimitUsages() {
        return activationLimitUsages;
    }

    /**
     * Sets the value of the activationLimitUsages property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivationLimitUsagesType }
     *     
     */
    public void setActivationLimitUsages(ActivationLimitUsagesType value) {
        this.activationLimitUsages = value;
    }

    /**
     * Gets the value of the activationLimitSession property.
     * 
     * @return
     *     possible object is
     *     {@link ActivationLimitSessionType }
     *     
     */
    public ActivationLimitSessionType getActivationLimitSession() {
        return activationLimitSession;
    }

    /**
     * Sets the value of the activationLimitSession property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivationLimitSessionType }
     *     
     */
    public void setActivationLimitSession(ActivationLimitSessionType value) {
        this.activationLimitSession = value;
    }

}
