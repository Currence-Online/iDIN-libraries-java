package net.bankid.merchant.library.internal;

import java.util.Date;

/**
 * Base class for iDx messages
 */
public class IdxMessageBase {
    protected Date created;
    
    /**
     * Default constructor
     */
    public IdxMessageBase() {
        created = new Date();
    }
    
    /**
     * @return date when this object was created
     */
    public Date getCreated() {
        return created;
    }
}
