package net.bankid.merchant.library.tests;

import mockit.NonStrictExpectations;

import net.bankid.merchant.library.Communicator;
import net.bankid.merchant.library.StatusRequest;
import net.bankid.merchant.library.StatusResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import net.bankid.merchant.library.TestBaseClass;
import org.junit.Test;
import org.w3c.dom.Document;

public class Communicator_GetResponse_Test extends TestBaseClass {
    public Communicator_GetResponse_Test() throws Exception {
    }
    
    @Test
    public void Should_generate_correct_idx_request() throws Exception {
        Invoke("StatusResponse-OK-1");
        
        Document req = messenger.getRequestDocument();
        
        assertThat(req.getDocumentElement().getAttribute("version"), is("1.0.0"));
        assertThat(req.getDocumentElement().getAttribute("productID"), is("NL:BVN:BankID:1.0"));
        assertThat(getTextContent(req, NameBVN, "createDateTimestamp"), matchesRegexp(dateTimeRegexp));
        
        assertThat(getTextContent(req, NameBVN, "merchantID"), is("1234567890"));
        assertThat(getTextContent(req, NameBVN, "subID"), is("42"));
        
        assertThat(getTextContent(req, NameBVN, "transactionID"), is("1234000000002144"));
        
        assertThat(req.getElementsByTagNameNS("*", "Signature").getLength(), is(1));
    }
    
    @Test
    public void Should_parse_correct_response() throws Exception {
        Invoke("StatusResponse-OK-1");
        
        Document res = messenger.getResponseDocument();
        
        assertThat(res.getDocumentElement().getAttribute("version"), is("1.0.0"));
        assertThat(res.getDocumentElement().getAttribute("productID"), is("NL:BVN:BankID:1.0"));
        assertThat(getTextContent(res, NameBVN, "createDateTimestamp"), matchesRegexp(dateTimeRegexp));
        
        assertThat(getTextContent(res, NameBVN, "acquirerID"), is("1234"));
        
        assertThat(res.getElementsByTagNameNS("*", "Signature").getLength(), is(2));
    }
    
    @Test
    public void Should_parse_data_response() throws Exception {
        StatusResponse response = Invoke("StatusResponse-OK-2");
        
        Document res = messenger.getResponseDocument();
        
        assertThat(response.getIsError(), is(false));
        assertThat(response.getSamlResponse(), notNullValue());
        assertThat(response.getSamlResponse().getStatus().getStatusCodeFirstLevel(),
            is("urn:oasis:names:tc:SAML:2.0:status:Success"));
        assertThat(response.getSamlResponse().getAttributes().get("urn:nl:bvn:bankid:1.0:consumer.bin"),
            is("BANKa94a8fe5ccb19ba61c4c0873d391e987982fbbd3"));
        assertThat(response.getSamlResponse().getAttributes().get("urn:nl:bvn:bankid:1.0:consumer.gender"),
            is("1"));
    }
    
    @Test
    public void Should_parse_error_message_with_no_container() throws Exception {
        StatusResponse response = Invoke("ErrorResponse-NoContainer");
        
        Document res = messenger.getResponseDocument();
        
        assertThat(response.getIsError(), is(true));
        assertThat(response.getErrorResponse(), notNullValue());
        assertThat(response.getErrorResponse().getErrorCode(), is("EC4200"));
        assertThat(response.getErrorResponse().getErrorMessage(), is("I am an error message"));
        assertThat(response.getErrorResponse().getErrorDetails(), is("And I am an error detail"));
        assertThat(response.getErrorResponse().getConsumerMessage(), is("You should display this message to consumers"));
        assertThat(response.getErrorResponse().getSuggestedAction(), is("And tell them to do what says here"));
    }
    
    @Test
    public void Should_parse_error_message_with_container() throws Exception {
        StatusResponse response = Invoke("ErrorResponse-Requester");
        
        Document res = messenger.getResponseDocument();
        
        assertThat(response.getIsError(), is(true));
        assertThat(response.getErrorResponse().getAdditionalInformation().getStatusCodeFirstLevel(),
            is("urn:oasis:names:tc:SAML:2.0:status:Requester"));
        assertThat(response.getErrorResponse().getAdditionalInformation().getStatusCodeSecondLevel(),
            is("urn:oasis:names:tc:SAML:2.0:status:RequestUnsupported"));
        assertThat(response.getErrorResponse().getAdditionalInformation().getStatusMessage(),
            is("More details here about the field causing the error"));
    }
    
    @Test
    public void Should_handle_invalid_acquirer_signature() throws Exception {
        StatusResponse response = Invoke("StatusResponse-InvalidAcquirerSig");
        
        Document res = messenger.getResponseDocument();
        
        assertThat(response.getIsError(), is(true));
        assertThat(response.getErrorResponse().getErrorMessage(), is("error occured"));
        assertThat(response.getErrorResponse().getAdditionalInformation(), nullValue());
    }
    
    @Test
    public void Should_handle_malformed_xml_response() throws Exception {
        StatusResponse response = Invoke("StatusResponse-Malformed");
        
        assertThat(response.getIsError(), is(true));
        assertThat(response.getErrorResponse().getErrorMessage(), is("error occured"));
        assertThat(response.getErrorResponse().getAdditionalInformation(), nullValue());
    }
    
    @Test
    public void Should_validate_message_without_container_with_other_status_than_success() throws Exception {
        StatusResponse response = Invoke("QA_StatusResponse-NoContainer-1");

        assertThat(response.getIsError(), is(false));
        assertThat(response.getErrorResponse(), nullValue());
        assertThat(response.getTransactionID(), is("1234123456789012"));
        assertThat(response.getStatus(), is("Open"));
    }

    @Test
    public void Should_parse_deprecatedbin() throws Exception {
        StatusResponse response = Invoke("StatusResponse-deprecatedbin");
        assertFalse("Response should not be error", response.getIsError());
        assertThat(response.getSamlResponse()
                           .getAttributes()
                           .get("urn:nl:bvn:bankid:1.0:consumer.deprecatedbin"), is("BANKa94a8fe5ccb19ba61c4c0873d391e987982fbbd4"));
    }
    
    @Test
    public void Should_validate_xml_signature_for_message_with_status_open() throws Exception {
        StatusResponse response = Invoke("QA_StatusResponse-Open-1");
        
        assertThat(response.getIsError(), is(true));
        assertThat(response.getErrorResponse().getErrorMessage(), is("Response should not have a BankId signature."));
    }
    
    @Test
    public void Should_invalidate_message_with_assertion_expired() throws Exception {
        StatusResponse response = Invoke("StatusResponse-AssertionExpired");
        
        assertThat(response.getTransactionID(), is("1234123456789012"));
        assertThat(response.getStatus(), is(StatusResponse.Success));
        
        assertThat(response.getSamlResponse().getStatus(), notNullValue());
        assertThat(response.getSamlResponse().getStatus().getStatusCodeFirstLevel(),
            is("urn:oasis:names:tc:SAML:2.0:status:Requester"));
        assertThat(response.getSamlResponse().getStatus().getStatusCodeSecondLevel(),
            is("urn:oasis:names:tc:SAML:2.0:status:RequestDenied"));
        assertThat(response.getSamlResponse().getStatus().getStatusMessage(),
            is("Assertion expired."));
        
        assertThat(response.getIsError(), is(true));
        assertThat(response.getErrorResponse(), notNullValue());
        assertThat(response.getErrorResponse().getErrorMessage(), is("SAML specific error."));
    }
    
    private StatusResponse Invoke(String message) throws Exception
    {
        messenger = new TestMessenger(message);
        
        setupConfiguration();
        new NonStrictExpectations() {{
            config.getMerchantSubID(); result = 42;
        }};

        Communicator comm = new Communicator();
        return comm.getResponse(new StatusRequest("1234000000002144"));
    }
}
