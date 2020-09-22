package net.bankid.merchant.library;

class LoggerFactory implements ILoggerFactory {

    private String getPomVersion() {
        return this.getClass().getPackage().getImplementationVersion();
    }

    @Override
    public ILogger create() {
        return new Logger(getPomVersion());
    }
}
