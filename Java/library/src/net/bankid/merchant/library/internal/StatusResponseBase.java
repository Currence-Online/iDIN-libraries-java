package net.bankid.merchant.library.internal;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Base class for status response
 */
public class StatusResponseBase {
    /**
     * Open
     */
    public static final String Open      = "Open";

    /**
     * Pending
     */
    public static final String Pending   = "Pending";

    /**
     * Success
     */
    public static final String Success   = "Success";

    /**
     * Failure
     */
    public static final String Failure   = "Failure";

    /**
     * Expired
     */
    public static final String Expired   = "Expired";

    /**
     * Canceled
     */
    public static final String Cancelled = "Cancelled";
    
    protected boolean isError;
    protected String transactionID;
    protected String status;
    protected XMLGregorianCalendar statusDateTimestamp;
    protected String rawMessage;
    
    /**
     * @return true if an error occurred, or false when no errors were encountered
     */
    public boolean getIsError() {
        return isError;
    }
        
    /**
     * @return The transaction ID
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * @return Possible values: Open, Pending, Success, Failure, Expired, Cancelled
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return Date when the status was created, or null if no such date available
     */
    public XMLGregorianCalendar getStatusDateTimestamp() {
        return statusDateTimestamp;
    }

    /**
     * @return The response XML
     */
    public String getRawMessage() {
        return rawMessage;
    }
}
