
package schemas.saml.authenticationcontext;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KeySharingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KeySharingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="sharing" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KeySharingType")
public class KeySharingType {

    @XmlAttribute(required = true)
    protected boolean sharing;

    /**
     * Gets the value of the sharing property.
     * 
     */
    public boolean isSharing() {
        return sharing;
    }

    /**
     * Sets the value of the sharing property.
     * 
     */
    public void setSharing(boolean value) {
        this.sharing = value;
    }

}
