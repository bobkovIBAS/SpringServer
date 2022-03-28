/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SpringServer.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@Document(collection = "possible_flights")
public class PossibleFlights {
    @BsonProperty("_id")
    @BsonId
    private ObjectId id;
    private City fromId;
    private City toId;
    private String planeTypes;
    private int freePlaces;
    private String dateFlights;
    private int sumTicket;
    
    public PossibleFlights(){
    
    }

    public PossibleFlights(ObjectId objectId, City fromId, City toId, String planeTypes, int freePlaces, String dateFlights, int sumTicket) {
        this.id = objectId;
        this.fromId = fromId;
        this.toId = toId;
        this.planeTypes = planeTypes;
        this.freePlaces = freePlaces;
        this.dateFlights = dateFlights;
        this.sumTicket = sumTicket;
    }
    
    public PossibleFlights(City fromId, City toId, String planeTypes, int freePlaces, String dateFlights, int sumTicket) {
        this.fromId = fromId;
        this.toId = toId;
        this.planeTypes = planeTypes;
        this.freePlaces = freePlaces;
        this.dateFlights = dateFlights;
        this.sumTicket = sumTicket;
    }

}
