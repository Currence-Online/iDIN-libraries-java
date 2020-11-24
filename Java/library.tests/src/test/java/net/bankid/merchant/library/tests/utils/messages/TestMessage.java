package net.bankid.merchant.library.tests.utils.messages;

import net.bankid.merchant.library.tests.constants.Messages;

import static net.bankid.merchant.library.tests.utils.resources.ResourcesUtils.getResourceStream;
import static net.bankid.merchant.library.tests.utils.resources.ResourcesUtils.read;

public class TestMessage {

	public static String getMessage(String name) {
		return get(name);
	}

	private static String get(String name) {
		String messagePath = Messages.MESSAGES_DIRECTORY + "/" + name + ".xml";
		try {
			return read(getResourceStream(messagePath));
		} catch(Exception ex) {
			throw new AssertionError("Could not read");
		}
	}
}
