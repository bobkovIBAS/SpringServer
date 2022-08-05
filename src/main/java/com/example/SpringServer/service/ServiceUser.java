package com.example.SpringServer.service;

import com.example.SpringServer.DAO.response.CreateFlightDataDAO;
import com.example.SpringServer.DAO.PossibleFlightDAO;
import com.example.SpringServer.DAO.SearchPossibleFlightDAO;
import com.example.SpringServer.DAO.response.FlightDataDAO;
import com.example.SpringServer.model.City;
import com.example.SpringServer.model.FlightsData;
import com.example.SpringServer.model.GuestCard;

import java.util.List;

public interface ServiceUser {
    List<FlightsData> getAllFlights(String id);
    List<FlightDataDAO> getAllFlightsGuestCard(String id, GuestCard guestCard);
    List<PossibleFlightDAO> getAvaliableFlightsByDate(String date);
    GuestCard userGuestCard(String id_user, String id_guestCard);
    List<GuestCard> userGuestCardAll(String id_user);
    List<PossibleFlightDAO> getAvaliableFlightsByFilter(SearchPossibleFlightDAO filter);
    void deleteBookedFlight(String id);
    void saveGuestCard(GuestCard gc);
    CreateFlightDataDAO createRegist(GuestCard guestCard, String id , String id_user, String date);
    CreateFlightDataDAO createRegistWithGuestCard(String id, String id_user, GuestCard guestCard,String date);
    void saveFlightsData(FlightsData flightData);
    List<PossibleFlightDAO> getAllPossibleFlightsDateNow();
    List<City> getAllCity();
}
