package net.bankid.merchant.library;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Configuration class
 */
public class Configuration {
    private String merchantID;
    private int merchantSubID;
    private String merchantReturnUrl;
    
    private String acquirerDirectoryURL;
    private String acquirerTransactionURL;
    private String acquirerStatusURL;
   
    private String keyStoreLocation;
    private String keyStorePassword;
   
    private String merchantCertificateAlias;
    private String merchantCertificatePassword;
    private String acquirerCertificateAlias;
    private String acquirerAlternateCertificateAlias;
    private String samlCertificateAlias;
    private String samlCertificatePassword;
    
    private boolean logsEnabled;
    private boolean serviceLogsEnabled;
    private String serviceLogsLocation;
    private String serviceLogsPattern;
    private ILoggerFactory loggerFactory;
    
    private IMessenger messenger;

    private IKeyProviderFactory keyProviderFactory;
    private IKeyProvider keyProvider;

    private boolean tls12Enabled;
    
    private static Configuration instance;
    
    /**
     * Gets the default Configuration instance
     * @return the default Configuration instance
     */
    public static Configuration defaultInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        
        return instance;
    }
    
    public Configuration() {
        this.keyProviderFactory = new KeyStoreKeyProviderFactory();
        this.keyProvider = this.keyProviderFactory.create(this);
    }
    
    /**
     * Constructor that highlights all required fields for this object.
     * @param merchantID ID of the Merchant
     * @param merchantSubID The SubID that uniquely defines a trade name of the Merchant to be used for display
     * @param merchantReturnUrl A valid URL to which the user is redirected, after the transaction has been authorized.
     * @param keyStoreLocation A Java keystore (file on the disk) that stores the certificates
     * @param keyStorePassword The password used to access the keystore
     * @param merchantCertificateAlias A string which specifies the alias of the merchant certificate.
     * @param merchantCertificatePassword The password of the private key of the signing certificate
     * @param acquirerCertificateAlias A string which specifies the alias of the acquirer
     * @param acquirerUrl_DirectoryReq The URL to which the library sends Directory request messages
     * @param acquirerUrl_TransactionReq The URL to which the library sends Transaction messages.
     * @param acquirerUrl_StatusReq The URL to which the library sends Status messages.
     * @param logsEnabled This tells the library that it should output debug logging messages.
     * @param tls12Enabled This tells the library that should use tls 1.2.
     * @param serviceLogsEnabled This tells the library that it should save ISO pain raw messages or not. Default is true.
     * @param serviceLogsLocation A directory on the disk where the library saves ISO pain raw messages.
     * @param serviceLogsPattern A string that describes a pattern to distinguish the ISO pain raw messages.
     * @param loggerFactory ILoggerFactory instance that is used to create ILogger object.
     */
    public Configuration(String merchantID, int merchantSubID, String merchantReturnUrl, String keyStoreLocation,
            String keyStorePassword, String merchantCertificateAlias, String merchantCertificatePassword, String acquirerCertificateAlias,
            String acquirerUrl_DirectoryReq, String acquirerUrl_TransactionReq, String acquirerUrl_StatusReq, boolean logsEnabled, boolean serviceLogsEnabled,
            String serviceLogsLocation, String serviceLogsPattern, boolean tls12Enabled, ILoggerFactory loggerFactory) {
        this.merchantID = merchantID;
        this.merchantSubID = merchantSubID;
        this.merchantReturnUrl = merchantReturnUrl;
        this.keyStoreLocation = keyStoreLocation;
        this.keyStorePassword = keyStorePassword;
        this.merchantCertificateAlias = merchantCertificateAlias;
        this.merchantCertificatePassword = merchantCertificatePassword;
        this.acquirerCertificateAlias = acquirerCertificateAlias;
        this.acquirerDirectoryURL = acquirerUrl_DirectoryReq;
        this.acquirerTransactionURL = acquirerUrl_TransactionReq;
        this.acquirerStatusURL = acquirerUrl_StatusReq;
        this.logsEnabled = logsEnabled;
        this.tls12Enabled = tls12Enabled;
        this.serviceLogsEnabled = serviceLogsEnabled;
        this.serviceLogsLocation = serviceLogsLocation;
        this.serviceLogsPattern = serviceLogsPattern;
        this.loggerFactory = loggerFactory != null? loggerFactory : new LoggerFactory();
        this.keyProviderFactory = new KeyStoreKeyProviderFactory();
        this.keyProvider = keyProviderFactory.create(this);
    }
    
    /**
     * Constructor that highlights all required fields for this object.
     * @param merchantID ID of the Merchant
     * @param merchantSubID The SubID that uniquely defines a trade name of the Merchant to be used for display
     * @param merchantReturnUrl A valid URL to which the user is redirected, after the transaction has been authorized.
     * @param keyStoreLocation A Java keystore (file on the disk) that stores the certificates
     * @param keyStorePassword The password used to access the keystore
     * @param merchantCertificateAlias A string which specifies the alias of the merchant certificate.
     * @param merchantCertificatePassword The password of the private key of the signing certificate
     * @param acquirerCertificateAlias A string which specifies the alias of the acquirer certificate
     * @param acquirerAlternateCertificateAlias A string which specifies the alias of the acquirer alternate certificate
     * @param acquirerUrl_DirectoryReq The URL to which the library sends Directory request messages
     * @param acquirerUrl_TransactionReq The URL to which the library sends Transaction messages.
     * @param acquirerUrl_StatusReq The URL to which the library sends Status messages.
     * @param logsEnabled This tells the library that it should output debug logging messages.
     * @param tls12Enabled This tells the library that should use tls 1.2.
     * @param serviceLogsEnabled This tells the library that it should save ISO pain raw messages or not. Default is true.
     * @param serviceLogsLocation A directory on the disk where the library saves ISO pain raw messages.
     * @param serviceLogsPattern A string that describes a pattern to distinguish the ISO pain raw messages.
     * @param loggerFactory ILoggerFactory instance that is used to create ILogger object.
     */
    public Configuration(String merchantID, int merchantSubID, String merchantReturnUrl, String keyStoreLocation,
            String keyStorePassword, String merchantCertificateAlias, String merchantCertificatePassword, String acquirerCertificateAlias,String acquirerAlternateCertificateAlias,
            String acquirerUrl_DirectoryReq, String acquirerUrl_TransactionReq, String acquirerUrl_StatusReq, boolean logsEnabled, boolean serviceLogsEnabled,
            String serviceLogsLocation, String serviceLogsPattern, boolean tls12Enabled, ILoggerFactory loggerFactory) {
        this.merchantID = merchantID;
        this.merchantSubID = merchantSubID;
        this.merchantReturnUrl = merchantReturnUrl;
        this.keyStoreLocation = keyStoreLocation;
        this.keyStorePassword = keyStorePassword;
        this.merchantCertificateAlias = merchantCertificateAlias;
        this.merchantCertificatePassword = merchantCertificatePassword;
        this.acquirerCertificateAlias = acquirerCertificateAlias;
        this.acquirerAlternateCertificateAlias = acquirerAlternateCertificateAlias;
        this.acquirerDirectoryURL = acquirerUrl_DirectoryReq;
        this.acquirerTransactionURL = acquirerUrl_TransactionReq;
        this.acquirerStatusURL = acquirerUrl_StatusReq;
        this.logsEnabled = logsEnabled;
        this.tls12Enabled = tls12Enabled;
        this.serviceLogsEnabled = serviceLogsEnabled;
        this.serviceLogsLocation = serviceLogsLocation;
        this.serviceLogsPattern = serviceLogsPattern;
        this.loggerFactory = loggerFactory != null? loggerFactory : new LoggerFactory();
        this.keyProviderFactory = new KeyStoreKeyProviderFactory();
        this.keyProvider = keyProviderFactory.create(this);
    }

    /**
     * Constructor that highlights all required fields for this object.
     * @param merchantID ID of the Merchant
     * @param merchantSubID The SubID that uniquely defines a trade name of the Merchant to be used for display
     * @param merchantReturnUrl A valid URL to which the user is redirected, after the transaction has been authorized.
     * @param keyStoreLocation A Java keystore (file on the disk) that stores the certificates
     * @param keyStorePassword The password used to access the keystore
     * @param merchantCertificateAlias A string which specifies the alias of the merchant certificate.
     * @param merchantCertificatePassword The password of the private key of the signing certificate
     * @param acquirerCertificateAlias A string which specifies the alias of the acquirer
     * @param acquirerUrl_DirectoryReq The URL to which the library sends Directory request messages
     * @param acquirerUrl_TransactionReq The URL to which the library sends Transaction messages.
     * @param acquirerUrl_StatusReq The URL to which the library sends Status messages.
     * @param logsEnabled This tells the library that it should output debug logging messages.
     * @param serviceLogsEnabled This tells the library that it should save ISO pain raw messages or not. Default is true.
     * @param serviceLogsLocation A directory on the disk where the library saves ISO pain raw messages.
     * @param serviceLogsPattern A string that describes a pattern to distinguish the ISO pain raw messages.
     * @param loggerFactory ILoggerFactory instance that is used to create ILogger object.
     */
    public Configuration(String merchantID, int merchantSubID, String merchantReturnUrl, String keyStoreLocation,
            String keyStorePassword, String merchantCertificateAlias, String merchantCertificatePassword, String acquirerCertificateAlias,
            String acquirerUrl_DirectoryReq, String acquirerUrl_TransactionReq, String acquirerUrl_StatusReq, boolean logsEnabled, boolean serviceLogsEnabled,
            String serviceLogsLocation, String serviceLogsPattern, ILoggerFactory loggerFactory) {
        this.merchantID = merchantID;
        this.merchantSubID = merchantSubID;
        this.merchantReturnUrl = merchantReturnUrl;
        this.keyStoreLocation = keyStoreLocation;
        this.keyStorePassword = keyStorePassword;
        this.merchantCertificateAlias = merchantCertificateAlias;
        this.merchantCertificatePassword = merchantCertificatePassword;
        this.acquirerCertificateAlias = acquirerCertificateAlias;
        this.acquirerDirectoryURL = acquirerUrl_DirectoryReq;
        this.acquirerTransactionURL = acquirerUrl_TransactionReq;
        this.acquirerStatusURL = acquirerUrl_StatusReq;
        this.logsEnabled = logsEnabled;
        this.tls12Enabled = false;
        this.serviceLogsEnabled = serviceLogsEnabled;
        this.serviceLogsLocation = serviceLogsLocation;
        this.serviceLogsPattern = serviceLogsPattern;
        this.loggerFactory = loggerFactory != null? loggerFactory : new LoggerFactory();
        this.keyProviderFactory = new KeyStoreKeyProviderFactory();
        this.keyProvider = keyProviderFactory.create(this);
    }

    /**
     * Constructor that highlights all required fields for this object.
     * @param merchantID ID of the Merchant
     * @param merchantSubID The SubID that uniquely defines a trade name of the Merchant to be used for display
     * @param merchantReturnUrl A valid URL to which the user is redirected, after the transaction has been authorized.
     * @param acquirerCertificateAlias A string which specifies the alias of the acquirer certificate
     * @param acquirerAlternateCertificateAlias A string which specifies the alias of the acquirer alternate certificate
     * @param acquirerUrl_DirectoryReq The URL to which the library sends Directory request messages
     * @param acquirerUrl_TransactionReq The URL to which the library sends Transaction messages.
     * @param acquirerUrl_StatusReq The URL to which the library sends Status messages.
     * @param logsEnabled This tells the library that it should output debug logging messages.
     * @param tls12Enabled This tells the library that should use tls 1.2.
     * @param serviceLogsEnabled This tells the library that it should save ISO pain raw messages or not. Default is true.
     * @param serviceLogsLocation A directory on the disk where the library saves ISO pain raw messages.
     * @param serviceLogsPattern A string that describes a pattern to distinguish the ISO pain raw messages.
     * @param loggerFactory ILoggerFactory instance that is used to create ILogger object.
     * @param keyProviderFactory IKeyProviderFactory instance that is used to create an IKeyProvider.
     */
    public Configuration(String merchantID, int merchantSubID, String merchantReturnUrl, String acquirerCertificateAlias,String acquirerAlternateCertificateAlias,
                         String acquirerUrl_DirectoryReq, String acquirerUrl_TransactionReq, String acquirerUrl_StatusReq, boolean logsEnabled, boolean serviceLogsEnabled,
                         String serviceLogsLocation, String serviceLogsPattern, boolean tls12Enabled, ILoggerFactory loggerFactory, IKeyProviderFactory keyProviderFactory) {
        this.merchantID = merchantID;
        this.merchantSubID = merchantSubID;
        this.merchantReturnUrl = merchantReturnUrl;
        this.acquirerCertificateAlias = acquirerCertificateAlias;
        this.acquirerAlternateCertificateAlias = acquirerAlternateCertificateAlias;
        this.acquirerDirectoryURL = acquirerUrl_DirectoryReq;
        this.acquirerTransactionURL = acquirerUrl_TransactionReq;
        this.acquirerStatusURL = acquirerUrl_StatusReq;
        this.logsEnabled = logsEnabled;
        this.tls12Enabled = tls12Enabled;
        this.serviceLogsEnabled = serviceLogsEnabled;
        this.serviceLogsLocation = serviceLogsLocation;
        this.serviceLogsPattern = serviceLogsPattern;
        this.loggerFactory = loggerFactory != null? loggerFactory : new LoggerFactory();
        this.keyProviderFactory = keyProviderFactory;
        this.keyProvider = keyProviderFactory.create(this);
    }

    private String getConfigValue(Document doc, String key)
    {
        NodeList nl = doc.getElementsByTagName("add");
        for (int i = 0; i < nl.getLength(); i++) {
            Element e = (Element) nl.item(i);
            if (e.getAttribute("key").equalsIgnoreCase(key)) {
                return e.getAttribute("value");
            }
        }
        return null;
    }
    
    /**
     * Attempts to load the settings from the application's configuration
     * @param is an InputStream where the configuration should be loaded from (e.g. a file)
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    public void Load(InputStream is) throws ParserConfigurationException, SAXException, IOException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        
        setMerchantID(getConfigValue(doc, "BankId.Merchant.MerchantID"));
        setMerchantSubID(Integer.parseInt(getConfigValue(doc, "BankId.Merchant.SubID")));
        setMerchantReturnUrl(getConfigValue(doc, "BankId.Merchant.ReturnUrl"));
        
        setAcquirerDirectoryURL(getConfigValue(doc, "BankId.Acquirer.DirectoryUrl"));
        setAcquirerTransactionURL(getConfigValue(doc, "BankId.Acquirer.TransactionUrl"));
        setAcquirerStatusURL(getConfigValue(doc, "BankId.Acquirer.StatusUrl"));
        
        setKeyStoreLocation(getConfigValue(doc, "BankId.KeyStore.Location"));
        setKeyStorePassword(getConfigValue(doc, "BankId.KeyStore.Password"));
        
        setMerchantCertificateAlias(getConfigValue(doc, "BankId.Merchant.Certificate.Alias"));
        setMerchantCertificatePassword(getConfigValue(doc, "BankId.Merchant.Certificate.Password"));
        setAcquirerCertificateAlias(getConfigValue(doc, "BankId.Acquirer.Certificate.Alias"));
        setAcquirerAlternateCertificateAlias(getConfigValue(doc, "BankId.Acquirer.AlternateCertificate.Alias"));
        setSamlCertificateAlias(getConfigValue(doc, "BankId.SAML.Certificate.Alias"));
        setSamlCertificatePassword(getConfigValue(doc, "BankId.SAML.Certificate.Password"));
        
        setLogsEnabled(Boolean.parseBoolean(getConfigValue(doc, "BankId.Logs.Enabled")));
        setServiceLogsEnabled(Boolean.parseBoolean(getConfigValue(doc, "BankId.ServiceLogs.Enabled")));
        setServiceLogsLocation(getConfigValue(doc, "BankId.ServiceLogs.Location"));
        setServiceLogsPattern(getConfigValue(doc, "BankId.ServiceLogs.Pattern"));
        setTls12Enabled(Boolean.parseBoolean(getConfigValue(doc, "BankId.TLS12.Enabled")));
        setLoggerFactory(new LoggerFactory());

        String keyProviderFactoryClassName = getConfigValue(doc, "BankId.KeyProviderFactoryClass");
        if (keyProviderFactoryClassName != null) {
            try {
                setKeyProviderFactory((IKeyProviderFactory) Class.forName(keyProviderFactoryClassName).newInstance());
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                throw new IllegalArgumentException("Could not construct IKeyProviderFactory", e);
            }
        } else {
            setKeyProviderFactory(new KeyStoreKeyProviderFactory());
        }
        setKeyProvider(getKeyProviderFactory().create(this));
        getKeyProvider().Load(doc);
    }
    
    /**
     * Sets the Configuration object to be used by Communicator instances
     * @param values the Configuration instance to get values from
     * @throws IOException 
     */
    public void Setup(Configuration values) throws IOException {
        setMerchantID(values.getMerchantID());
        setMerchantSubID(values.getMerchantSubID());
        setMerchantReturnUrl(values.getMerchantReturnUrl());
        
        setAcquirerDirectoryURL(values.getAcquirerDirectoryURL());
        setAcquirerTransactionURL(values.getAcquirerTransactionURL());
        setAcquirerStatusURL(values.getAcquirerStatusURL());
        
        setKeyStoreLocation(values.getKeyStoreLocation());
        setKeyStorePassword(values.getKeyStorePassword());
        
        setMerchantCertificateAlias(values.getMerchantCertificateAlias());
        setMerchantCertificatePassword(values.getMerchantCertificatePassword());
        setAcquirerCertificateAlias(values.getAcquirerCertificateAlias());
        setAcquirerAlternateCertificateAlias(values.getAcquirerAlternateCertificateAlias());
        setSamlCertificateAlias(values.getSamlCertificateAlias());
        setSamlCertificatePassword(values.getSamlCertificatePassword());
        
        setLogsEnabled(values.isLogsEnabled());
        setTls12Enabled(values.isTls12Enabled());
        setServiceLogsEnabled(values.isServiceLogsEnabled());
        setServiceLogsLocation(values.getServiceLogsLocation());
        setServiceLogsPattern(values.getServiceLogsPattern());
        setLoggerFactory(values.getLoggerFactory());

        if(values.getKeyProviderFactory() == null) {
            setKeyProviderFactory(new KeyStoreKeyProviderFactory());
        } else if(getKeyProviderFactory() == null || !values.getKeyProviderFactory()
                                                            .getClass()
                                                            .equals(getKeyProviderFactory().getClass())) {
            setKeyProviderFactory(values.getKeyProviderFactory());
        }

        if (getKeyProvider() == null || !getKeyProvider().getClass().equals(values.getKeyProvider().getClass())) {
            setKeyProvider(getKeyProviderFactory().create(this));
        }
        getKeyProvider().Setup(values.getKeyProvider());
    }

    /**
     * @return MerchantID as supplied to you. If the MerchantID has less than 9 digits, use leading
     * zeros to fill out the field.
     */
    public String getMerchantID() {
        return merchantID;
    }

    /**
     * @param merchantID MerchantID as supplied to you. If the MerchantID has less than 9 digits, use leading
     * zeros to fill out the field.
     */
    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    /**
     * @return MerchantSubId as supplied to you. If you do not have a ContractSubId, use 0 for this field.
     */
    public int getMerchantSubID() {
        return merchantSubID;
    }
    
    /**
     * @param merchantSubID MerchantSubId as supplied to you. If you do not have a ContractSubId,
     * use 0 for this field.
     */
    public void setMerchantSubID(int merchantSubID) {
        this.merchantSubID = merchantSubID;
    }

    /**
     * @return A valid URL to which the user is redirected, after the transaction has been authorized.
     */
    public String getMerchantReturnUrl() {
        return merchantReturnUrl;
    }

    /**
     * @param merchantReturnUrl A valid URL to which the user is redirected, after the transaction has been authorized.
     */
    public void setMerchantReturnUrl(String merchantReturnUrl) {
        this.merchantReturnUrl = merchantReturnUrl;
    }
    
    /**
     * @return A string which specifies the alias of the merchant certificate.
     */
    public String getMerchantCertificateAlias() {
        return merchantCertificateAlias;
    }
    
    /**
     * @param merchantCertificateAlias A string which specifies the alias of the merchant certificate.
     */
    public void setMerchantCertificateAlias(String merchantCertificateAlias) {
        this.merchantCertificateAlias = merchantCertificateAlias;
    }

    /**
     * @return A string which specifies the alias of the certificate to use to validate received messages
     */
    public String getAcquirerCertificateAlias() {
        return acquirerCertificateAlias;
    }
    
    /**
     * @param acquirerCertificateAlias A string which specifies the alias of the certificate to use to validate received messages
     */
    public void setAcquirerCertificateAlias(String acquirerCertificateAlias) {
        this.acquirerCertificateAlias = acquirerCertificateAlias;
    }
    
    /**
     * @return A string which specifies the alias of the alternate certificate to use to validate received messages
     */
    public String getAcquirerAlternateCertificateAlias() {
        return acquirerAlternateCertificateAlias;
    }
    
    /**
     * @param acquirerAlternateCertificateAlias A string which specifies the alias of the alternate certificate to use to validate received messages
     */
    public void setAcquirerAlternateCertificateAlias(String acquirerAlternateCertificateAlias) {
        this.acquirerAlternateCertificateAlias = acquirerAlternateCertificateAlias;
    }

    /**
     * @return The URL to which the library sends Directory request messages
     */
    public String getAcquirerDirectoryURL() {
        return acquirerDirectoryURL;
    }
    
    /**
     * @param acquirerDirectoryURL The URL to which the library sends Directory request messages
     */
    public void setAcquirerDirectoryURL(String acquirerDirectoryURL) {
        this.acquirerDirectoryURL = acquirerDirectoryURL;
    }

    /**
     * @return The URL to which the library sends Transaction messages.
     */
    public String getAcquirerTransactionURL() {
        return acquirerTransactionURL;
    }

    /**
     * @param acquirerTransactionURL The URL to which the library sends Transaction messages.
     */
    public void setAcquirerTransactionURL(String acquirerTransactionURL) {
        this.acquirerTransactionURL = acquirerTransactionURL;
    }

    /**
     * @return The URL to which the library sends Status request messages.
     */
    public String getAcquirerStatusURL() {
        return acquirerStatusURL;
    }

    /**
     * @param acquirerStatusURL The URL to which the library sends Status request messages.
     */
    public void setAcquirerStatusURL(String acquirerStatusURL) {
        this.acquirerStatusURL = acquirerStatusURL;
    }
    
    /**
     * @return The password of the private key of the signing certificate
     */
    public String getMerchantCertificatePassword() {
        return merchantCertificatePassword;
    }
    
    /**
     * @param merchantCertificatePassword The password of the private key of the signing certificate
     */
    public void setMerchantCertificatePassword(String merchantCertificatePassword) {
        this.merchantCertificatePassword = merchantCertificatePassword;
    }

    /**
     * @return The password used to access the keystore
     */
    public String getKeyStorePassword() {
        return keyStorePassword;
    }
    
    /**
     * @param keyStorePassword The password used to access the keystore
     */
    public void setKeyStorePassword(String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }

    /**
     * @return A Java keystore (file on the disk) that stores the certificates
     */
    public String getKeyStoreLocation() {
        return keyStoreLocation;
    }
    
    /**
     * @param keyStoreLocation A Java keystore (file on the disk) that stores the certificates
     */
    public void setKeyStoreLocation(String keyStoreLocation) {
        this.keyStoreLocation = keyStoreLocation;
    }

    /**
     * @return the keyStore
     */
    InputStream getKeyStore() {
        if (getKeyProvider() instanceof KeyStoreKeyProvider) {
            KeyStoreKeyProvider keyStoreKeyProvider = (KeyStoreKeyProvider) getKeyProvider();
            return keyStoreKeyProvider.getKeyStore();
        }
        return null;
    }
    
    /**
     * @return This tells the library that it should output debug logging messages.
     */
    public boolean isLogsEnabled() {
        return logsEnabled;
    }

    /**
     * @param logsEnabled This tells the library that it should output debug logging messages.
     */
    public void setLogsEnabled(boolean logsEnabled) {
        this.logsEnabled = logsEnabled;
    }

    /**
     * @return This tells the library that it should save ISO pain raw messages or not. Default is true.
     */
    public boolean isServiceLogsEnabled() {
        return serviceLogsEnabled;
    }

    /**
     * @param serviceLogsEnabled This tells the library that it should save ISO pain raw messages or not.
     */
    public void setServiceLogsEnabled(boolean serviceLogsEnabled) {
        this.serviceLogsEnabled = serviceLogsEnabled;
    }

    /**
     * @return A directory on the disk where the library saves ISO pain raw messages.
     */
    public String getServiceLogsLocation() {
        return serviceLogsLocation;
    }

    /**
     * @param serviceLogsLocation A directory on the disk where the library saves ISO pain raw messages.
     */
    public void setServiceLogsLocation(String serviceLogsLocation) {
        this.serviceLogsLocation = serviceLogsLocation;
    }
    /**
     * @return A string that describes a pattern to distinguish the ISO pain raw messages.
     * For example, %Y-%M-%D\%h%m%s.%f-%a.xml -> 102045.924-AcquirerTrxReq.xml
     * Other remarks:
     * %Y = current year
     * %M = current month
     * %D = current day
     * %h = current hour
     * %m = current minute
     * %s = current second
     * %f = current millisecond
     * %a = current action
     */
    public String getServiceLogsPattern() {
        return serviceLogsPattern;
    }
    
    /**
     * @param serviceLogsPattern A string that describes a pattern to distinguish the ISO pain raw messages.
     * For example, %Y-%M-%D\%h%m%s.%f-%a.xml -> 102045.924-AcquirerTrxReq.xml
     * Other remarks:
     * %Y = current year
     * %M = current month
     * %D = current day
     * %h = current hour
     * %m = current minute
     * %s = current second
     * %f = current millisecond
     * %a = current action
     */
    public void setServiceLogsPattern(String serviceLogsPattern) {
        this.serviceLogsPattern = serviceLogsPattern;
    }

    /**
     * @return ILoggerFactory instance that is used to create ILogger object
     */
    public ILoggerFactory getLoggerFactory() {
        return loggerFactory;
    }

    /**
     * @param loggerFactory the loggerFactory to set
     */
    public void setLoggerFactory(ILoggerFactory loggerFactory) {
        this.loggerFactory = loggerFactory;
    }

    /**
     * @return the samlCertificateAlias
     */
    public String getSamlCertificateAlias() {
        return samlCertificateAlias;
    }

    /**
     * @param samlCertificateAlias the samlCertificateAlias to set
     */
    public void setSamlCertificateAlias(String samlCertificateAlias) {
        this.samlCertificateAlias = samlCertificateAlias;
    }

    /**
     * @return the samlCertificatePassword
     */
    public String getSamlCertificatePassword() {
        return samlCertificatePassword;
    }

    /**
     * @param samlCertificatePassword the samlCertificatePassword to set
     */
    public void setSamlCertificatePassword(String samlCertificatePassword) {
        this.samlCertificatePassword = samlCertificatePassword;
    }
    
    /**
     * 
     * @param tls12Enabled flag that indicates the library to use TLS 1.2
     */
    public void setTls12Enabled(boolean tls12Enabled) {
        this.tls12Enabled = tls12Enabled;
    }

    /**
     * 
     * @return the tls12Enabled
     */
    public boolean isTls12Enabled() {
        return tls12Enabled;
    }

    /**
     * @return the messenger to use when sending requests
     */
    public IMessenger getMessenger() {
        if (messenger == null)
            messenger = new Messenger(this);
        
        return messenger;
    }

    /**
     * @return the key provider to use for cryptographic operations
     */
    public IKeyProvider getKeyProvider() {
        return keyProvider;
    }

    /**
     * Set the key provider to use for cryptographic operations.
     * Make sure that the key provider is fully initialized, or initialize it by calling either
     * {@link Configuration#Setup(Configuration)} or {@link Configuration#Load(InputStream)}.
     * @param keyProvider the key provider to use for cryptographic operations
     */
    private void setKeyProvider(IKeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }

    /**
     * @return the factory to use for creating a key provider
     */
    public IKeyProviderFactory getKeyProviderFactory() {
        return keyProviderFactory;
    }

    /**
     * Set the factory to use to create a key provider.
     * After setting the key provider factory, remember to construct and set the key provider.
     * <pre>
     *     config.setKeyProvider(config.getKeyProviderFactory().create(config));
     * </pre>
     * @param keyProviderFactory the factory to use to create a key provider
     */
    private void setKeyProviderFactory(IKeyProviderFactory keyProviderFactory) {
        this.keyProviderFactory = keyProviderFactory;
    }
}
