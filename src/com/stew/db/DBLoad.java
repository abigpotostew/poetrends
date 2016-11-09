package com.stew.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.stew.VisibleForTest;

/**
 * Created by stew.bracken on 11/6/16.
 */
public class DBLoad {
    private MongoClient mongoClient;
    private MongoDatabase db;

    public DBLoad(){

    }

    public boolean loadDb(final String dbName){
        mongoClient = new MongoClient();
        assert(dbName != null && !dbName.isEmpty());
        db = mongoClient.getDatabase(dbName);
        return true;
    }

    @VisibleForTest
    public MongoDatabase getDbTestOnly(){
        return db;
    }
}
