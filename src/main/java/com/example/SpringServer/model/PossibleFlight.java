/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SpringServer.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "possible_flights")
public class PossibleFlight {
    @BsonProperty("_id")
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    private City fromId;
    private City toId;
    private String planeTypes;
    private List<PossibleFlightDate> flightDate;

    public PossibleFlight() {

    }

    public PossibleFlight(String id, City fromId, City toId, String planeTypes, List<PossibleFlightDate> flightDate) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.planeTypes = planeTypes;
        this.flightDate = flightDate;
    }

    public PossibleFlight(City fromId, City toId, String planeTypes, List<PossibleFlightDate> flightDate) {
        this.fromId = fromId;
        this.toId = toId;
        this.planeTypes = planeTypes;
        this.flightDate = flightDate;
    }
}
