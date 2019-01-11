package net.bankid.merchant.library;

import net.bankid.merchant.library.internal.DirectoryResponseBase;
import net.bankid.merchant.library.validation.Validator;
import schemas.idx.AcquirerErrorRes;
import schemas.idx.DirectoryRes;

/**
 * Describes a directory response
 */
public class DirectoryResponse extends DirectoryResponseBase {
    private ErrorResponse errorResponse;
    
    private DirectoryResponse(DirectoryRes dirRes, String xml) throws CommunicatorException {
        isError = false;
        errorResponse = null;
        directoryDateTimestamp = dirRes.getDirectory().getDirectoryDateTimestamp();
        
        for (final DirectoryRes.Directory.Country c : dirRes.getDirectory().getCountry()) {
            for (final DirectoryRes.Directory.Country.Issuer i : c.getIssuer()) {
                Issuer db = new Issuer();
                db.setIssuerCountry(c.getCountryNames());
                db.setIssuerID(i.getIssuerID());
                db.setIssuerName(i.getIssuerName());
                
                getIssuers().add(db);
            }
        }
        
        rawMessage = xml;
    }
    
    private DirectoryResponse(AcquirerErrorRes errRes, String xml) throws CommunicatorException {
        isError = true;
        errorResponse = ErrorResponse.Get(errRes);
        directoryDateTimestamp = null;
        getIssuers().clear();
        rawMessage = xml;
    }
    
    private DirectoryResponse(Throwable e) {
        isError = true;
        errorResponse = ErrorResponse.Get(e);
        directoryDateTimestamp = null;
        getIssuers().clear();
        rawMessage = null;
    }
    
    static DirectoryResponse Parse(String xml) {
        try {
            DirectoryRes dirRes = (DirectoryRes) Utils.deserialize(xml, DirectoryRes.class);
            Validator.validate(dirRes);
            return new DirectoryResponse(dirRes, xml);
        }
        catch (Exception e1) {
            try {
                AcquirerErrorRes errRes = (AcquirerErrorRes) Utils.deserialize(xml, AcquirerErrorRes.class);
                return new DirectoryResponse(errRes, xml);
            }
            catch (Exception e2) {
                DirectoryResponse dr = new DirectoryResponse(e1);
                dr.rawMessage = xml;
                return dr;
            }
        }
    }
    
    static DirectoryResponse Get(Throwable e) {
        return new DirectoryResponse(e);
    }
    
    /**
     * @return Object that holds the error if one occurs; when there are no errors, this is set to null
     */
    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
