package net.bankid.merchant.library.internal;

import javax.xml.datatype.Duration;

/**
 * Base class for transaction requests
 */
public class TransactionRequestBase extends IdxMessageBase {
    protected String entranceCode;
    protected String language;
    protected Duration expirationPeriod;
    protected String issuerID;
    
    /**
     * @return An 'authentication identifier' to facilitate continuation of the session even if the existing session has been lost.
     */
    public String getEntranceCode() {
        return entranceCode;
    }

    /**
     * @param entranceCode An 'authentication identifier' to facilitate continuation of the session even if the existing session has been lost.
     */
    public void setEntranceCode(String entranceCode) {
        this.entranceCode = entranceCode;
    }

    /**
     * @return This field enables the Issuers's site to select the Consumer's preferred language (e.g. the language selected on the Merchant's site), if the Issuers's site supports this: Dutch = 'nl', English = 'en'. Language used by Consumer formatted like ISO 639-1. 
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language This field enables the Issuers's site to select the Consumer's preferred language (e.g. the language selected on the Merchant's site), if the Issuers's site supports this: Dutch = 'nl', English = 'en'. Language used by Consumer formatted like ISO 639-1. 
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return Optional: The period of validity of the authentication request
     */
    public Duration getExpirationPeriod() {
        return expirationPeriod;
    }

    /**
     * @param expirationPeriod Optional: The period of validity of the authentication request
     */
    public void setExpirationPeriod(Duration expirationPeriod) {
        this.expirationPeriod = expirationPeriod;
    }

    /**
     * @return The identification (usually BIC number) of the issuing party
     */
    public String getIssuerID() {
        return issuerID;
    }

    /**
     * @param issuerID The identification (usually BIC number) of the issuing party
     */
    public void setIssuerID(String issuerID) {
        this.issuerID = issuerID;
    }
}
