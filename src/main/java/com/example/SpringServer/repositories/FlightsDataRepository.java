/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SpringServer.repositories;

import com.example.SpringServer.model.FlightsData;
import com.example.SpringServer.model.GuestCard;
import com.example.SpringServer.model.PossibleFlights;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface FlightsDataRepository extends MongoRepository<FlightsData, String> {
    List<FlightsData> findAll();
    FlightsData insert(FlightsData flightData);
    FlightsData deleteById(ObjectId id);

}