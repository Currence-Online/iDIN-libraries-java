package net.bankid.merchant.library;

import static net.bankid.merchant.library.release.ReleaseInfo.getReleaseInfo;

class LoggerFactory implements ILoggerFactory {

	@Override
	public ILogger create() {
		return new Logger(getReleaseInfo().getVersion());
	}
}
