package com.stew.record;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by stew.bracken on 11/7/16.
 */
public class StashBuilder implements IBuilder<Stash> {
    String accountName, id, name, type;
    ArrayList<StashItem> items;

    public StashBuilder(){}

    @Override
    public Stash build(){
        return new Stash(accountName, id, name, type, items);
    }

    public StashBuilder accountName(String name){
        accountName = name;
        return this;
    }

    public StashBuilder id(String id){
        this.id = id;
        return this;
    }

    public StashBuilder name(String name){
        this.name = name;
        return this;
    }

    public StashBuilder type(String type){
        this.type = type;
        return this;
    }

    public StashBuilder fromJson(final JSONObject json){
        String[] strAttr = {"accountName", "id", "stash", "stashType"};
        for(int i=0;i<strAttr.length;++i){
            try{
                String value = json.getString(strAttr[i]);
                switch(strAttr[i]){
                    case "accountName":
                        accountName(value);
                    case "id":
                        id(value);
                    case "stash":
                        name(value);
                    case "stashType":
                        type(value);
                }
            }catch(JSONException e){
                System.out.println("Error, could not read stash property with key"+strAttr[i]);
            }
        }
        JSONArray jItems = json.getJSONArray("items");
        items = new ArrayList<>();
        for(Object obj : jItems){
            JSONObject jObj = (JSONObject)obj;
            if (obj != null){
                items.add(
                        new JsonStashItem(jObj.getString("id"), jObj)
                );
            }
        }
        return this;
    }
}