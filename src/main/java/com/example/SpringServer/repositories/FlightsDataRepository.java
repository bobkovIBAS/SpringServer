/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SpringServer.repositories;

import com.example.SpringServer.model.FlightsData;
import com.example.SpringServer.model.GuestCard;
import org.bson.types.ObjectId;

import java.util.List;


public interface FlightsDataRepository {
    List<FlightsData> getAllFlights();
    FlightsData getAllFlightsByUser(GuestCard guestCard);
    FlightsData getAllByInCity();
    void save(FlightsData flightData);
    void deleteFlight(ObjectId id);
    List<FlightsData> getСache();
    void addCache(FlightsData flightData);

}
