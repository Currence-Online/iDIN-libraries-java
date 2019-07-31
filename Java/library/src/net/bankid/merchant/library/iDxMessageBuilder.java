package net.bankid.merchant.library;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import net.bankid.merchant.library.internal.DirectoryRequestBase;
import net.bankid.merchant.library.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import schemas.idx.AcquirerStatusReq;
import schemas.idx.AcquirerTrxReq;
import schemas.idx.DirectoryReq;
import schemas.idx.TransactionContainer;

class iDxMessageBuilder {
    private final static String ProductID = "NL:BVN:BankID:1.0";
    private final static String Version = "1.0.0";
    
    public iDxMessageBuilder() {
    }
    
    public String getDirectoryRequest(final Configuration config, final DirectoryRequestBase directoryReq)
        throws DatatypeConfigurationException, JAXBException, CommunicatorException {
        
        DirectoryReq dirReq = new DirectoryReq() {{
            productID = ProductID;
            version = Version;
            createDateTimestamp = Utils.toUtc(directoryReq.getCreated());
            merchant = new DirectoryReq.Merchant() {{
                merchantID = config.getMerchantID();
                subID = config.getMerchantSubID();
            }};
        }};
        
        Validator.validate(dirReq);
        
        return Utils.serialize(dirReq, schemas.idx.DirectoryReq.class);
    }
    
    public String getTransactionRequest(final Configuration config, final AuthenticationRequest transactionReq, final Element containedData)
        throws TransformerConfigurationException, ParserConfigurationException, SAXException, IOException, TransformerException, DatatypeConfigurationException, JAXBException, CommunicatorException {
        
        AcquirerTrxReq trxReq = new AcquirerTrxReq() {{
            productID = ProductID;
            version = Version;
            createDateTimestamp = Utils.toUtc(transactionReq.getCreated());
            merchant = new AcquirerTrxReq.Merchant() {{
                merchantID = config.getMerchantID();
                subID = config.getMerchantSubID();
                merchantReturnURL = config.getMerchantReturnUrl();
            }};
            issuer = new AcquirerTrxReq.Issuer() {{
               issuerID = transactionReq.getIssuerID(); 
            }};
            transaction = new AcquirerTrxReq.Transaction() {{
                entranceCode = transactionReq.getEntranceCode();
                expirationPeriod = transactionReq.getExpirationPeriod();
                language = transactionReq.getLanguage();
                container = new TransactionContainer();
            }};
        }};
        
        Validator.validate(trxReq);
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document doc = factory.newDocumentBuilder().parse(new ByteArrayInputStream(
            Utils.serialize(trxReq, schemas.idx.AcquirerTrxReq.class).getBytes())
        );
        Node node = doc.importNode(containedData, true);
        doc.getElementsByTagName("container").item(0).appendChild(node);

        return Utils.serialize(doc);
    }
    
    public String getStatusRequest(final Configuration config, final StatusRequest statusReq)
        throws DatatypeConfigurationException, JAXBException, CommunicatorException {
        
        AcquirerStatusReq stsReq = new AcquirerStatusReq() {{
            productID = ProductID;
            version = Version;
            createDateTimestamp = Utils.toUtc(statusReq.getCreated());
            merchant = new AcquirerStatusReq.Merchant() {{
                merchantID = config.getMerchantID();
                subID = config.getMerchantSubID();
            }};
            transaction = new AcquirerStatusReq.Transaction() {{
                transactionID = statusReq.getTransactionID();
            }};
        }};
        
        Validator.validate(stsReq);
        
        return Utils.serialize(stsReq, schemas.idx.AcquirerStatusReq.class);
    }
}
