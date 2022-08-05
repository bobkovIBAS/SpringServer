/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SpringServer.repositories;

import com.example.SpringServer.model.GuestCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface GuestCardRepository extends MongoRepository<GuestCard, String> {
    List<GuestCard> findAll();
    @Query("{ 'passport' : ?0 }")
    GuestCard findByPassport(String passport);
    GuestCard insert(GuestCard guestCard);

}
