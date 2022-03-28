/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SpringServer.model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Date;


public class FlightsData implements Serializable {
    
    private static final long SerialVersionUID = 1L;
    @BsonId
    private ObjectId objectId;
    private int id;
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
        this.objectId = id;
        this.guestCard = guestCard;
        this.bookingDate = bookingDate;
        this.free = free;
        this.sumTicket = sumTicket;
        this.possibleFlights = possibleFlights;
        this.outDate = outDate;
        this.cityIn = cityIn;
        this.cityOut = cityOut;
    }

    public FlightsData(int id, GuestCard guestCard, Date bookingDate, int free, int sumTicket, PossibleFlights possibleFlights, Date outDate, City cityIn, City cityOut) {
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

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public GuestCard getUserId() {
        return guestCard;
    }

    public void setUserId(GuestCard userId) {
        this.guestCard = userId;
    }

    public GuestCard getGuestCard() {
        return guestCard;
    }

    public void setGuestCard(GuestCard guestCard) {
        this.guestCard = guestCard;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public PossibleFlights getPossibleFlights() {
        return possibleFlights;
    }

    public void setPossibleFlights(PossibleFlights possibleFlights) {
        this.possibleFlights = possibleFlights;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getSumTicket() {
        return sumTicket;
    }

    public void setSumTicket(int sumTicket) {
        this.sumTicket = sumTicket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getCityIn() {
        return cityIn;
    }

    public void setCityIn(City cityIn) {
        this.cityIn = cityIn;
    }

    public City getCityOut() {
        return cityOut;
    }

    public void setCityOut(City cityOut) {
        this.cityOut = cityOut;
    }
}
