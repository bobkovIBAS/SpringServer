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
@Document(collection = "guest_card")
public class GuestCard {
    @BsonProperty("_id")
    @BsonId
    private ObjectId id;
    private String name;
    private String surname;
    private String passport;

    public GuestCard(ObjectId id,String name,String surname,String pass){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.passport= pass;
    }

    public GuestCard(String name, String surname, String passport) {
        this.name = name;
        this.surname = surname;
        this.passport = passport;
    }
    
    public GuestCard() {
        
    }

    
}
