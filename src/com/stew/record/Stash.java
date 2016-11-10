package com.stew.record;

import com.stew.record.item.StashItem;
import com.stew.record.support.StashHashUtil;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by stew.bracken on 11/7/16.
 */
public class Stash {
    String accountName, id, name, type;
    ArrayList<StashItem> items;
    private int stashHash;
    //public final long UNHASHED = -1;

    //public Stash(){}

    /**
     * Please use the StashBuilder to construct
     * @param _accountName
     * @param _id
     * @param _name
     * @param _type
     * @param _items
     */
    Stash(String _accountName, String _id, String _name, String _type, ArrayList<StashItem> _items){
        accountName = _accountName;
        id = _id;
        name = _name;
        type = _type;
        items = _items;
        stashHash = StashHashUtil.UNHASHED;
    }

    public String accountName(){
        return this.accountName;
    }

    public static boolean contains(final String accName, final List<Stash> list){
        for(Stash s : list){
            if (s.accountName().equals(accName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        if(stashHash != StashHashUtil.UNHASHED){
            return stashHash;//return cached hash
        }
        //compute hash
        int itemsHash = 0;
        for( StashItem s : items){
            itemsHash += s.hashCode();
        }
        return id.hashCode() + name.hashCode() + itemsHash;
    }

    public Document toDocument(){
        List<Document> itemsDoc = items.stream().map(a -> a.toDocument()).collect(Collectors.toList());
        return new Document("_id", id)
                .append("id", id)
                .append("accountName", accountName)
                .append("name", name)
                .append("type", type)
                .append("items", itemsDoc);

    }

}
