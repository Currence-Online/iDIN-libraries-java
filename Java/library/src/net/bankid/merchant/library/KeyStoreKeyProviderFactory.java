package net.bankid.merchant.library;

/**
 * Factory class for {@link KeyStoreKeyProvider}.
 * This is the default {@link IKeyProviderFactory} implementation.
 */
public class KeyStoreKeyProviderFactory implements IKeyProviderFactory {
    @Override
    public IKeyProvider create(Configuration config) {
        return new KeyStoreKeyProvider(config);
    }
}
