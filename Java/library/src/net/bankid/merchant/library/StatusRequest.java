package net.bankid.merchant.library;

import net.bankid.merchant.library.internal.StatusRequestBase;

/**
 * Describes a status request
 */
public class StatusRequest extends StatusRequestBase {
    /**
     * Parameterless constructor, so it can be used as a Model in views
     */
    public StatusRequest() {
    }
    
    /**
     * Constructor that highlights all required fields for this object
     * @param transactionID The transaction ID to check
     */
    public StatusRequest(String transactionID) {
        this.transactionID = transactionID;
    }
}
