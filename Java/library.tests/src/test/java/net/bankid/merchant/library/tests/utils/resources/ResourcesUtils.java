package net.bankid.merchant.library.tests.utils.resources;

import net.bankid.merchant.library.tests.utils.messages.TestMessage;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertNotNull;

public class ResourcesUtils {

	public static InputStream getResourceStream(String resourcePath) {
		ClassLoader classLoader = TestMessage.class.getClassLoader();
		InputStream stream = classLoader.getResourceAsStream(resourcePath);
		assertNotNull("Could not retrieve resource from " + resourcePath, stream);
		return stream;
	}

	public static String read(InputStream stream) {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		try {
			while((length = stream.read(buffer)) != -1) {
				result.write(buffer, 0, length);
			}
			return result.toString(StandardCharsets.UTF_8.name());
		} catch(IOException ex) {
			throw new RuntimeException("Could not read from stream.", ex);
		}
	}
}
