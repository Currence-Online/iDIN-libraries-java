package net.bankid.merchant.library;

public interface IKeyProviderFactory {
    IKeyProvider create(Configuration config);
}
