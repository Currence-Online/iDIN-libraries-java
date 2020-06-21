package net.bankid.merchant.library;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class SigningKeyPair {
    private final PrivateKey privateKey;
    private final X509Certificate certificate;

    public SigningKeyPair(PrivateKey privateKey, X509Certificate certificate) {
        assert(privateKey != null);
        assert(certificate != null);
        this.privateKey = privateKey;
        this.certificate = certificate;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public X509Certificate getCertificate() {
        return certificate;
    }
}
