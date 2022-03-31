package com.example.SpringServer.controller;

import com.example.SpringServer.model.City;
import com.example.SpringServer.model.FlightsData;
import com.example.SpringServer.model.GuestCard;
import com.example.SpringServer.model.PossibleFlights;
import com.example.SpringServer.repositories.CityRepository;
import com.example.SpringServer.repositories.FlightsDataRepository;
import com.example.SpringServer.repositories.GuestCardRepository;
import com.example.SpringServer.repositories.PossibleFlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("controller")
public class Controller {
    private GuestCardRepository guestRepository;
    private PossibleFlightsRepository possibleFlightsRepository;
    private CityRepository cityRepository;
    private FlightsDataRepository flightRepository;

    @Autowired
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

    @GetMapping("getAllFlights")
    public List<FlightsData> getAllFlights() {
        return flightRepository.findAll();
    }


    public List<PossibleFlights> getAvaliableFlightsByDate(Date date) {
        return possibleFlightsRepository.findAll();
    }

    @PostMapping("/deleteBookedFlight/{id}")
    public void deleteBookedFlight(@PathVariable("id") String id) {
        flightRepository.deleteById(id);
    }

    public void saveGuestCard(GuestCard gc) {
        guestRepository.save(gc);
    }

    @PostMapping("/createRegist/{id}/{name}/{surname}/{passport}")
    public void createRegist(@PathVariable("id") String id, @PathVariable("name") String name,
                             @PathVariable("surname") String surname, @PathVariable("passport") String passport) {
        GuestCard gc = new GuestCard();
        gc.setName(name);
        gc.setSurname(surname);
        gc.setPassport(passport);
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

    @GetMapping("getAllGuestCard")
    public List<GuestCard> getAllGuestCard() {
        return guestRepository.findAll();
    }

    @GetMapping("getAllPossibleFlights")
    public List<PossibleFlights> getAllPossibleFlights() {
        return possibleFlightsRepository.findAll();
    }

    @GetMapping("getAllCity")
    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    @GetMapping("getMaskDate")
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

    @GetMapping("getNumberOfTicketsBooked")
    public int getNumberOfTicketsBooked() {
        List<FlightsData> flightsDataList = flightRepository.findAll();
        int counter = 0;
        for (FlightsData findpossible : flightsDataList) {
            counter = +1;
        }
        return counter;
    }

}