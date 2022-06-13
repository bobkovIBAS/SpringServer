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

import java.util.Date;

@Getter
@Setter
@Document(collection = "flights_data")
public class FlightsData {

    @BsonProperty("_id")
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    private GuestCard guestCard;
    private Date bookingDate;
    private PossibleFlight possibleFlights;


    public FlightsData() {

    }

    public FlightsData(String id, GuestCard guestCard, Date bookingDate, PossibleFlight possibleFlights) {
        this.id = id;
        this.guestCard = guestCard;
        this.bookingDate = bookingDate;
        this.possibleFlights = possibleFlights;

    }

    public FlightsData(GuestCard guestCard, Date bookingDate, PossibleFlight possibleFlights) {
        this.guestCard = guestCard;
        this.bookingDate = bookingDate;
        this.possibleFlights = possibleFlights;
    }
}
