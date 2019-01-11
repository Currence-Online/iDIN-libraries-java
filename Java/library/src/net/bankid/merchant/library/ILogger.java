package net.bankid.merchant.library;

/**
 * Interface that describes a custom logger
 */
public interface ILogger {
    /**
     * Log a message outputted from the library
     * @param config the configuration object
     * @param message a formatting string
     * @param args any number of extra objects
     */
    void Log(Configuration config, String message, Object... args);
    
    /**
     * Logs a request or response xml string
     * @param config the configuration object
     * @param content the xml
     */
    void LogXmlMessage(Configuration config, String content);
}
