package net.bankid.merchant.library;

public enum AssuranceLevel {
    Loa3 (2);
    
    private final int value;
    
    AssuranceLevel(int value) {
        this.value = value;
    }
    
    public int intValue() {
        return value;
    }
    
    public String stringValue() throws CommunicatorException {
        if (value == Loa3.intValue()) {
            return "nl:bvn:bankid:1.0:loa3";
        }
        else
            throw new CommunicatorException("Level of assurance not supported: " + value);
    }
}
