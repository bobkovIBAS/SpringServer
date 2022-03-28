/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SpringServer.model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.io.Serializable;


public class GuestCard  implements Serializable {
    private static final long SerialVersionUID = 1L;
    @BsonId
    private ObjectId objectId;
    private int id;
    private String name;
    private String surname;
    private String passport;

    public GuestCard(ObjectId id,String name,String surname,String pass){
        this.objectId = id;
        this.name = name;
        this.surname = surname;
        this.passport= pass;
    }

    public GuestCard(int id,String name, String surname, String passport) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.passport = passport;
    }
    
    public GuestCard() {
        
    }
    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
    
    
}
