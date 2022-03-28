/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SpringServer.server;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author isaevin
 */
public class PUtill {
    private static String host = "localhost";
    private static int port = 27017;
    private static String dataBase = "AirTicketSystem";
    private static MongoClient mongoStart = null;
    private static CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    
    private PUtill(){
    }
    
    public static MongoClient getConntection(){
        if(mongoStart==null){
            mongoStart = new MongoClient(host,port);
        }
        return mongoStart;
    }
    public static MongoCollection getCollection(String model, Class cls){
        return getConntection().getDatabase(dataBase).withCodecRegistry(pojoCodecRegistry).getCollection(model, cls);
    }
}
