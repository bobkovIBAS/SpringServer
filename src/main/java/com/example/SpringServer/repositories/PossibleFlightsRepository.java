/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SpringServer.repositories;

import com.example.SpringServer.model.PossibleFlight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface PossibleFlightsRepository extends MongoRepository<PossibleFlight, String> {
    List<PossibleFlight> findAll();

    PossibleFlight insert(PossibleFlight possibleFlights);

    @Query("{dateFlights: { $regex: ?0 } }")
    List<PossibleFlight> getFlightsEqualBydate(String date);


    @Query("{planeTypes: { $regex: ?0 } }")
    List<PossibleFlight> getTypeAirplane(String type);
}
