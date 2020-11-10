package net.bankid.merchant.library.tests;

import net.bankid.merchant.library.CommunicatorException;
import net.bankid.merchant.library.Configuration;
import net.bankid.merchant.library.IMessenger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URI;

public class TestMessenger implements IMessenger {
    private String request;
    private String response;
    private Document requestDoc;
    private Document responseDoc;
    
    public TestMessenger(String response) throws Exception {
        this.request = "";
        
        InputStream in = getClass().getResourceAsStream("/messages/" + response + ".xml");
        StringBuilder out = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        for(String line = br.readLine(); line != null; line = br.readLine()) 
          out.append(line + "\n");
        br.close();
        
        this.response = out.toString();
    }
    
    @Override
    public String sendMessage(Configuration config, String message, URI url) throws CommunicatorException {
        this.request = message;
        return response;
    }
    
    public Document getRequestDocument() throws Exception {
        if (requestDoc == null) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            requestDoc = dbf.newDocumentBuilder()
                .parse(new InputSource(new StringReader(request)));
        }
        return requestDoc;
    }
    
     public Document getResponseDocument() throws Exception {
        if (responseDoc == null) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            responseDoc = dbf.newDocumentBuilder()
                .parse(new InputSource(new StringReader(response)));
        }
        return responseDoc;
    }

    /**
     * @return the request
     */
    public String getRequest() {
        return request;
    }

    /**
     * @param request the request to set
     */
    public void setRequest(String request) {
        this.request = request;
    }

    /**
     * @return the response
     */
    public String getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(String response) {
        this.response = response;
    }
    
}
