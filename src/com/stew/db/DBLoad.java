package com.stew.db;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.stew.VisibleForTest;
import com.stew.record.Stash;
import org.bson.Document;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by stew.bracken on 11/6/16.
 */
public class DBLoad {
    private MongoClient mongoClient;
    private MongoDatabase db;
    private String dbName, collectionName;

    public DBLoad(){

    }

    public boolean loadDb(final String dbName){
        assert(dbName != null && !dbName.isEmpty());
        this.dbName = dbName;

        mongoClient = new MongoClient();
        db = mongoClient.getDatabase(dbName);
        return true;
    }

    public void setCollectionName(final String collName){
        this.collectionName = collName;
    }
    public String getCollectionName(){
        return this.collectionName;
    }

    @VisibleForTest
    public MongoDatabase getDbTestOnly(){
        return db;
    }

    /**
     * creates a new collection and loads stashes into it. expects database to already exist
     * @param collectionName
     * @param stashes
     */
    public void initialDbLoad(final String collectionName, List<Stash> stashes){
        setCollectionName(collectionName);
        db.createCollection(getCollectionName());
        db.getCollection(getCollectionName()).insertMany(stashes.stream().map(s->s.toDocument()).collect(Collectors.toList()));
    }

    public FindIterable<Document> find(){
        return db.getCollection(getCollectionName()).find();
    }


}
