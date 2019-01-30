
package schemas.saml.metadata;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ContactTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ContactTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="technical"/>
 *     &lt;enumeration value="support"/>
 *     &lt;enumeration value="administrative"/>
 *     &lt;enumeration value="billing"/>
 *     &lt;enumeration value="other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ContactTypeType", namespace = "urn:oasis:names:tc:SAML:2.0:metadata")
@XmlEnum
public enum ContactTypeType {

    @XmlEnumValue("technical")
    TECHNICAL("technical"),
    @XmlEnumValue("support")
    SUPPORT("support"),
    @XmlEnumValue("administrative")
    ADMINISTRATIVE("administrative"),
    @XmlEnumValue("billing")
    BILLING("billing"),
    @XmlEnumValue("other")
    OTHER("other");
    private final String value;

    ContactTypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ContactTypeType fromValue(String v) {
        for (ContactTypeType c: ContactTypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
