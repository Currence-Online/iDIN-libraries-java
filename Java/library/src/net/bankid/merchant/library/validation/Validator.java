package net.bankid.merchant.library.validation;

import java.net.URL;
import java.util.regex.Pattern;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import org.w3c.dom.Element;
import net.bankid.merchant.library.SamlResponse;
import net.bankid.merchant.library.ServiceIds;
import net.bankid.merchant.library.CommunicatorException;
import net.bankid.merchant.library.SamlAttribute;
import schemas.idx.AcquirerStatusReq;
import schemas.idx.AcquirerStatusRes;
import schemas.idx.AcquirerTrxReq;
import schemas.idx.AcquirerTrxRes;
import schemas.idx.DirectoryReq;
import schemas.idx.DirectoryRes;
import schemas.saml.protocol.AuthnRequestType;

/**
 * Class used for validating different fields
 */
public class Validator {
    
    /**
     * Interface describing a custom validator (for when a regexp isn't enough)
     * @param <T> The type that this ICustomValidator can handle
     */
    public interface ICustomValidation<T> {
        /**
         * @param value The value to validate
         * @return true if the value is valid, false otherwise
         */
        boolean isValid(T value);
    }
    
    //protected static final Pattern URL = Pattern.compile("^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\\\?([^#]*))?(#(.*))?");

    protected static final Pattern DateTimestamp = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{3}Z");

    protected static final Pattern BankId_MerchantReference = Pattern.compile("[a-zA-Z][a-zA-Z0-9]{0,34}");

    protected static final Pattern BankId_LOA = Pattern.compile("nl:bvn:bankid:1.0:loa[2-3]{1}");

    protected static final Pattern BankId_TransactionID = Pattern.compile("[0-9]{16}");
    
    protected static final Pattern BankId_DocumentID = Pattern.compile("[a-zA-Z0-9]{0,255}");  
    /**
     * Validates a directory request object
     * @param dirReq the object to validate
     * @throws CommunicatorException 
     */
    public static void validate(DirectoryReq dirReq) throws CommunicatorException {
        checkByValue(dirReq.getCreateDateTimestamp().toString(), DateTimestamp);
    }
    
    /**
     * Validates a directory response object
     * @param dirRes the object to validate
     * @throws CommunicatorException 
     */
    public static void validate(DirectoryRes dirRes) throws CommunicatorException {
        checkByValue(dirRes.getCreateDateTimestamp().toString(), DateTimestamp);
        checkByValue(dirRes.getDirectory().getDirectoryDateTimestamp().toString(), DateTimestamp);
    }

    /**
     * Validates a transaction request object
     * @param trxReq the object to validate
     * @throws CommunicatorException 
     */
    public static void validate(AcquirerTrxReq trxReq) throws CommunicatorException {
        checkByValue(trxReq.getCreateDateTimestamp().toString(), DateTimestamp);
        checkByValue(trxReq.getTransaction().getExpirationPeriod(), new ICustomValidation<Duration>() {
            @Override
            public boolean isValid(Duration value) {
                return Validator.checkDuration(value);
            }
        });
        checkUrl(trxReq.getMerchant().getMerchantReturnURL());
    }
    
    /**
     * Validates a transaction response object
     * @param trxRes the object to validate
     * @throws CommunicatorException 
     */
    public static void validate(AcquirerTrxRes trxRes) throws CommunicatorException {
        checkByValue(trxRes.getCreateDateTimestamp().toString(), DateTimestamp);
        checkUrl(trxRes.getIssuer().getIssuerAuthenticationURL());
        checkByValue(trxRes.getTransaction().getTransactionCreateDateTimestamp().toString(), DateTimestamp);
    }
    
    /**
     * Validates a status request object
     * @param stsReq the object to validate
     * @throws CommunicatorException 
     */
    public static void validate(AcquirerStatusReq stsReq) throws CommunicatorException {
        checkByValue(stsReq.getCreateDateTimestamp().toString(), DateTimestamp);
    }
    
    /**
     * Validates a directory response object
     * @param stsRes the object to validate
     * @throws CommunicatorException 
     */
    public static void validate(AcquirerStatusRes stsRes) throws CommunicatorException {
        checkByValue(stsRes.getCreateDateTimestamp().toString(), DateTimestamp);
    }
    
    /**
     * Validates a SAML AuthnRequest object, part of transaction request
     * @param authnReq the object to validate
     * @throws CommunicatorException 
     */
    public static void validate(AuthnRequestType authnReq) throws CommunicatorException {
        checkByValue(authnReq.getID(), BankId_MerchantReference);
        checkUrl(authnReq.getAssertionConsumerServiceURL());
        checkByValue(authnReq.getRequestedAuthnContext().getAuthnContextClassRef().get(0), BankId_LOA);
        checkByValue(authnReq.getAttributeConsumingServiceIndex(), new ICustomValidation<Integer>() {
            @Override
            public boolean isValid(Integer value) {
                return Validator.checkBankIdServiceId(value);
            }
        });
        checkSigning(authnReq);
    }
    
    /**
     * Validates an SamlResponse object, part of status response
     * @param accRec the object to validate
     * @throws CommunicatorException 
     */
    public static void validate(SamlResponse accRec) throws CommunicatorException {
        for (String attrKey : accRec.getAttributes().keySet()) {
            String attrValue = accRec.getAttributes().get(attrKey);
            
            switch (attrKey) {
                case SamlAttribute.ConsumerBin:
                    checkByValue(attrValue, Pattern.compile("[A-Za-z]{2}[^\\p{Cntrl}]{0,1024}"));
                    break;
                case SamlAttribute.ConsumerTransientID:
                    checkByValue(attrValue, Pattern.compile("TRANS[^\\p{Cntrl}]{1,256}"));
                    break;
                case SamlAttribute.ConsumerDeprecatedBin:
                    checkByValue(attrValue, Pattern.compile("[A-Za-z]{2}[^\\p{Cntrl}]{0,1024}"));
                    break;
                case SamlAttribute.ConsumerGender:
                    checkByValue(attrValue, Pattern.compile("0|1|2|9"));
                    break;
                case SamlAttribute.ConsumerLegalLastName:
                    checkByValue(attrValue, Pattern.compile("[^\\p{Cntrl}]{1,200}"));
                    break;
                case SamlAttribute.ConsumerLegalLastNamePrefix:
                    checkByValue(attrValue, Pattern.compile("[^\\p{Cntrl}]{1,10}"));
                    break;
                case SamlAttribute.ConsumerInitials:
                    checkByValue(attrValue, Pattern.compile("^[\\p{Lu}]{1,24}"));
                    break;
                case SamlAttribute.ConsumerDateOfBirth:
                    checkByValue(attrValue, Pattern.compile("^\\d\\d\\d\\d(0[01-9]|1[012])(0[0-9]|[12][0-9]|3[01])$"));
                    break;
                case SamlAttribute.ConsumerStreet:
                    checkByValue(attrValue, Pattern.compile("[^\\p{Cntrl}]{1,43}"));
                    break;
                case SamlAttribute.ConsumerHouseNo:
                    checkByValue(attrValue, Pattern.compile("[0-9]{1,5}"));
                    break;
                case SamlAttribute.ConsumerAddressExtra:
                    checkByValue(attrValue, Pattern.compile("[^\\p{Cntrl}]{1,70}"));
                    break;
                case SamlAttribute.ConsumerPostalCode:
                    checkByValue(attrValue, Pattern.compile("[0-9]{4}[a-zA-Z]{2}"));
                    break;
                case SamlAttribute.ConsumerCity:
                    checkByValue(attrValue, Pattern.compile("[^\\p{Cntrl}]{1,24}"));
                    break;
                case SamlAttribute.ConsumerCountry:
                    checkByValue(attrValue, Pattern.compile("[a-zA-Z]{2}"));
                    break;
                case SamlAttribute.ConsumerIs18OrOlder:
                    checkByValue(attrValue, Pattern.compile("true|false"));
                    break;
                case SamlAttribute.ConsumerPrefLastName:
                    checkByValue(attrValue, Pattern.compile("[^\\p{Cntrl}]{1,200}"));
                    break;
                case SamlAttribute.ConsumerPartnerLastName:
                    checkByValue(attrValue, Pattern.compile("[^\\p{Cntrl}]{1,200}"));
                    break;
                case SamlAttribute.ConsumerPrefLastNamePrefix:
                    checkByValue(attrValue, Pattern.compile("[^\\p{Cntrl}]{1,10}"));
                    break;
                case SamlAttribute.ConsumerPartnerLastNamePrefix:
                    checkByValue(attrValue, Pattern.compile("[^\\p{Cntrl}]{1,10}"));
                    break;
                case SamlAttribute.ConsumerTelephone:
                    checkByValue(attrValue, Pattern.compile("[0-9\\(\\)\\p{Space}+-]{1,20}"));
                    break;
                case SamlAttribute.ConsumerEmail:
                    checkByValue(attrValue, Pattern.compile("[^\\p{Cntrl}]{1,255}"));
                    break;
                case SamlAttribute.ConsumerIntAddressLine1:
                    checkByValue(attrValue, Pattern.compile("[^\\p{Cntrl}]{1,70}"));
                    break;
                case SamlAttribute.ConsumerIntAddressLine2:
                    checkByValue(attrValue, Pattern.compile("[^\\p{Cntrl}]{1,70}"));
                    break;
                case SamlAttribute.ConsumerIntAddressLine3:
                    checkByValue(attrValue, Pattern.compile("[^\\p{Cntrl}]{1,70}"));
                    break;
            }
        }
    }
    
    protected static void checkByValue(String value, Pattern regexp) throws CommunicatorException {
        if (!regexp.matcher(value).matches())
            throw new CommunicatorException("incorrect value: " + value);
    }
    
    protected static <T> void checkByValue(T value, ICustomValidation<T> validation) throws CommunicatorException {
        if (!validation.isValid(value))
            throw new CommunicatorException("incorrect value: " + value.toString());
    }
    
    protected static void checkUrl(String value) throws CommunicatorException {
        try {
            URL url = new URL(value);
        }
        catch (Exception e) {
            throw new CommunicatorException("malformed url", e);
        }
    }
    
    /**
     * Validates a Duration object, this is used via an anonymous ICustomValidator instance
     * @param value the object to check
     * @return true if the value is valid, false otherwise
     */
    public static boolean checkDuration(Duration value) {
        if (value == null)
            return true;
        try {
            Duration maxDuration = DatatypeFactory.newInstance().newDuration(true, 0, 0, 0, 0, 5, 0);
            return !value.isLongerThan(maxDuration);
        } catch (DatatypeConfigurationException ex) {
            return false;
        }
    }
    
    /**
     * Validates a ServiceId, this is used via an anonymous ICustomValidator instance
     * @param value the object to check
     * @return true if the value is valid, false otherwise
     */
    public static boolean checkBankIdServiceId(int value) {
        // see 150528A BankID Implementation Guidelines v1.03-TC.pdf,
        // section 3.3.1, table 7 for more information
        
        // unsigned short interval [0, 65535]
        if (value<0 || value > 65535){
            return false;
        }
                
        return true;
    }
    
	private static void checkSigning(AuthnRequestType authnReq) throws CommunicatorException {
		String documentId = "";
		if (!authnReq.getExtensions().getAny().isEmpty()) {
			Element documentIdAttribute = (Element) authnReq.getExtensions().getAny().get(0);
			documentId = documentIdAttribute.getElementsByTagName("AttributeValue").item(0).getTextContent();
			checkByValue(documentId, BankId_DocumentID);
		}
		
		int requestedServiceId = authnReq.getAttributeConsumingServiceIndex();
		if (!documentId.isEmpty()) {
			if ((requestedServiceId & ServiceIds.Sign) != 0 && requestedServiceId != ServiceIds.Sign) {
				throw new CommunicatorException("Sign cannnot be combined with other services");
			} else if (requestedServiceId != ServiceIds.Sign) {
				throw new CommunicatorException("DocumentID should not be filled if the Sign service is not requested");
			}
		} else {
			if (requestedServiceId == ServiceIds.Sign || (requestedServiceId & ServiceIds.Sign) != 0) {
				throw new CommunicatorException("DocumentID should be present");
			}
		}
	}
}
