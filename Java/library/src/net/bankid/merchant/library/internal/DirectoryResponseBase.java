package net.bankid.merchant.library.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Base class for directory responses
 */
public class DirectoryResponseBase {
    protected boolean isError;
    protected XMLGregorianCalendar directoryDateTimestamp;
    protected List<Issuer> issuers;
    protected String rawMessage;
    
    /**
     * An issuer contained in a directory response
     */
    public class Issuer {
        private String issuerCountry;
        private String issuerName;
        private String issuerID;

        /**
         * @return Country name
         */
        public String getIssuerCountry() {
            return issuerCountry;
        }

        /**
         * @return Bank name
         */
        public String getIssuerName() {
            return issuerName;
        }

        /**
         * @return BIC
         */
        public String getIssuerID() {
            return issuerID;
        }

        /**
         * @param issuerCountry the issuerCountry to set
         */
        public void setIssuerCountry(String issuerCountry) {
            this.issuerCountry = issuerCountry;
        }

        /**
         * @param issuerName the issuerName to set
         */
        public void setIssuerName(String issuerName) {
            this.issuerName = issuerName;
        }

        /**
         * @param issuerID the issuerID to set
         */
        public void setIssuerID(String issuerID) {
            this.issuerID = issuerID;
        }
    }
    
    /**
     * @return true if an error occurred, or false when no errors were encountered
     */
    public boolean getIsError() {
        return isError;
    }

    /**
     * @return Date set to when this directory was last updated
     */
    public XMLGregorianCalendar getDirectoryDateTimestamp() {
        return directoryDateTimestamp;
    }

    /**
     * @return List of available issuers
     */
    public final List<Issuer> getIssuers() {
        if (issuers == null) {
            issuers = new ArrayList<>();
        }
        return issuers;
    }
    
    /**
     * @return The response XML
     */
    public String getRawMessage() {
        return rawMessage;
    }

    /**
     * @return List of available issuers as a map where the key is each country and the value is a list of issuers in that country
     */
    public final Map<String, List<Issuer>> getIssuersByCountry() {
        List<Issuer> banks = getIssuers();
        HashMap<String, List<Issuer>> ret = new HashMap<>();
        
        for (Issuer bank : banks) {
            if (ret.containsKey(bank.getIssuerCountry())) {
                ret.get(bank.getIssuerCountry()).add(bank);
            }
            else {
                ret.put(bank.getIssuerCountry(), new ArrayList<Issuer>());
                ret.get(bank.getIssuerCountry()).add(bank);
            }
        }
        
        return ret;
    }
}
