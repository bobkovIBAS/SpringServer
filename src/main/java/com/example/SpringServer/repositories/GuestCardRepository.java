/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SpringServer.repositories;

import com.example.SpringServer.model.GuestCard;
import com.example.SpringServer.model.PossibleFlights;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface GuestCardRepository extends MongoRepository<GuestCard, String> {
    List<GuestCard> findAll();
    GuestCard insert(GuestCard guestCard);
            
}
