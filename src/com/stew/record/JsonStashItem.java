package com.stew.record;

import org.json.JSONObject;

/**
 * Created by stew.bracken on 11/7/16.
 */
public class JsonStashItem implements StashItem{

    String id;
    JSONObject original;

    public JsonStashItem(String id, JSONObject orig){
        this.id = id;
        original = orig;
    }
    @Override
    public String getId(){
        return id;
    }

    @Override
    public int hashCode(){
        //TODO: temporary until I deserialize items
        return original.toString().hashCode();//StashHashUtil.UNHASHED;
    };
}
