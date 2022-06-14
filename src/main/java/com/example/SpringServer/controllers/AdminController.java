package com.example.SpringServer.controllers;

import com.example.SpringServer.model.City;
import com.example.SpringServer.model.PossibleFlight;
import com.example.SpringServer.service.ServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private ServiceAdmin serviceAdmin;

    @PostMapping(value  = "/newflight", consumes = {"application/json"})
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createNewPossibleFlight(@RequestBody PossibleFlight possibleFlight) {
        try {
            serviceAdmin.createPossibleFlight(possibleFlight);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value  = "/editflight", consumes = {"application/json"})
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editPossibleFlight(@RequestBody PossibleFlight possibleFlight) {
        try {
            serviceAdmin.editPossibleFlight(possibleFlight);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteflight/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deletePossibleFlight(@PathVariable ("id") String id) {
        try {
            serviceAdmin.deletePossibleFlight(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value  = "/newcity", consumes = {"application/json"})
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createNewCity(@RequestBody City city) {
        try {
            boolean checkCreate = serviceAdmin.createCity(city);
            if(checkCreate){
                return new ResponseEntity<>(HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value  = "/editcity", consumes = {"application/json"})
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editCity(@RequestBody City city) {
        try {
            serviceAdmin.editCity(city);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletecity/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteCity(@PathVariable ("id") String id) {
        try {
            serviceAdmin.deleteCity(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
