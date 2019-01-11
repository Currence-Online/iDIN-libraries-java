package net.bankid.merchant.library.internal;

/**
 * Base class for status requests
 */
public class StatusRequestBase extends IdxMessageBase {
    protected String transactionID;
    
    /**
     * @return the transactionID
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * @param transactionID the transactionID to set
     */
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
}
