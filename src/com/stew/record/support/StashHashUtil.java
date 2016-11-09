package com.stew.record.support;

/**
 * Created by stew.bracken on 11/8/16.
 */
public class StashHashUtil {
    public static final int UNHASHED = -1;
    private static int HASH_CONSTRAINT = 449291056;
    public static int hashContraint(final int hash){
        return hash % HASH_CONSTRAINT;
    }
}
