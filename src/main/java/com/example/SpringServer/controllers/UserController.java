package com.example.SpringServer.controllers;

import com.example.SpringServer.DAO.response.CreateFlightDataDAO;
import com.example.SpringServer.DAO.PossibleFlightDAO;
import com.example.SpringServer.DAO.SearchPossibleFlightDAO;
import com.example.SpringServer.DAO.response.FlightDataDAO;
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

    @GetMapping("/getAllFlights/{id_user}")
    private ResponseEntity<List<FlightsData>> getAllFlights(@PathVariable("id_user") String id_user) {
        List<FlightsData> flightsData =  serviceUser.getAllFlights(id_user);
        if(!flightsData.isEmpty()){
            return new ResponseEntity<>(flightsData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/getflight/{id_user}")
    private ResponseEntity<List<FlightDataDAO>> getAllFlightsGuestCard(
            @PathVariable("id_user") String id_user,
            @RequestBody GuestCard user) {
        List<FlightDataDAO> flightsData =  serviceUser.getAllFlightsGuestCard(id_user,user);
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

    @PostMapping(value  = "/registrationwithguestcard/{id}/{id_user}/{date}", consumes = {"application/json"})
    public ResponseEntity<CreateFlightDataDAO> createRegistration(@PathVariable ("id") String id,
                                                                  @RequestBody GuestCard user,
                                                                  @PathVariable ("id_user") String id_user,
                                                                  @PathVariable ("date") String date) {
        try {
            CreateFlightDataDAO  createFlightDataDAO = serviceUser.createRegist(user,id,id_user,date);
            return new ResponseEntity<>(createFlightDataDAO,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value  = "/registration/{id}/{id_user}/{date}", consumes = {"application/json"})
    public ResponseEntity<CreateFlightDataDAO> createRegistration(@PathVariable ("id") String id,
                                                @PathVariable ("id_user") String id_user
                                                ,@RequestBody GuestCard guestCard,
                                                @PathVariable ("date") String date) {
        try {
            CreateFlightDataDAO createFlightDataDAO = serviceUser.createRegistWithGuestCard(id,id_user,guestCard, date);
            return new ResponseEntity<>(createFlightDataDAO,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getGuestCard/{id_user}/{id_guestcard}")
    private ResponseEntity<GuestCard> getGuestCard(@PathVariable("id_user") String id,@PathVariable("id_guestcard") String idGuestCard) {
        GuestCard guestCard =  serviceUser.userGuestCard(id,idGuestCard);
        if(guestCard!=null){
            return new ResponseEntity<>(guestCard, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getGuestCard/{id_user}")
    private ResponseEntity<List<GuestCard>> getGuestCardAll(@PathVariable("id_user") String id) {
        List<GuestCard> guestCard =  serviceUser.userGuestCardAll(id);
        if(guestCard!=null){
            return new ResponseEntity<>(guestCard, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
