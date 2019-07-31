package net.bankid.merchant.library;

import java.io.IOException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
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
    
	private HttpURLConnection getConnection(URI url, Configuration config)
			throws IOException, KeyManagementException, NoSuchAlgorithmException {
		String conUrlStr = url.toString();
		URL conUrl = new URL(conUrlStr);

		HttpURLConnection con = null;
		if (isProxyConnection(config)) {
			logger.Log(config, "using proxy connection");
			Proxy proxy = new Proxy(Proxy.Type.HTTP,
					new InetSocketAddress(config.getProxyHost(), config.getProxyPort()));
			con = (HttpURLConnection) conUrl.openConnection(proxy);
			if (isProxyAutorizationRequired(config)) {
				logger.Log(config, "using proxy authorisation");
				String authorization = config.getProxyAuthorizationType() + " "
						+ config.getProxyAuthorizationCredentials();
				con.setRequestProperty("Proxy-Authorization", authorization);
			}
		} else {
			con = (HttpURLConnection) conUrl.openConnection();
		}

		if (conUrlStr.startsWith("https://")) {

			if (config.isTls12Enabled()) {
				SSLContext sc = SSLContext.getInstance("TLSv1.2");
				sc.init(null, null, new java.security.SecureRandom());
				((HttpsURLConnection) con).setSSLSocketFactory(sc.getSocketFactory());
			}
		}

		return con;
	}
    
    @Override
    public String sendMessage(Configuration config, String message, URI url) throws CommunicatorException {
        try {
			HttpURLConnection con = getConnection(url, config);
            
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
    
	private boolean isProxyConnection(Configuration config) {
		return config.getProxyHost() != null && !config.getProxyHost().trim().isEmpty()
				&& config.getProxyPort() != 0;
	}
	
	private boolean isProxyAutorizationRequired(Configuration config) {
		return config.getProxyAuthorizationType() != null && !config.getProxyAuthorizationType().trim().isEmpty()
				&& config.getProxyAuthorizationCredentials() != null
				&& !config.getProxyAuthorizationCredentials().trim().isEmpty();
	}
}
