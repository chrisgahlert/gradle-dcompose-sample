package com.sample;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;
import java.util.Map;

public class MongoDbCounter {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\n##################################");
        System.out.println(getCount());
        System.out.println("##################################\n");
    }

    private static ServerAddress getMongoAddress() {
        return new ServerAddress(System.getProperty("mongo.host"), Integer.parseInt(System.getProperty("mongo.port")));
    }

    public static String getCount() {
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient(getMongoAddress());
            mongoClient.setWriteConcern(WriteConcern.SAFE);

            MongoDatabase db = mongoClient.getDatabase("sample");
            MongoCollection<Document> collection = db.getCollection("invocations");

            // get latest invocation
            Document previousInvocation = collection.find().sort(new Document("date", -1)).first();

            // Insert document for current run
            collection.insertOne(new Document("date", new Date()));

            // get count of all invocations
            long count = collection.count();

            String msg = "times invoked: " + count;
            if (previousInvocation != null) {
                msg += " (last invocation was at " + previousInvocation.getDate("date") + ")";
            }
            return msg;
        } finally {
            if (mongoClient != null) {
                mongoClient.close();
            }
        }
    }

}
