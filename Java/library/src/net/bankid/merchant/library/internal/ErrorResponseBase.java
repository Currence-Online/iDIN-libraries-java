package net.bankid.merchant.library.internal;

/**
 * Base class for error responses
 */
public class ErrorResponseBase {
    protected String errorCode;
    protected String errorMessage;
    protected String errorDetails;
    protected String suggestedAction;
    protected String consumerMessage;
    
    /**
     * @return Unique identification of the error occurring within the iDx transaction
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @return Descriptive text accompanying Error.errorCode
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @return Details of the error
     */
    public String getErrorDetails() {
        return errorDetails;
    }

    /**
     * @return Suggestions aimed at resolving the problem
     */
    public String getSuggestedAction() {
        return suggestedAction;
    }

    /**
     * @return A (standardized) message that the merchant should show to the consumer
     */
    public String getConsumerMessage() {
        return consumerMessage;
    }
}
