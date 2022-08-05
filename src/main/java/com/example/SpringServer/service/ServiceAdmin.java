package com.example.SpringServer.service;

import com.example.SpringServer.DAO.response.FlightDataDAO;
import com.example.SpringServer.model.City;
import com.example.SpringServer.model.FlightsData;
import com.example.SpringServer.model.PossibleFlight;

import java.util.List;

public interface ServiceAdmin {

    Boolean createCity(City city);
    void editCity(City city);
    void deleteCity(String id);
    void editPossibleFlight(PossibleFlight possibleFlight);
    void deletePossibleFlight(String id);
    void deleteFlightDataUser(String id);
    void createPossibleFlight(PossibleFlight possibleFlight);
    List<FlightDataDAO> getAllFlightData();

}
