package net.bankid.merchant.library;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

class Messenger implements IMessenger {
    private final ILogger logger;
    
    public Messenger(Configuration config) {
        logger = config.getLoggerFactory().create();
    }
    
    private HttpURLConnection getConnection(URI url, boolean isTls12Enabled) throws IOException, KeyManagementException, NoSuchAlgorithmException
    {
        if(url.toString().startsWith("https://"))
        {
            HttpsURLConnection con = (HttpsURLConnection) new URL(url.toString()).openConnection();
            if(isTls12Enabled)
            {
                SSLContext sc = SSLContext.getInstance("TLSv1.2");
                sc.init(null,null,new java.security.SecureRandom());
                con.setSSLSocketFactory(sc.getSocketFactory());
            }
            return con;
        }
        else
        {
            HttpURLConnection con = (HttpURLConnection) new URL(url.toString()).openConnection();
            return con;
        }
    }
    
    @Override
    public String sendMessage(Configuration config, String message, URI url) throws CommunicatorException {
        try {
            HttpURLConnection con = getConnection(url, config.isTls12Enabled());
            
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            con.setDoInput(true);
            con.setDoOutput(true);
            
            con.getOutputStream().write(message.getBytes());
            
            logger.Log(config, "result status:" + con.getResponseCode());
            
            String response = Utils.copy(con.getInputStream());
            
            return response;
        } catch (IOException ex) {
            throw new CommunicatorException("error sending message", ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(Messenger.class.getName()).log(Level.SEVERE, null, ex);
            throw new CommunicatorException("error sending message", ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Messenger.class.getName()).log(Level.SEVERE, null, ex);
            throw new CommunicatorException("error sending message", ex);
        }
    }
}
