package com.example.SpringServer.service.serviceImp;

import com.example.SpringServer.model.*;
import com.example.SpringServer.repositories.CityRepository;
import com.example.SpringServer.repositories.FlightsDataRepository;
import com.example.SpringServer.repositories.GuestCardRepository;
import com.example.SpringServer.repositories.PossibleFlightsRepository;
import com.example.SpringServer.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ServiceUserImp implements ServiceUser {

    private GuestCardRepository guestRepository;
    private PossibleFlightsRepository possibleFlightsRepository;
    private CityRepository cityRepository;
    private FlightsDataRepository flightRepository;

    @Autowired
    public ServiceUserImp(GuestCardRepository guestRepository, PossibleFlightsRepository possibleFlightsRepository, CityRepository cityRepository, FlightsDataRepository flightRepository) {
        this.guestRepository = guestRepository;
        this.possibleFlightsRepository = possibleFlightsRepository;
        this.cityRepository = cityRepository;
        this.flightRepository = flightRepository;
    }

    public List<FlightsData> getAllFlights() {
        return flightRepository.findAll();
    }


    public List<PossibleFlight> getAvaliableFlightsByDate(String date) {
        return possibleFlightsRepository.getFlightsEqualBydate(date);
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

    public void createRegist(GuestCard guestCard, String id) {
        saveGuestCard(guestCard);
        createFlightData(guestCard, id);
    }


    public void createFlightData(GuestCard guestCard, String id) {
        List<PossibleFlight> possibleFligths = getAllPossibleFlights();
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
    public void createPossibleFlight(PossibleFlight possibleFlight){
        possibleFlightsRepository.insert(possibleFlight);
    }


    public List<GuestCard> getAllGuestCard() {
        return guestRepository.findAll();
    }


    public List<PossibleFlight> getAllPossibleFlights() {
        List<PossibleFlight> listPossibleFlight = possibleFlightsRepository.findAll();
        List<PossibleFlight> filterPossibleFlight = new ArrayList<>();
        String dateNow = getDateString(new Date());
        for (PossibleFlight possibleFlight:listPossibleFlight){
            boolean possibleFlightDates =  possibleFlight.getFlightDate()
                    .stream().anyMatch(i->getDateString(i.getDateFlight()).equals(dateNow));
        }
        return filterPossibleFlight;
    }


    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    public String getDateString(Date val){
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        String str = "";
        try {
            str = formater.format(val);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return str;
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
