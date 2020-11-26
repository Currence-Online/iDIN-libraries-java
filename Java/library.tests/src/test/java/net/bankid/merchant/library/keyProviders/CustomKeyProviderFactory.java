package net.bankid.merchant.library.keyProviders;

import net.bankid.merchant.library.Configuration;
import net.bankid.merchant.library.IKeyProvider;
import net.bankid.merchant.library.IKeyProviderFactory;

public class CustomKeyProviderFactory implements IKeyProviderFactory {
	@Override
	public IKeyProvider create(Configuration configuration) {
		return new CustomKeyProvider();
	}
}
