package net.bankid.merchant.library;

class LoggerFactory implements ILoggerFactory {
    @Override
    public ILogger create() {
        return new Logger();
    }
}
