
package net.bankid.merchant.library;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

class Utils {
    public static XMLGregorianCalendar toUtc(Date date) throws DatatypeConfigurationException {
        GregorianCalendar currentDateTimestamp = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        currentDateTimestamp.setTime(date);

        return DatatypeFactory.newInstance().newXMLGregorianCalendar(currentDateTimestamp);
    }
    
    public static String serialize(Object o, Class... classes) throws CommunicatorException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(classes);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.setProperty(Marshaller.JAXB_FRAGMENT, false);
        m.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
        
        StringWriter sw = new StringWriter();
        m.marshal(o, sw);
        
        return sw.toString();
    }
    
    public static String serialize(Document doc) throws TransformerConfigurationException, TransformerException {
        StringWriter sw = new StringWriter();
        Transformer trans = TransformerFactory.newInstance().newTransformer();
        
        doc.setXmlStandalone(true);
        trans.transform(new DOMSource(doc), new StreamResult(sw));

        return sw.toString();
    }
    
    public static <T> T deserialize(String xml, Class<T> type) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(type);
        Unmarshaller u = context.createUnmarshaller();
        
        Object o = u.unmarshal(new StringReader(xml));
        return type.cast(o);
    }
    
    public static <T> T deserialize(Node node, Class<T> type) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(type);
        Unmarshaller u = context.createUnmarshaller();
        JAXBElement el = (JAXBElement) u.unmarshal(node);
        
        Object o = el.getValue();
        return type.cast(o);
    }
    
    public static String sha1Hex(final byte[] data) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        sha1.update(data);
        byte fp[] = sha1.digest();
        String fingerprint = "";
        for (int i = 0; i < fp.length; i++) {
            String f = "00" + Integer.toHexString(fp[i]);
            fingerprint = fingerprint + f.substring(f.length() - 2);
        }
        fingerprint = fingerprint.toUpperCase();
        
        return fingerprint;
    }
    
    public static String copy(InputStream input) throws IOException {
        StringBuilder sb = new StringBuilder();
        int n = 0;
        while (-1 != (n = input.read())) {
            sb.append((char)n);
        }
        return sb.toString();
    }
}
