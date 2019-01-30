
package schemas.saml.authenticationcontext;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mediumType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="mediumType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="memory"/>
 *     &lt;enumeration value="smartcard"/>
 *     &lt;enumeration value="token"/>
 *     &lt;enumeration value="MobileDevice"/>
 *     &lt;enumeration value="MobileAuthCard"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "mediumType")
@XmlEnum
public enum MediumType {

    @XmlEnumValue("memory")
    MEMORY("memory"),
    @XmlEnumValue("smartcard")
    SMARTCARD("smartcard"),
    @XmlEnumValue("token")
    TOKEN("token"),
    @XmlEnumValue("MobileDevice")
    MOBILE_DEVICE("MobileDevice"),
    @XmlEnumValue("MobileAuthCard")
    MOBILE_AUTH_CARD("MobileAuthCard");
    private final String value;

    MediumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MediumType fromValue(String v) {
        for (MediumType c: MediumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
