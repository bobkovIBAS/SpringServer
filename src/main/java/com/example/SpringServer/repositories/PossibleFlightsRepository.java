/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SpringServer.repositories;

import com.example.SpringServer.model.PossibleFlights;

import java.util.Date;
import java.util.List;


public interface PossibleFlightsRepository {
   List<PossibleFlights> getTypeAirplane(String type);
   List<PossibleFlights> getDepartureTime(String data);
   List<PossibleFlights> getAllPossibleFlights();
   void save(PossibleFlights possibleFlights);
   List<PossibleFlights> getAllFlights();
   List<PossibleFlights> getFlightsEqualBydate(Date date);
}
