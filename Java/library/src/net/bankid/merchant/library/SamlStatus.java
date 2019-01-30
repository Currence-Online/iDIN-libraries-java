package net.bankid.merchant.library;

/**
 * Class responsible for handling Saml Statuses
 */
public class SamlStatus {
    public static final String Success   = "urn:oasis:names:tc:SAML:2.0:status:Success";
            
    private String statusMessage;
    private String statusCodeFirstLevel;
    private String statusCodeSecondLevel;
    
    SamlStatus(String statusMessage, String statusCodeFirstLevel, String statusCodeSecondLevel) {
        this.statusMessage = statusMessage;
        this.statusCodeFirstLevel = statusCodeFirstLevel;
        this.statusCodeSecondLevel = statusCodeSecondLevel;
    }

    /**
     * @return The status message
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @param statusMessage The status message
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    /**
     * @return The status code first level
     */
    public String getStatusCodeFirstLevel() {
        return statusCodeFirstLevel;
    }

    /**
     * @param statusCodeFirstLevel The status code first level
     */
    public void setStatusCodeFirstLevel(String statusCodeFirstLevel) {
        this.statusCodeFirstLevel = statusCodeFirstLevel;
    }

    /**
     * @return The status code second level
     */
    public String getStatusCodeSecondLevel() {
        return statusCodeSecondLevel;
    }

    /**
     * @param statusCodeSecondLevel The status code second level
     */
    public void setStatusCodeSecondLevel(String statusCodeSecondLevel) {
        this.statusCodeSecondLevel = statusCodeSecondLevel;
    }
}
