package net.bankid.merchant.library;

/**
 * Interface to create an {@link IKeyProvider}
 */
public interface IKeyProviderFactory {
    /**
     * Create a new {@link IKeyProvider}
     * @param config The {@link Configuration}
     * @return The new {@link IKeyProvider}
     */
    IKeyProvider create(Configuration config);
}
