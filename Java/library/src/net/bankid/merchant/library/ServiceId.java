package net.bankid.merchant.library;

/**
 * Enumeration used to indicate the purpose of the authentication and/or the attributes requested
 */
public class ServiceId {
    private int value;
    
    public ServiceId(int value) {
        this.value = value;
    }
    
    public ServiceId(String value) {
        try {
            this.value = Integer.parseInt(value);
        }
        catch (Exception e) {
            this.value = ServiceIds.None;
        }
    }
    
    public int getValue() {
        return value;
    }
}
