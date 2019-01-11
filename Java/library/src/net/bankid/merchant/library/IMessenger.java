package net.bankid.merchant.library;

import java.net.URI;

public interface IMessenger {
    String sendMessage(Configuration config, String message, URI url) throws CommunicatorException;
}
