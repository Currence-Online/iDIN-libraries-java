package net.bankid.merchant.library.tests;

import javax.xml.datatype.DatatypeFactory;
import mockit.NonStrictExpectations;
import static org.hamcrest.CoreMatchers.*;

import net.bankid.merchant.library.Communicator;
import net.bankid.merchant.library.DirectoryResponse;
import net.bankid.merchant.library.TestBaseClass;
import org.junit.Test;
import static org.junit.Assert.*;

import org.w3c.dom.Document;

public final class Communicator_GetDirectory_Test extends TestBaseClass {
    public Communicator_GetDirectory_Test() throws Exception {
        messenger = new TestMessenger("DirectoryResponse-OK-3");
    }
    
    @Test
    public void Should_generate_correct_request() throws Exception {
        setupConfiguration();
        new NonStrictExpectations() {{
            config.getMerchantSubID(); result = 42;
        }};
        
        Communicator comm = new Communicator();
        comm.getDirectory();
        
        Document req = messenger.getRequestDocument();
        
        assertThat(req.getDocumentElement().getAttribute("version"), is("1.0.0"));
        assertThat(req.getDocumentElement().getAttribute("productID"), is("NL:BVN:BankID:1.0"));
        assertThat(getTextContent(req, NameBVN, "createDateTimestamp"), matchesRegexp(dateTimeRegexp));
        assertThat(getTextContent(req, NameBVN, "merchantID"), is("1234567890"));
        assertThat(getTextContent(req, NameBVN, "subID"), is("42"));
        assertThat(req.getElementsByTagNameNS("*", "Signature").getLength(), is(1));
    }
    
    @Test
    public void Should_use_default_value_for_subId_if_not_specified() throws Exception {
        setupConfiguration();
        new NonStrictExpectations() {{
            config.getMerchantSubID(); result = 0;
        }};
        
        Communicator comm = new Communicator();
        comm.getDirectory();
        
        Document req = messenger.getRequestDocument();
        
        assertThat(req.getDocumentElement().getAttribute("version"), is("1.0.0"));
        assertThat(req.getDocumentElement().getAttribute("productID"), is("NL:BVN:BankID:1.0"));
        assertThat(getTextContent(req, NameBVN, "createDateTimestamp"), matchesRegexp(dateTimeRegexp));
        assertThat(getTextContent(req, NameBVN, "merchantID"), is("1234567890"));
        assertThat(getTextContent(req, NameBVN, "subID"), is("0"));
        assertThat(req.getElementsByTagNameNS("*", "Signature").getLength(), is(1));
    }
    
    @Test
    public void Should_parse_correct_response() throws Exception {
        setupConfiguration();
        new NonStrictExpectations() {{
            config.getMerchantSubID(); result = 0;
        }};
        
        Communicator comm = new Communicator();
        DirectoryResponse res = comm.getDirectory();
        
        assertThat(res.getIsError(), is(false));
        assertThat(res.getErrorResponse(), nullValue());
        assertThat(res.getDirectoryDateTimestamp(), is(
            DatatypeFactory.newInstance().newXMLGregorianCalendar(2013, 12, 31, 23, 0, 0, 0, 0)));
        assertThat(res.getIssuers().size(), is(3));
        
        assertThat(res.getIssuers().get(0).getIssuerCountry(), is("Netherlands"));
        assertThat(res.getIssuers().get(0).getIssuerID(), is("INGBNL2A"));
        assertThat(res.getIssuers().get(0).getIssuerName(), is("NL BANK A"));
        
        assertThat(res.getIssuers().get(1).getIssuerCountry(), is("Netherlands"));
        assertThat(res.getIssuers().get(1).getIssuerID(), is("INGBNL2B"));
        assertThat(res.getIssuers().get(1).getIssuerName(), is("NL BANK B"));
        
        assertThat(res.getIssuers().get(2).getIssuerCountry(), is("Romania"));
        assertThat(res.getIssuers().get(2).getIssuerID(), is("INGBRO2A"));
        assertThat(res.getIssuers().get(2).getIssuerName(), is("RO BANK A"));
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
        DirectoryResponse res = comm.getDirectory();
        
        assertThat(res.getIsError(), is(true));
        assertThat(res.getErrorResponse(), notNullValue());
        assertThat(res.getErrorResponse().getErrorCode(), is("EC4200"));
        assertThat(res.getErrorResponse().getErrorMessage(), is("I am an error message"));
        assertThat(res.getErrorResponse().getErrorDetails(), is("And I am an error detail"));
        assertThat(res.getErrorResponse().getConsumerMessage(), is("You should display this message to consumers"));
        assertThat(res.getErrorResponse().getSuggestedAction(), is("And tell them to do what says here"));
    }
}