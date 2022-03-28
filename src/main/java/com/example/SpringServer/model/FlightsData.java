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
import java.util.Date;

@Getter
@Setter
@Document(collection = "flights_data")
public class FlightsData {

    @BsonProperty("_id")
    @BsonId
    private ObjectId id;
    private GuestCard guestCard;
    private Date bookingDate;
    private int free;
    private int sumTicket;
    private PossibleFlights possibleFlights;
    private Date outDate;
    private City cityIn;
    private City cityOut;

    public FlightsData(){
        
    }

    public FlightsData(ObjectId id, GuestCard guestCard, Date bookingDate, int free, int sumTicket, PossibleFlights possibleFlights, Date outDate, City cityIn, City cityOut) {
        this.id = id;
        this.guestCard = guestCard;
        this.bookingDate = bookingDate;
        this.free = free;
        this.sumTicket = sumTicket;
        this.possibleFlights = possibleFlights;
        this.outDate = outDate;
        this.cityIn = cityIn;
        this.cityOut = cityOut;
    }

    public FlightsData(GuestCard guestCard, Date bookingDate, int free, int sumTicket, PossibleFlights possibleFlights, Date outDate, City cityIn, City cityOut) {
        this.guestCard = guestCard;
        this.bookingDate = bookingDate;
        this.free = free;
        this.sumTicket = sumTicket;
        this.possibleFlights = possibleFlights;
        this.outDate = outDate;
        this.cityIn = cityIn;
        this.cityOut = cityOut;
    }
}
