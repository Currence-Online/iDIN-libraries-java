package net.bankid.merchant.library;

/**
 * Interface describing a factory of loggers
 */
public interface ILoggerFactory {
    /**
     * Create a new logger
     * @return a new logger
     */
    public ILogger create();
}
