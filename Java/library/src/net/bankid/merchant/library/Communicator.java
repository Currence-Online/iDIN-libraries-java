package net.bankid.merchant.library;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import javax.xml.bind.JAXBException;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import net.bankid.merchant.library.internal.DirectoryRequestBase;

/**
 * Communicator class, to be used for sending messages where LocalInstrumentationCode = CORE
 */
public class Communicator {
    /**
     * Logger instance, to be used for logging iso pain raw messages and library messages.
     */
    protected ILogger logger;

    /**
     * XmlProcessor instance, used to process XMLs (signing, verifying, validating signature).
     */
    protected XmlProcessor xmlProcessor;
    
    protected IMessenger messenger;
    
    /**
     * Configuration instance
     */
    protected Configuration config;

    /**
     * Constructs a new Communicator, initializes the Configuration and sets LocalInstrumentCode = CORE
     */
    public Communicator() {
        this.config = Configuration.defaultInstance();
        initialize();
    }
    
    public Communicator(Configuration config){
        this.config = config;
        initialize();
    }
    
    private void initialize(){
        logger = (config.getLoggerFactory() != null)?
                config.getLoggerFactory().create() : new LoggerFactory().create();
        xmlProcessor = new XmlProcessor(config);
        messenger = config.getMessenger();
    }
    
    /**
     * Sends a directory request to the URL specified in Configuration.AcquirerUrl_DirectoryReq.
     * @return A DirectoryResponse object which contains the response from the server (a list of issuers), or error
               information when an error occurs
     */
    public DirectoryResponse getDirectory() {
        try {
            logger.Log(config, "sending new directory request");
            
            DirectoryRequestBase directoryReq = new DirectoryRequestBase();
            String xml = new iDxMessageBuilder().getDirectoryRequest(config, directoryReq);
            xml = xmlProcessor.AddSignature(config, xml);

            DirectoryResponse dr = DirectoryResponse.Parse(
                performRequest(xml, config.getAcquirerDirectoryURL())
            );
            
            return dr;
        }
        catch (DatatypeConfigurationException | JAXBException | CommunicatorException ex) {
            ex.printStackTrace();
            logger.Log(config, ex.getMessage());
            return DirectoryResponse.Get(ex);
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | UnrecoverableEntryException | InvalidAlgorithmParameterException | ParserConfigurationException | MarshalException | SAXException | XMLSignatureException | TransformerException ex) {
            ex.printStackTrace();
            logger.Log(config, ex.getMessage());
            return DirectoryResponse.Get(ex);
        }
    }
    
    String performRequest(String xml, String url) throws CommunicatorException {
        try {
            logger.Log(config, "sending request to " + url);
            
            if (!xmlProcessor.VerifySchema(config, xml)) {
                logger.Log(config, "request xml schema is not valid");
                throw new CommunicatorException("request xml schema not valid");
            }
            
            logger.LogXmlMessage(config, xml);

            logger.Log(config, "creating http(s) client");
            
            String response = messenger.sendMessage(config, xml, new URI(url));
            
            logger.LogXmlMessage(config, response);
            
            if (!xmlProcessor.VerifySchema(config, response)) {
                logger.Log(config, "response xml schema is not valid");
                throw new CommunicatorException("response xml schema not valid");
            }
            
            if (!xmlProcessor.VerifySignature(config, response)) {
                logger.Log(config, "response xml signature not valid");
                throw new CommunicatorException("response xml signature not valid");
            }
            
            return response;
        }
        catch (IOException | IllegalStateException | ParserConfigurationException | SAXException ex) {
            logger.Log(config, ex.getMessage());
            throw new CommunicatorException("error occured", ex);
        } catch (MarshalException | XMLSignatureException ex) {
            logger.Log(config, ex.getMessage());
            throw new CommunicatorException("error occured", ex);
        } catch (URISyntaxException ex) {
            logger.Log(config, ex.getMessage());
            throw new CommunicatorException("error occured", ex);
        }
    }
    
    /**
     * Sends a transaction request to the URL specified in Configuration.AcquirerUrl_TransactionReq.
     * @param transactionRequest A NewAuthenticationRequest object.
     * @return A NewAuthenticationResponse object which contains the response from the server (transaction id, issuer authentication URL),
               or error information when an error occurs
     */
    public AuthenticationResponse newAuthenticationRequest(AuthenticationRequest transactionRequest) {
        try {
            logger.Log(config, "sending transaction request");
            Element bankidElement = new BankIdMessageBuilder().getTransaction(config, transactionRequest);
            String xml = new iDxMessageBuilder().getTransactionRequest(config, transactionRequest, bankidElement);
            xml = xmlProcessor.AddSignature(config, xml);

            AuthenticationResponse nmr = AuthenticationResponse.Parse(
                performRequest(xml, config.getAcquirerTransactionURL())
            );

            return nmr;
        }
        catch (ParserConfigurationException | SAXException | IOException | TransformerException | CommunicatorException ex) {
            logger.Log(config, ex.getMessage());
            return AuthenticationResponse.Get(ex);
        } catch (DatatypeConfigurationException | JAXBException ex) {
            logger.Log(config, ex.getMessage());
            return AuthenticationResponse.Get(ex);
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | UnrecoverableEntryException | InvalidAlgorithmParameterException | MarshalException | XMLSignatureException ex) {
            logger.Log(config, ex.getMessage());
            return AuthenticationResponse.Get(ex);
        }
    }
    
    /**
     * Sends a status request to the URL specified in Configuration.AcquirerUrl_StatusReq.
     * @param statusRequest A StatusRequest object
     * @return A StatusResponse object which contains the response from the server (transaction id, status message), or
               error information when an error occurs.
     */
    public StatusResponse getResponse(StatusRequest statusRequest) {
        try {
            logger.Log(config, "sending status request");
            String xml = new iDxMessageBuilder().getStatusRequest(config, statusRequest);
            xml = xmlProcessor.AddSignature(config, xml);

            StatusResponse sr = StatusResponse.Parse(config, performRequest(xml, config.getAcquirerStatusURL())
            );

            return sr;
        }
        catch (CommunicatorException ex) {
            logger.Log(config, ex.getMessage());
            return StatusResponse.Get(ex);
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | UnrecoverableEntryException | InvalidAlgorithmParameterException | ParserConfigurationException | MarshalException | SAXException | XMLSignatureException | TransformerException ex) {
            logger.Log(config, ex.getMessage());
            return StatusResponse.Get(ex);
        } catch (DatatypeConfigurationException | JAXBException ex) {
            logger.Log(config, ex.getMessage());
            return StatusResponse.Get(ex);
        }
    }
}
