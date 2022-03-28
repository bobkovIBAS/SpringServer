/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SpringServer.model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.io.Serializable;

public class PossibleFlights implements Serializable {
    private static final long SerialVersionUID = 1L;
    @BsonId
    private ObjectId objectId;
    private int id;
    private City fromId;
    private City toId;
    private String planeTypes;
    private int freePlaces;
    private String dateFlights;
    private int sumTicket;
    
    public PossibleFlights(){
    
    }

    public PossibleFlights(ObjectId objectId, City fromId, City toId, String planeTypes, int freePlaces, String dateFlights, int sumTicket) {
        this.objectId = objectId;
        this.fromId = fromId;
        this.toId = toId;
        this.planeTypes = planeTypes;
        this.freePlaces = freePlaces;
        this.dateFlights = dateFlights;
        this.sumTicket = sumTicket;
    }
    
    public PossibleFlights(int id ,City fromId, City toId, String planeTypes, int freePlaces, String dateFlights, int sumTicket) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.planeTypes = planeTypes;
        this.freePlaces = freePlaces;
        this.dateFlights = dateFlights;
        this.sumTicket = sumTicket;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public City getFromId() {
        return fromId;
    }

    public void setFromId(City fromId) {
        this.fromId = fromId;
    }

    public City getToId() {
        return toId;
    }

    public void setToId(City toId) {
        this.toId = toId;
    }

    public String getPlaneTypes() {
        return planeTypes;
    }

    public void setPlaneTypes(String planeTypes) {
        this.planeTypes = planeTypes;
    }

    public int getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
    }

    public String getDateFlights() {
        return dateFlights;
    }

    public void setDateFlights(String dateFlights) {
        this.dateFlights = dateFlights;
    }

    public int getSumTicket() {
        return sumTicket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSumTicket(int sumTicket) {
        this.sumTicket = sumTicket;
    }
    @Override
    public String toString() {
      return "possible_flights [id=" + objectId + ", cityFrom=" + fromId + ", cityTo=" + toId + ", name=" + planeTypes + ", freePlace=" + freePlaces + ""
              + ", date=" + dateFlights + ", sumTickets=" + sumTicket + "]";
    }
}
