package net.bankid.merchant.library;

public class KeyStoreKeyProviderFactory implements IKeyProviderFactory {
    @Override
    public IKeyProvider create(Configuration config) {
        return new KeyStoreKeyProvider(config);
    }
}
