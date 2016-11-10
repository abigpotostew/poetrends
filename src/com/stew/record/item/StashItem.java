package com.stew.record.item;

import org.bson.Document;

/**
 * Created by stew.bracken on 11/7/16.
 */
public interface StashItem {
    String getId();
    int hashCode();

    default Document toDocument(){
        return new Document("_id", getId())
                .append("hashCode", hashCode());
    }
}
