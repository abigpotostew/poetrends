package com.stew.record;

import com.stew.record.item.JsonStashItem;
import com.stew.record.item.StashItem;
import org.bson.BSONException;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stew.bracken on 11/7/16.
 */
public class StashBuilder implements IBuilder<Stash> {
    String accountName, id, stashName, type;
    ArrayList<StashItem> items;

    public StashBuilder(){}

    @Override
    public Stash build(){
        return new Stash(accountName, id, stashName, type, items);
    }

    public StashBuilder accountName(String name){
        accountName = name;
        return this;
    }

    public StashBuilder id(String id){
        this.id = id;
        return this;
    }

    public StashBuilder stashName(String stashName){
        this.stashName = stashName;
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
                        accountName(value); break;
                    case "id":
                        id(value); break;
                    case "stash":
                        stashName(value); break;
                    case "stashType":
                        type(value); break;
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

    public StashBuilder fromBsonDoc(final Document doc) throws BSONException{
        accountName(doc.getString("accountName"));
        id(doc.getString("id"));
        stashName(doc.getString("stash"));
        type(doc.getString("stashType"));
        items = new ArrayList<>();
        List<Document> itemDocs = (List) doc.get("items");
        for (Document doc : itemDocs) {

        }

        return null;
    }
}