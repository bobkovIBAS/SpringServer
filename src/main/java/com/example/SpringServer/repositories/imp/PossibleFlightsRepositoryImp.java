/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SpringServer.repositories.imp;

import com.example.SpringServer.repositories.PossibleFlightsRepository;
import com.mongodb.client.MongoCollection;
import com.example.SpringServer.model.PossibleFlights;
import com.example.SpringServer.server.PUtill;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class PossibleFlightsRepositoryImp implements PossibleFlightsRepository {
     private MongoCollection<PossibleFlights> collectionPossibleFlights = PUtill.getCollection("possible_flights",PossibleFlights.class);

    @Override
    public List<PossibleFlights> getTypeAirplane(String type) {
         List<PossibleFlights> possible = loadPossibleFlight();
         List<PossibleFlights> findPossible = new ArrayList<>();
         for(PossibleFlights possibe:possible){
             if(possibe.getPlaneTypes().equals(type)){
                 findPossible.add(possibe);
             }
         }
        return findPossible;
    }

    @Override
    public List<PossibleFlights> getAllPossibleFlights(){
        List<PossibleFlights> possible = new ArrayList<>();
        collectionPossibleFlights.find().forEach((Consumer<PossibleFlights>) doc ->{
            possible.add(doc);
        });
        return possible;
    }

    @Override
    public List<PossibleFlights> getDepartureTime(String data) {
        List<PossibleFlights> possible = loadPossibleFlight();
         List<PossibleFlights> findPossible = new ArrayList<>();
         for(PossibleFlights element:possible){
             if(element.getDateFlights().equals(data)){

                 findPossible.add(element);
             }
         }
        return findPossible;
    }

    @Override
    public List<PossibleFlights> getAllFlights() {
        return (List<PossibleFlights>) collectionPossibleFlights.find();
    }

    public List<PossibleFlights> loadPossibleFlight(){
        List<PossibleFlights> possibleFlightsList = new ArrayList<>();
        collectionPossibleFlights.find().forEach((Consumer<PossibleFlights>) doc ->{
            possibleFlightsList.add(doc);
        });
        return possibleFlightsList;
    }

    @Override
    public List<PossibleFlights> getFlightsEqualBydate(Date date) {
        List<PossibleFlights> possibleFlightsList = loadPossibleFlight();
        List<PossibleFlights> searchList = new ArrayList<>();
        for (PossibleFlights findPossible:possibleFlightsList){
            if(findPossible.getDateFlights().equals(date)){
                searchList.add(findPossible);
            }
        }
        return searchList;
    }

    @Override
    public void save(PossibleFlights possibleFlights) {
        collectionPossibleFlights.insertOne(possibleFlights);
    }
}
