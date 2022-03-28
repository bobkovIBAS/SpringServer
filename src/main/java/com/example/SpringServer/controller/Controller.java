package com.example.SpringServer.controller;

import com.example.SpringServer.model.City;
import com.example.SpringServer.model.FlightsData;
import com.example.SpringServer.model.GuestCard;
import com.example.SpringServer.model.PossibleFlights;
import com.example.SpringServer.repositories.CityRepository;
import com.example.SpringServer.repositories.FlightsDataRepository;
import com.example.SpringServer.repositories.GuestCardRepository;
import com.example.SpringServer.repositories.PossibleFlightsRepository;
import com.example.SpringServer.repositories.imp.CityRepositoryImp;
import com.example.SpringServer.repositories.imp.FlightsDataRepositoryImp;
import com.example.SpringServer.repositories.imp.GuestCardRepositoryImp;
import com.example.SpringServer.repositories.imp.PossibleFlightsRepositoryImp;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("controller")
public class Controller {
    GuestCardRepository guestRepository = new GuestCardRepositoryImp();
    FlightsDataRepository flightRepository = new FlightsDataRepositoryImp();
    PossibleFlightsRepository possibleFlightsRepository = new PossibleFlightsRepositoryImp();
    CityRepository cityRepository = new CityRepositoryImp();


    public Controller(){
    }

    @GetMapping("getAllFlights")
    public List<FlightsData> getAllFlights(){
        return flightRepository.getAllFlights();
    }

    @GetMapping("getAvaliableFlightsByDate")
    public List<PossibleFlights> getAvaliableFlightsByDate(Date date){
        return possibleFlightsRepository.getFlightsEqualBydate(date);
    }

    @GetMapping("deleteBookedFlight")
    public void deleteBookedFlight(ObjectId id){
        flightRepository.deleteFlight(id);
    }


    public void saveGuestCard(GuestCard gc){
        guestRepository.save(gc);
    }

    @PostMapping("/createRegist/{id}/{name}/{surname}/{passport}")
    public void createRegist(@PathVariable("id") int id,@PathVariable("name") String name,
                             @PathVariable("surname") String surname,@PathVariable("passport") String passport)
    {
        GuestCard gc = new GuestCard();
        gc.setName(name);
        gc.setSurname(surname);
        gc.setPassport(passport);
        saveGuestCard(gc);
        createFlightData(gc,id);
    }

    public void createFlightData(GuestCard guestCard,Integer id){
        List<PossibleFlights> possibleFligths = getAllPossibleFlights();
        FlightsData flightData = new FlightsData();
        possibleFligths.forEach(item->{
            if(item.getId() == id){
                flightData.setPossibleFlights(item);
                flightData.setUserId(guestCard);
            }    
        });
        saveFlightsData(flightData);
    }


    public void saveFlightsData(FlightsData flightData){
        flightRepository.addCache(flightData);
        flightRepository.save(flightData);

    }

    @GetMapping("getAllGuestCard")
    public List<GuestCard> getAllGuestCard(){
        return guestRepository.findAll();
    }

    @GetMapping("getAllPossibleFlights")
    public List<PossibleFlights> getAllPossibleFlights(){
        return possibleFlightsRepository.getAllPossibleFlights();
    }

    @GetMapping("getAllCity")
    public List<City> getAllCity(){
        return cityRepository.findAll();
    }

    @GetMapping("getMaskDate")
    public Date getMaskDate(String str){
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        try{
            date = formater.parse(str);
        }
        catch(ParseException s){
            System.out.println(s);
        }
        return date;
    }

    @GetMapping("getNumberOfTicketsBooked")
    public int getNumberOfTicketsBooked(){
        List<FlightsData> flightsDataList = flightRepository.getAllFlights();
        int counter = 0;
        for(FlightsData findpossible:flightsDataList){
            counter = +1;
        }
        return counter;
    }

}