package com.example.SpringServer.controllers;

import com.example.SpringServer.DAO.PossibleFlightDAO;
import com.example.SpringServer.DAO.SearchPossibleFlightDAO;
import com.example.SpringServer.model.City;
import com.example.SpringServer.model.FlightsData;
import com.example.SpringServer.model.GuestCard;
import com.example.SpringServer.service.ServiceUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private ServiceUser serviceUser;

    public UserController(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @GetMapping("/getAllPossibleFlights")
    private ResponseEntity<List<PossibleFlightDAO>>getAllPossibleFlights() {
        List<PossibleFlightDAO> possibleFlights =  serviceUser.getAllPossibleFlightsDateNow();
        if(!possibleFlights.isEmpty()){
            return new ResponseEntity<>(possibleFlights, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllFlights")
    private ResponseEntity<List<FlightsData>> getAllFlights() {
        List<FlightsData> flightsData =  serviceUser.getAllFlights();
        if(!flightsData.isEmpty()){
            return new ResponseEntity<>(flightsData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getallcity")
    private ResponseEntity<List<City>> getAllCity() {
        List<City> listCity =  serviceUser.getAllCity();
        if(!listCity.isEmpty()){
            return new ResponseEntity<>(listCity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAvailableFlightsByDate/{date}")
    private ResponseEntity<List<PossibleFlightDAO>> getAvailableFlightsByDate(@PathVariable("date") String date){
        List<PossibleFlightDAO> possibleFlights =  serviceUser.getAvaliableFlightsByDate(date);
        if(!possibleFlights.isEmpty()){
            return new ResponseEntity<>(possibleFlights, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value  ="/searchflight", consumes = {"application/json"})
    private ResponseEntity<List<PossibleFlightDAO>> getAvailableFlightsByFilter(@RequestBody SearchPossibleFlightDAO search){
        List<PossibleFlightDAO> possibleFlights =  serviceUser.getAvaliableFlightsByFilter(search);
        if(!possibleFlights.isEmpty()){
            return new ResponseEntity<>(possibleFlights, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value  = "/registration/{id}", consumes = {"application/json"})
    public ResponseEntity<?> createRegistration(@PathVariable ("id") String id,@RequestBody GuestCard user) {
        try {
            serviceUser.createRegist(user,id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteBookedFlight/{id}")
    public ResponseEntity<HttpStatus> deleteBookedFlight(@PathVariable ("id") String id) {
        try {
            serviceUser.deleteBookedFlight(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
