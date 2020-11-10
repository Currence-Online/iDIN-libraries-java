package net.bankid.merchant.library.tests;

import javax.xml.datatype.DatatypeFactory;
import mockit.NonStrictExpectations;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import net.bankid.merchant.library.*;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Communicator_NewAuthentication_Test extends TestBaseClass {
    public Communicator_NewAuthentication_Test() throws Exception {
        messenger = new TestMessenger("TransactionResponse-OK-1");
    }
    
    @Test
    public void Should_generate_correct_idx_request() throws Exception
    {
        setupConfiguration();
        new NonStrictExpectations() {{
            config.getMerchantSubID(); result = 42;
        }};

        Communicator comm = new Communicator();
        comm.newAuthenticationRequest(new AuthenticationRequest("1", ServiceIds.None, "INGBNL2A") {{
            expirationPeriod = DatatypeFactory.newInstance().newDuration(2 * 60 * 1000); // 2 minutes
            language = "nl";
        }});
        
        Document req = messenger.getRequestDocument();
        
        assertThat(req.getDocumentElement().getAttribute("version"), is("1.0.0"));
        assertThat(req.getDocumentElement().getAttribute("productID"), is("NL:BVN:BankID:1.0"));
        assertThat(getTextContent(req, NameBVN, "createDateTimestamp"), matchesRegexp(dateTimeRegexp));
        
        assertThat(getTextContent(req, NameBVN, "issuerID"), is("INGBNL2A"));
        assertThat(getTextContent(req, NameBVN, "merchantID"), is("1234567890"));
        assertThat(getTextContent(req, NameBVN, "subID"), is("42"));
        assertThat(getTextContent(req, NameBVN, "merchantReturnURL"), is(merchantReturnUrl.toString()));
        assertThat(getTextContent(req, NameBVN, "expirationPeriod"), is("P0Y0M0DT0H2M0.000S"));
        assertThat(getTextContent(req, NameBVN, "language"), is("nl"));
        assertThat(getTextContent(req, NameBVN, "entranceCode"), is("1"));
        
        assertThat(getTextContent(req, NameBVN, "container"), notNullValue());
        assertThat(req.getElementsByTagNameNS("*", "Signature").getLength(), is(1));
    }
    
    @Test
    public void Should_generate_correct_saml_request() throws Exception {
        setupConfiguration();
        new NonStrictExpectations() {{
            config.getMerchantSubID(); result = 42;
        }};
        
        Communicator comm = new Communicator();
        comm.newAuthenticationRequest(new AuthenticationRequest("1", ServiceIds.Address, "INGBNL2A", AssuranceLevel.Loa3, "nl", "abc"));
        Document req = messenger.getRequestDocument();
        
        Element authReq = (Element) req.getElementsByTagNameNS("*", "AuthnRequest").item(0);
        assertThat(authReq.getAttribute("ID"), is("abc"));
        assertThat(authReq.getAttribute("Version"), is("2.0"));
        assertThat(authReq.getAttribute("IssueInstant"), matchesRegexp(dateTimeRegexp));
        assertThat(authReq.getAttribute("Destination"), is(""));
        assertThat(authReq.getAttribute("ForceAuthn"), is("true"));
        assertThat(authReq.getAttribute("ProtocolBinding"), is("nl:bvn:bankid:1.0:protocol:iDx"));
        assertThat(authReq.getAttribute("AssertionConsumerServiceIndex"), is(""));
        assertThat(authReq.getAttribute("AssertionConsumerServiceURL"), is(merchantReturnUrl.toString()));
        assertThat(authReq.getAttribute("AttributeConsumingServiceIndex"), is("1024"));
        assertThat(authReq.getAttribute("ProviderName"), is(""));
        
        Element issuer = (Element) req.getElementsByTagNameNS("*", "Issuer").item(1);
        assertThat(issuer.getTextContent(), is("1234567890"));
        assertThat(issuer.getAttribute("NameQualifier"), is(""));
        assertThat(issuer.getAttribute("SPNameQualifier"), is(""));
        assertThat(issuer.getAttribute("Format"), is(""));
        assertThat(issuer.getAttribute("SPProviderID"), is(""));
        assertThat(issuer.getAttribute("Signature"), is(""));
        assertThat(issuer.getAttribute("Subject"), is(""));
        assertThat(issuer.getAttribute("NameIDPolicy"), is(""));
        
        Element reqAuthCtx = (Element) req.getElementsByTagNameNS("*", "RequestedAuthnContext").item(0);
        assertThat(reqAuthCtx.getAttribute("Comparison"), is("minimum"));
        assertThat(reqAuthCtx.getElementsByTagNameNS("*", "AuthnContextClassRef").item(0).getTextContent(), is("nl:bvn:bankid:1.0:loa3"));
        assertThat(reqAuthCtx.getElementsByTagNameNS("*", "AuthnContextDeclRef").item(0), nullValue());
    }
    
    @Test
    public void Should_parse_correct_response() throws Exception {
        setupConfiguration();
        new NonStrictExpectations() {{
            config.getMerchantSubID(); result = 42;
        }};
        
        Communicator comm = new Communicator();
        comm.newAuthenticationRequest(new AuthenticationRequest("1", ServiceIds.None, "INGBNL2A"));

        Document res = messenger.getResponseDocument();
        assertThat(res.getDocumentElement().getAttribute("version"), is("1.0.0"));
        assertThat(res.getDocumentElement().getAttribute("productID"), is("NL:BVN:BankID:1.0"));
        assertThat(getTextContent(res, NameBVN, "createDateTimestamp"), matchesRegexp(dateTimeRegexp));
        assertThat(getTextContent(res, NameBVN, "acquirerID"), is("1234"));
        assertThat(getTextContent(res, NameBVN, "issuerAuthenticationURL"), is("http://example.com"));
        assertThat(getTextContent(res, NameBVN, "transactionID"), is("1234000000002144"));
        assertThat(getTextContent(res, NameBVN, "acquirerID"), is("1234"));
        assertThat(getTextContent(res, NameBVN, "transactionCreateDateTimestamp"), matchesRegexp(dateTimeRegexp));
        
        assertThat(res.getElementsByTagNameNS("*", "Signature").getLength(), is(1));
    }
    
    @Test
    public void Should_parse_error_message_with_no_container() throws Exception
    {
        messenger = new TestMessenger("ErrorResponse-NoContainer");
        setupConfiguration();
        new NonStrictExpectations() {{
            config.getMerchantSubID(); result = 0;
        }};

        Communicator comm = new Communicator();
        AuthenticationResponse res =
            comm.newAuthenticationRequest(new AuthenticationRequest("1", ServiceIds.Address, "INGBNL2A"));
        
        assertThat(res.getIsError(), is(true));
        assertThat(res.getErrorResponse(), notNullValue());
        assertThat(res.getErrorResponse().getErrorCode(), is("EC4200"));
        assertThat(res.getErrorResponse().getErrorMessage(), is("I am an error message"));
        assertThat(res.getErrorResponse().getErrorDetails(), is("And I am an error detail"));
        assertThat(res.getErrorResponse().getConsumerMessage(), is("You should display this message to consumers"));
        assertThat(res.getErrorResponse().getSuggestedAction(), is("And tell them to do what says here"));
    }
    
    @Test
    public void Should_handle_invalid_acquirer_signature() throws Exception {
        messenger = new TestMessenger("TransactionResponse-InvalidAcquirerSig");
        setupConfiguration();
        new NonStrictExpectations() {{
            config.getMerchantSubID(); result = 42;
        }};

        Communicator comm = new Communicator();
        AuthenticationResponse res =
            comm.newAuthenticationRequest(new AuthenticationRequest("1", ServiceIds.Address, "INGBNL2A"));

        assertThat(res.getIsError(), is(true));
        assertThat(res.getErrorResponse().getErrorMessage(), is("error occured"));
        assertThat(res.getErrorResponse().getAdditionalInformation(), nullValue());
    }
    
    @Test
    public void Should_handle_malformed_xml_response() throws Exception {
        messenger = new TestMessenger("TransactionResponse-InvalidAcquirerSig");
        setupConfiguration();
        new NonStrictExpectations() {{
            config.getMerchantSubID(); result = 42;
        }};

        Communicator comm = new Communicator();
        AuthenticationResponse res =
            comm.newAuthenticationRequest(new AuthenticationRequest("1", ServiceIds.Address, "INGBNL2A"));
        
        assertThat(res.getIsError(), is(true));
        assertThat(res.getErrorResponse().getErrorMessage(), is("error occured"));
        assertThat(res.getErrorResponse().getAdditionalInformation(), nullValue());
    }
    
    @Test
    public void Should_validate_response_IssuerAuthenticationURL_format() throws Exception {
        AuthenticationResponse res = invoke("TransactionResponse-InvalidIssuerAuthenticationURL");

        assertThat(res.getIsError(), is(true));
        assertThat(res.getErrorResponse().getErrorMessage(), is("malformed url"));
    }
    
    @Test
    public void Should_accept_expiration_periods_lesser_than_5_minutes() throws Exception {
        setupConfiguration();
        new NonStrictExpectations() {{
            config.getMerchantSubID(); result = 42;
        }};

        Communicator comm = new Communicator();
        AuthenticationResponse res =
            comm.newAuthenticationRequest(
        new AuthenticationRequest("1", ServiceIds.None, "INGBNL2A") {{
            expirationPeriod = DatatypeFactory.newInstance().newDuration(4*60*1000 + 59*1000);
        }});
    }
    
    @Test
    public void Should_reject_expiration_periods_greater_than_5_minutes() throws Exception {
        setupConfiguration();
        new NonStrictExpectations() {{
            config.getMerchantSubID(); result = 42;
        }};

        Communicator comm = new Communicator();
        AuthenticationResponse res =
            comm.newAuthenticationRequest(new AuthenticationRequest("1", ServiceIds.None, "INGBNL2A") {{
            expirationPeriod = DatatypeFactory.newInstance().newDuration(5*60*1000 + 1*1000);
        }});
        
        assertThat(res.getIsError(), is(true));
    }
    
    @Test
    public void Should_reject_merchant_references_not_according_to_format() throws Exception {
        setupConfiguration();
        new NonStrictExpectations() {{
            config.getMerchantSubID(); result = 42;
        }};

        Communicator comm = new Communicator();
        AuthenticationResponse res =
            comm.newAuthenticationRequest(new AuthenticationRequest("1", ServiceIds.None, "INGBNL2A", AssuranceLevel.Loa3, "en", "2cocojambo"));
        assertThat(res.getIsError(), is(true));
        
        res =
            comm.newAuthenticationRequest(new AuthenticationRequest("1", ServiceIds.None, "INGBNL2A", AssuranceLevel.Loa3, "en", "ashdjasjkdhasjkhdajkshdjakhdjkashdka"));
        assertThat(res.getIsError(), is(true));
    }
    
    private void assertServiceIds(String expected, int value) throws Exception {
        invoke("TransactionResponse-OK-1", new AuthenticationRequest("1", value, "INGBNL2A"));
        Element el = (Element) messenger.getRequestDocument().getElementsByTagNameNS("*", "AuthnRequest").item(0);
        String v = el.getAttribute("AttributeConsumingServiceIndex");
        assertThat(v, is(expected));
    }
    
    @Test
    public void Should_serialize_the_service_ids_correctly() throws Exception {
        assertServiceIds("0", ServiceIds.None);
        assertServiceIds("0", ServiceIds.ConsumerTransientId);
        assertServiceIds("16", ServiceIds.Gender);
        assertServiceIds("64", ServiceIds.ConsumerTransientId | ServiceIds.IsEighteenOrOlder);
        assertServiceIds("80", ServiceIds.ConsumerTransientId | ServiceIds.IsEighteenOrOlder | ServiceIds.Gender);
        assertServiceIds("448", ServiceIds.ConsumerTransientId | ServiceIds.DateOfBirth);
        assertServiceIds("464", ServiceIds.ConsumerTransientId | ServiceIds.DateOfBirth | ServiceIds.Gender);
        assertServiceIds("1024", ServiceIds.ConsumerTransientId | ServiceIds.Address);
        assertServiceIds("1040", ServiceIds.ConsumerTransientId | ServiceIds.Address | ServiceIds.Gender);
        assertServiceIds("1088", ServiceIds.ConsumerTransientId | ServiceIds.Address | ServiceIds.IsEighteenOrOlder);
        assertServiceIds("1104", ServiceIds.ConsumerTransientId | ServiceIds.Address | ServiceIds.IsEighteenOrOlder | ServiceIds.Gender);
        assertServiceIds("1472", ServiceIds.ConsumerTransientId | ServiceIds.Address | ServiceIds.DateOfBirth);
        assertServiceIds("1488", ServiceIds.ConsumerTransientId | ServiceIds.Address | ServiceIds.DateOfBirth | ServiceIds.Gender);
        assertServiceIds("4096", ServiceIds.ConsumerTransientId | ServiceIds.Name);
        assertServiceIds("4112", ServiceIds.ConsumerTransientId | ServiceIds.Name | ServiceIds.Gender);
        assertServiceIds("4160", ServiceIds.ConsumerTransientId | ServiceIds.Name | ServiceIds.IsEighteenOrOlder);
        assertServiceIds("4176", ServiceIds.ConsumerTransientId | ServiceIds.Name | ServiceIds.IsEighteenOrOlder | ServiceIds.Gender);
        assertServiceIds("4544", ServiceIds.ConsumerTransientId | ServiceIds.Name | ServiceIds.DateOfBirth);
        assertServiceIds("4560", ServiceIds.ConsumerTransientId | ServiceIds.Name | ServiceIds.DateOfBirth | ServiceIds.Gender);
        assertServiceIds("5120", ServiceIds.ConsumerTransientId | ServiceIds.Name | ServiceIds.Address);
        assertServiceIds("5136", ServiceIds.ConsumerTransientId | ServiceIds.Name | ServiceIds.Address | ServiceIds.Gender);
        assertServiceIds("5184", ServiceIds.ConsumerTransientId | ServiceIds.Name | ServiceIds.Address | ServiceIds.IsEighteenOrOlder);
        assertServiceIds("5200", ServiceIds.ConsumerTransientId | ServiceIds.Name | ServiceIds.Address | ServiceIds.IsEighteenOrOlder | ServiceIds.Gender);
        assertServiceIds("5568", ServiceIds.ConsumerTransientId | ServiceIds.Name | ServiceIds.Address | ServiceIds.DateOfBirth);
        assertServiceIds("5584", ServiceIds.ConsumerTransientId | ServiceIds.Name | ServiceIds.Address | ServiceIds.DateOfBirth | ServiceIds.Gender);
        assertServiceIds("16384", ServiceIds.ConsumerBin);
        assertServiceIds("16400", ServiceIds.ConsumerBin | ServiceIds.Gender);
        assertServiceIds("16448", ServiceIds.ConsumerBin | ServiceIds.IsEighteenOrOlder);
        assertServiceIds("16464", ServiceIds.ConsumerBin | ServiceIds.IsEighteenOrOlder | ServiceIds.Gender);
        assertServiceIds("16832", ServiceIds.ConsumerBin | ServiceIds.DateOfBirth);
        assertServiceIds("16848", ServiceIds.ConsumerBin | ServiceIds.DateOfBirth | ServiceIds.Gender);
        assertServiceIds("17408", ServiceIds.ConsumerBin | ServiceIds.Address);
        assertServiceIds("17424", ServiceIds.ConsumerBin | ServiceIds.Address | ServiceIds.Gender);
        assertServiceIds("17472", ServiceIds.ConsumerBin | ServiceIds.Address | ServiceIds.IsEighteenOrOlder);
        assertServiceIds("17488", ServiceIds.ConsumerBin | ServiceIds.Address | ServiceIds.IsEighteenOrOlder | ServiceIds.Gender);
        assertServiceIds("17856", ServiceIds.ConsumerBin | ServiceIds.Address | ServiceIds.DateOfBirth);
        assertServiceIds("17872", ServiceIds.ConsumerBin | ServiceIds.Address | ServiceIds.DateOfBirth | ServiceIds.Gender);
        assertServiceIds("20480", ServiceIds.ConsumerBin | ServiceIds.Name);
        assertServiceIds("20496", ServiceIds.ConsumerBin | ServiceIds.Name | ServiceIds.Gender);
        assertServiceIds("20544", ServiceIds.ConsumerBin | ServiceIds.Name | ServiceIds.IsEighteenOrOlder);
        assertServiceIds("20560", ServiceIds.ConsumerBin | ServiceIds.Name | ServiceIds.IsEighteenOrOlder | ServiceIds.Gender);
        assertServiceIds("20928", ServiceIds.ConsumerBin | ServiceIds.Name | ServiceIds.DateOfBirth);
        //assertServiceIds("20929", ServiceIds.ConsumerBin | ServiceIds.Name | ServiceIds.DateOfBirth);
        assertServiceIds("20944", ServiceIds.ConsumerBin | ServiceIds.Name | ServiceIds.DateOfBirth | ServiceIds.Gender);
        assertServiceIds("21504", ServiceIds.ConsumerBin | ServiceIds.Name | ServiceIds.Address);
        assertServiceIds("21520", ServiceIds.ConsumerBin | ServiceIds.Name | ServiceIds.Address | ServiceIds.Gender);
        assertServiceIds("21568", ServiceIds.ConsumerBin | ServiceIds.Name | ServiceIds.Address | ServiceIds.IsEighteenOrOlder);
        assertServiceIds("21584", ServiceIds.ConsumerBin | ServiceIds.Name | ServiceIds.Address | ServiceIds.IsEighteenOrOlder | ServiceIds.Gender);
        assertServiceIds("21952", ServiceIds.ConsumerBin | ServiceIds.Name | ServiceIds.Address | ServiceIds.DateOfBirth);
        assertServiceIds("21968", ServiceIds.ConsumerBin | ServiceIds.Name | ServiceIds.Address | ServiceIds.DateOfBirth | ServiceIds.Gender);
    }
    
    @Test
    public void Should_reject_invalid_service_id_combinations() throws Exception {
        AuthenticationResponse res = invoke("TransactionResponse-OK-1", new AuthenticationRequest("1", 666, "INGBNL2A"));
        
        assertThat(res.getIsError(), is(true));
    }
    
    private AuthenticationResponse invoke(String response, AuthenticationRequest request) throws Exception {
        messenger = new TestMessenger(response);
        setupConfiguration();

        Communicator comm = new Communicator();
        AuthenticationResponse res =
            comm.newAuthenticationRequest(request);
        return comm.newAuthenticationRequest(request);
    }
    
    private AuthenticationResponse invoke(String response) throws Exception {
        return invoke(response, new AuthenticationRequest("1", ServiceIds.None, "INGBNL2A"));
    }
}
