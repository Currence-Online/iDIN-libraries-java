package net.bankid.merchant.library;

import javax.xml.bind.JAXBException;
import net.bankid.merchant.library.internal.StatusResponseBase;
import net.bankid.merchant.library.validation.Validator;
import schemas.idx.AcquirerErrorRes;
import schemas.idx.AcquirerStatusRes;

/**
 * Represents a status response
 */
public class StatusResponse extends StatusResponseBase {
    private SamlResponse samlResponse;
    private ErrorResponse errorResponse;
    
    private StatusResponse(Configuration config, AcquirerStatusRes stsRes, String xml) throws JAXBException, CommunicatorException {
        isError = false; 
        errorResponse = null;
        transactionID = stsRes.getTransaction().getTransactionID();
        status = stsRes.getTransaction().getStatus();
        rawMessage = xml;
        
        if (status.equalsIgnoreCase(Success)) {
            statusDateTimestamp = stsRes.getTransaction().getStatusDateTimestamp();
        
            SamlResponse accReport = SamlResponse.Parse(config, stsRes.getTransaction().getContainer().getAny().get(0));
            Validator.validate(accReport);
            samlResponse = accReport;
            
            // check for invalid SAML status
            if (!samlResponse.getStatus().getStatusCodeFirstLevel().equalsIgnoreCase(SamlStatus.Success)){
                isError = true;
                errorResponse = ErrorResponse.Get(samlResponse.getStatus());
            }
        }
        else {
            if (stsRes.getTransaction().getContainer() != null && stsRes.getTransaction().getContainer().getAny() != null) {
                throw new CommunicatorException("Response should not have a BankId signature.");
            }
        }
    }
    
    private StatusResponse(AcquirerErrorRes errRes, String xml) throws CommunicatorException {
        isError = true; 
        errorResponse = ErrorResponse.Get(errRes);
        transactionID = null;
        statusDateTimestamp = null;
        status = null;
        rawMessage = xml;
        samlResponse = null;
    }
    
    private StatusResponse(Throwable e) {
        isError = true; 
        errorResponse = ErrorResponse.Get(e);
        transactionID = null;
        statusDateTimestamp = null;
        status = null;
        rawMessage = null;
        samlResponse = null;
    }
    
    static StatusResponse Parse(Configuration config, String xml) {
        try {
            AcquirerStatusRes stsRes = (AcquirerStatusRes) Utils.deserialize(xml, AcquirerStatusRes.class);
            Validator.validate(stsRes);
            return new StatusResponse(config, stsRes, xml);
        }
        catch (Exception e1) {
            try {
                AcquirerErrorRes errRes = (AcquirerErrorRes) Utils.deserialize(xml, AcquirerErrorRes.class);
                return new StatusResponse(errRes, xml);
            }
            catch (Exception e2) {
                StatusResponse sr = new StatusResponse(e1);
                sr.rawMessage = xml;
                return sr;
            }
        }
    }
    
    static StatusResponse Get(Throwable e) {
        return new StatusResponse(e);
    }

    /**
     * @return the samlResponse
     */
    public SamlResponse getSamlResponse() {
        return samlResponse;
    }
    
    /**
     * @return Object that holds the error if one occurs; when there are no errors, this is set to null
     */
    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
