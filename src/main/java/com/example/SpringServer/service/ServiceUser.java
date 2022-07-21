package com.example.SpringServer.service;

import com.example.SpringServer.DAO.PossibleFlightDAO;
import com.example.SpringServer.DAO.SearchPossibleFlightDAO;
import com.example.SpringServer.model.City;
import com.example.SpringServer.model.FlightsData;
import com.example.SpringServer.model.GuestCard;

import java.util.List;
import java.util.Map;

public interface ServiceUser {
    List<FlightsData> getAllFlights(String id);
    List<PossibleFlightDAO> getAvaliableFlightsByDate(String date);
    GuestCard userGuestCard(String id_user, String id_guestCard);
    List<GuestCard> userGuestCardAll(String id_user);
    List<PossibleFlightDAO> getAvaliableFlightsByFilter(SearchPossibleFlightDAO filter);
    void deleteBookedFlight(String id);
    void saveGuestCard(GuestCard gc);
    void createRegist(Map<String, String> localSave, String id);
    void createRegist(GuestCard guestCard, String id , String id_user);
    void createRegistWithGuestCard(String id, String id_user, GuestCard guestCard);
    void createFlightData(GuestCard guestCard, String id);
    void saveFlightsData(FlightsData flightData);
    List<PossibleFlightDAO> getAllPossibleFlightsDateNow();
    List<City> getAllCity();
}
