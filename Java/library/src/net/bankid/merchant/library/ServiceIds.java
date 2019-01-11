package net.bankid.merchant.library;

/**
 * Helper class for service id creation
 */
public class ServiceIds {
    public static final int None = 0;
    public static final int ConsumerTransientId = 0;
    public static final int ConsumerBin = 16384;
    public static final int Name = 4096;
    public static final int Address = 1024;
    public static final int IsEighteenOrOlder = 64;
    public static final int DateOfBirth = 64 + 128 + 256;
    public static final int Gender = 16;
    public static final int BSN = 1;
    public static final int Email = 2;
    public static final int Telephone = 4;
    public static final int Sign = 8;
}
