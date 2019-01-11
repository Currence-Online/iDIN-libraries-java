package net.bankid.merchant.library;

import net.bankid.merchant.library.internal.TransactionResponseBase;
import net.bankid.merchant.library.validation.Validator;
import schemas.idx.AcquirerErrorRes;
import schemas.idx.AcquirerTrxRes;

/**
 * Describes a transaction response
 */
public class AuthenticationResponse extends TransactionResponseBase {
    private ErrorResponse errorResponse;
    
    private AuthenticationResponse(AcquirerTrxRes trxRes, String xml) throws CommunicatorException {
        isError = false;
        errorResponse = null;
        issuerAuthenticationURL = trxRes.getIssuer().getIssuerAuthenticationURL();
        transactionID = trxRes.getTransaction().getTransactionID();
        transactionCreateDateTimestamp = trxRes.getTransaction().getTransactionCreateDateTimestamp();
        rawMessage = xml;
    }
    
    private AuthenticationResponse(AcquirerErrorRes errRes, String xml) throws CommunicatorException {
        isError = true;
        errorResponse = ErrorResponse.Get(errRes);
        issuerAuthenticationURL = null;
        transactionID = null;
        transactionCreateDateTimestamp = null;
        rawMessage = xml;
    }
    
    private AuthenticationResponse(Throwable e) {
        isError = true;
        errorResponse = ErrorResponse.Get(e);
        issuerAuthenticationURL = null;
        transactionID = null;
        transactionCreateDateTimestamp = null;
        rawMessage = null;
    }
    
    static AuthenticationResponse Parse(String xml) {
        try {
            AcquirerTrxRes trxRes = (AcquirerTrxRes) Utils.deserialize(xml, AcquirerTrxRes.class);
            Validator.validate(trxRes);
            return new AuthenticationResponse(trxRes, xml);
        }
        catch (Exception e1) {
            try {
                AcquirerErrorRes errRes = (AcquirerErrorRes) Utils.deserialize(xml, AcquirerErrorRes.class);
                return new AuthenticationResponse(errRes, xml);
            }
            catch (Exception e2) {
                AuthenticationResponse nmr = new AuthenticationResponse(e1);
                nmr.rawMessage = xml;
                return nmr;
            }
        }
    }
    
    static AuthenticationResponse Get(Throwable e) {
        return new AuthenticationResponse(e);
    }
    
    /**
     * @return Object that holds the error if one occurs; when there are no errors, this is set to null
     */
    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
