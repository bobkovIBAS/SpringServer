package com.example.SpringServer.controller;

import com.example.SpringServer.model.City;
import com.example.SpringServer.model.FlightsData;
import com.example.SpringServer.model.GuestCard;
import com.example.SpringServer.model.PossibleFlights;
import com.example.SpringServer.repositories.CityRepository;
import com.example.SpringServer.repositories.FlightsDataRepository;
import com.example.SpringServer.repositories.GuestCardRepository;
import com.example.SpringServer.repositories.PossibleFlightsRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class Controller {
    private GuestCardRepository guestRepository;
    private PossibleFlightsRepository possibleFlightsRepository;
    private CityRepository cityRepository;
    private FlightsDataRepository flightRepository;


    public Controller(GuestCardRepository guestRepository,
                      PossibleFlightsRepository possibleFlightsRepository,
                      CityRepository cityRepository,
                      FlightsDataRepository flightRepository) {
        this.guestRepository = guestRepository;
        this.possibleFlightsRepository = possibleFlightsRepository;
        this.cityRepository = cityRepository;
        this.flightRepository = flightRepository;
    }

    public Controller() {
    }


    public List<FlightsData> getAllFlights() {
        return flightRepository.findAll();
    }


    public List<PossibleFlights> getAvaliableFlightsByDate(Date date) {
        return possibleFlightsRepository.findAll();
    }


    public void deleteBookedFlight(String id) {
        flightRepository.deleteById(id);
    }

    public void saveGuestCard(GuestCard gc) {
        guestRepository.save(gc);
    }

    public void createRegist(Map<String, String> localSave, String id) {
        GuestCard gc = new GuestCard();
        gc.setName(localSave.get("name"));
        gc.setSurname(localSave.get("surname"));
        gc.setPassport(localSave.get("passport"));
        saveGuestCard(gc);
        createFlightData(gc, id);
    }

    public void createFlightData(GuestCard guestCard, String id) {
        List<PossibleFlights> possibleFligths = getAllPossibleFlights();
        FlightsData flightData = new FlightsData();
        possibleFligths.forEach(item -> {
            if (item.getId().equals(id)) {
                flightData.setPossibleFlights(item);
                flightData.setGuestCard(guestCard);
            }
        });
        saveFlightsData(flightData);
    }


    public void saveFlightsData(FlightsData flightData) {
        flightRepository.insert(flightData);
    }


    public List<GuestCard> getAllGuestCard() {
        return guestRepository.findAll();
    }


    public List<PossibleFlights> getAllPossibleFlights() {
        return possibleFlightsRepository.findAll();
    }


    public List<City> getAllCity() {
        return cityRepository.findAll();
    }


    public Date getMaskDate(String str) {
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        try {
            date = formater.parse(str);
        } catch (ParseException s) {
            System.out.println(s);
        }
        return date;
    }


    public int getNumberOfTicketsBooked() {
        List<FlightsData> flightsDataList = flightRepository.findAll();
        int counter = 0;
        for (FlightsData findpossible : flightsDataList) {
            counter = +1;
        }
        return counter;
    }

}