package net.bankid.merchant.library;

import javax.xml.bind.JAXBException;
import net.bankid.merchant.library.internal.ErrorResponseBase;
import org.w3c.dom.Node;
import schemas.idx.AcquirerErrorRes;
import schemas.saml.protocol.ResponseType;

/**
 * Describes an error response
 */
public class ErrorResponse extends ErrorResponseBase {
    private SamlStatus additionalInformation;
    
    private ErrorResponse(AcquirerErrorRes errRes) {
        errorCode = errRes.getError().getErrorCode();
        errorMessage = errRes.getError().getErrorMessage();
        errorDetails = errRes.getError().getErrorDetail();
        suggestedAction = errRes.getError().getSuggestedAction();
        consumerMessage = errRes.getError().getConsumerMessage();
        
        if (errRes.getError().getContainer() != null) {
            try {
                Object samlError = errRes.getError().getContainer().getAny().get(0);
                ResponseType response = Utils.deserialize((Node) samlError, ResponseType.class);
                
                additionalInformation = new SamlStatus(
                    response.getStatus().getStatusMessage(),
                    response.getStatus().getStatusCode().getValue(),
                    response.getStatus().getStatusCode().getStatusCode().getValue());
            } catch (JAXBException ex) {
                additionalInformation = null;
            }
        }
    }
    
    private ErrorResponse(Throwable e) {
        errorCode = "";
        errorMessage = e.getMessage();
        errorDetails = e.getCause() != null? e.getCause().getMessage() : "";
        suggestedAction = "";
        consumerMessage = "";
        additionalInformation = null;
    }
    
    private ErrorResponse(SamlStatus samlStatus) {                       
        errorCode = "";
        errorMessage = "SAML specific error.";
        errorDetails = "";
        suggestedAction = "";
        consumerMessage = "";
        additionalInformation = samlStatus;
    }
    
    static ErrorResponse Get(AcquirerErrorRes errRes) {
        return new ErrorResponse(errRes);
    }
    
    static ErrorResponse Get(Throwable e) {
        return new ErrorResponse(e);
    }
    
    static ErrorResponse Get(SamlStatus samlStatus) {
        return new ErrorResponse(samlStatus);
    }
    
    /**
     * @return details of the SAML status
     */
    public SamlStatus getAdditionalInformation() {
        return additionalInformation;
    }
}
