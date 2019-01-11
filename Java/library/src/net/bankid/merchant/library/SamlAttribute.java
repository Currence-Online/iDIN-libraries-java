package net.bankid.merchant.library;

public class SamlAttribute {
    /**
     * Get the delivered service id (can be the same as RequestedServiceID, a different number, or 0) 
     */
    public static final String DeliveredServiceID = "urn:nl:bvn:bankid:1.0:bankid.deliveredserviceid";
    
    /**
     * Get the BIN(Bank Identifying Number) 
     */
    public static final String ConsumerBin = "urn:nl:bvn:bankid:1.0:consumer.bin";
    
    /**
     * Get the Consumer TransientID
     */
    public static final String ConsumerTransientID = "urn:nl:bvn:bankid:1.0:consumer.transientid";
    
    /**
     * Get the Consumer gender
     */
    public static final String ConsumerGender = "urn:nl:bvn:bankid:1.0:consumer.gender";
    
    /**
     * Get the Consumer legal last name 
     */
    public static final String ConsumerLegalLastName = "urn:nl:bvn:bankid:1.0:consumer.legallastname";
    
    /**
     * Get the Consumer preferred last name
     */
    public static final String ConsumerPrefLastName = "urn:nl:bvn:bankid:1.0:consumer.preferredlastname";
    
    /**
     * Get the Consumer's registered partner last name (analogous to legal last name)
     */
    public static final String ConsumerPartnerLastName = "urn:nl:bvn:bankid:1.0:consumer.partnerlastname";
    
    /**
     * Get the last name prefix of Consumer's legal last name
     */
    public static final String ConsumerLegalLastNamePrefix = "urn:nl:bvn:bankid:1.0:consumer.legallastnameprefix";
    
    /**
     * Get the last name prefix of Consumer preferred last name (analogous to legal last name prefix)
     */
    public static final String ConsumerPrefLastNamePrefix = "urn:nl:bvn:bankid:1.0:consumer.preferredlastnameprefix";
    
    /**
     * Get the initials of the Consumer
     */
    public static final String ConsumerInitials = "urn:nl:bvn:bankid:1.0:consumer.initials";
    
    /**
     * Get the date of birth of the Consumer
     */
    public static final String ConsumerDateOfBirth = "urn:nl:bvn:bankid:1.0:consumer.dateofbirth";
    
    /**
     * Get the street name of the Consumer's residential address
     */
    public static final String ConsumerStreet = "urn:nl:bvn:bankid:1.0:consumer.street";
    
    /**
     * Get the house number of the Consumer's residential address
     */
    public static final String ConsumerHouseNo = "urn:nl:bvn:bankid:1.0:consumer.houseno";
    
    /**
     * Get the house number suffix of the Consumer's residential address. Used for NL addresses only
     */
    public static final String ConsumerHouseNoSuf = "urn:nl:bvn:bankid:1.0:consumer.housenosuf";
    
    /**
     * Get additional address details of Consumer's residential address. Used for NL addresses only
     */
    public static final String ConsumerAddressExtra = "urn:nl:bvn:bankid:1.0:consumer.addressextra";
    
    /**
     * Get the postal code of the Consumer's residential address
     */
    public static final String ConsumerPostalCode = "urn:nl:bvn:bankid:1.0:consumer.postalcode";
    
    /**
     * Get the city of the Customer's residential address
     */
    public static final String ConsumerCity = "urn:nl:bvn:bankid:1.0:consumer.city";
    
    /**
     * Get the country code of the country where the Consumer resides
     */
    public static final String ConsumerCountry = "urn:nl:bvn:bankid:1.0:consumer.country";
    
    /**
     * Get the Deprecated BIN
     */
    public static final String ConsumerDeprecatedBin = "urn:nl:bvn:bankid:1.0:consumer.deprecatedbin";
    
    /**
     * Get the value specifying if the Consumer is 18 or older
     */
    public static final String ConsumerIs18OrOlder = "urn:nl:bvn:bankid:1.0:consumer.is18orolder";
    
    /**
     * Get the Consumer's registered partner last name prefix
     */
    public static final String ConsumerPartnerLastNamePrefix = "urn:nl:bvn:bankid:1.0:consumer.partnerlastnameprefix";
    
    /**
     * Get Consumer's telephone
     */
    public static final String ConsumerTelephone = "urn:nl:bvn:bankid:1.0:consumer.telephone";
    
    /**
     * Get Consumer's email
     */
    public static final String ConsumerEmail = "urn:nl:bvn:bankid:1.0:consumer.email";
    
    /**
     * Get Consumer's address line 1
     */
    public static final String ConsumerIntAddressLine1 = "urn:nl:bvn:bankid:1.0:consumer.intaddressline1";
    
    /**
     * Get Consumer's address line 2
     */
    public static final String ConsumerIntAddressLine2 = "urn:nl:bvn:bankid:1.0:consumer.intaddressline2";
    
    /**
     * Get Consumer's address line 3
     */
    public static final String ConsumerIntAddressLine3 = "urn:nl:bvn:bankid:1.0:consumer.intaddressline3";
    
    /**
     * Get Consumer's document ID
     */
    public static final String DocumentId = "urn:nl:bvn:bankid:1.0:consumer.documentID";
    
    
    private String name;
    private String value;

    /**
     * @return Name of the Saml Attribute
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Name of the Saml Attribute
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Name of the Saml Attribute
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value Name of the Saml Attribute
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    public SamlAttribute(String name, String value)
    {
        this.name = name;
        this.value = value;
    }
}
