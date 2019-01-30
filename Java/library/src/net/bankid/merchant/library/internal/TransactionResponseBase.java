package net.bankid.merchant.library.internal;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Base class for transaction responses
 */
public class TransactionResponseBase {
    protected boolean isError;
    protected String issuerAuthenticationURL;
    protected String transactionID;
    protected XMLGregorianCalendar transactionCreateDateTimestamp;
    protected String rawMessage;

    /**
     * @return true if an error occurred, or false when no errors were encountered
     */
    public boolean getIsError() {
        return isError;
    }

    /**
     * @return The URL to which to redirect the consumer so they can authorize the transaction
     */
    public String getIssuerAuthenticationURL() {
        return issuerAuthenticationURL;
    }

    /**
     * @return The transaction ID
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * @return Date set to when this transaction was created
     */
    public XMLGregorianCalendar getTransactionCreateDateTimestamp() {
        return transactionCreateDateTimestamp;
    }

    /**
     * @return The response XML
     */
    public final String getRawMessage() {
        return rawMessage;
    }
}
