package net.bankid.merchant.library;

import java.util.UUID;
import net.bankid.merchant.library.internal.TransactionRequestBase;

/**
 * Describes a new authentication request
 */
public class AuthenticationRequest extends TransactionRequestBase {
    protected String merchantReference;
    protected ServiceId requestedServiceID;
    protected AssuranceLevel assuranceLevel;
    protected String documentID;
    
    /**
     * This constructor initializes default values for some fields
     */
    public AuthenticationRequest() {
        this.assuranceLevel = AssuranceLevel.Loa3;
        this.language = "nl";
        this.merchantReference = GenerateMerchantReference();
    }
    /**
     * Constructor with the minimum number of arguments
     * @param entranceCode the entrance code
     * @param requestedServiceID the service id requested
     * @param issuerId the id of the issuer
     */
    public AuthenticationRequest(String entranceCode, int requestedServiceID, String issuerId) {
        this.entranceCode = entranceCode;
        this.requestedServiceID = new ServiceId(requestedServiceID);
        this.issuerID = issuerId;
        this.assuranceLevel = AssuranceLevel.Loa3;
        this.language = "nl";
        this.merchantReference = GenerateMerchantReference();
    }
    
    /**
     * Constructor with full parameters
     * @param entranceCode the entrance code
     * @param requestedServiceID the service id requested
     * @param issuerId the id of the issuer
     * @param assuranceLevel AssuranceLevel.Loa3
     * @param languange "en", "nl", etc
     * @param merchantReference either a supplied value or null if you want the library to generate one
     */
    public AuthenticationRequest(String entranceCode, int requestedServiceID, String issuerId,
            AssuranceLevel assuranceLevel, String languange, String merchantReference) {
        this.entranceCode = entranceCode;
        this.requestedServiceID = new ServiceId(requestedServiceID);
        this.issuerID = issuerId;
        this.assuranceLevel = assuranceLevel;
        this.language = (languange == null || languange.isEmpty()) ? "nl" : languange;
        this.merchantReference = (merchantReference == null || merchantReference.isEmpty())?
                GenerateMerchantReference() : merchantReference;
    }

    /**
     * @return A unique transaction reference, specified by the Merchant
     */
    public String getMerchantReference() {
        return merchantReference;
    }

    /**
     * @param merchantReference A unique transaction reference, specified by the Merchant
     */
    public void setMerchantReference(String merchantReference) {
        this.merchantReference = merchantReference;
    }

    /**
     * @return ID of the list of services provided
     */
    public ServiceId getRequestedServiceID() {
        return requestedServiceID;
    }

    /**
     * @param requestedServiceID ID of the list of services provided
     */
    public void setRequestedServiceID(ServiceId requestedServiceID) {
        this.requestedServiceID = requestedServiceID;
    }

    /**
     * @return The minimum level of assurance required
     */
    public AssuranceLevel getAssuranceLevel() {
        return assuranceLevel;
    }

    /**
     * @param assuranceLevel The minimum level of assurance required
     */
    public void setAssuranceLevel(AssuranceLevel assuranceLevel) {
        this.assuranceLevel = assuranceLevel;
    }
    
    /**
     * @return documentID
     */
    public String getDocumentID() {
		return documentID;
	}
    
    /**
     * @param documentID
     */
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}
    
    private static String GenerateMerchantReference() {
        return (
            "A" +
            UUID.randomUUID().toString().replaceAll("-", "") +
            UUID.randomUUID().toString().replaceAll("-", "")
        ).substring(0, 35);
    }
}
