package com.stelios.cakenaysh.Util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Database {

    private final MongoClient client;
    private final MongoDatabase database;
    private final MongoCollection<Document> playerStats;
    private final MongoCollection<Document> playerItems;

    public Database() {
        client = MongoClients.create();
        database = client.getDatabase("cakenaysh");
        playerStats = database.getCollection("playerStats");
        playerItems = database.getCollection("playerItems");
    }

    //getters
    public MongoClient getClient() {
        return client;
    }
    public MongoDatabase getDatabase() {
        return database;
    }
    public MongoCollection<Document> getPlayerStats() {
        return playerStats;
    }
    public MongoCollection<Document> getPlayerItems() {
        return playerItems;
    }

}
