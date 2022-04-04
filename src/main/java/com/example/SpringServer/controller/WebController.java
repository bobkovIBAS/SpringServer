package com.example.SpringServer.controller;

import com.example.SpringServer.model.FlightsData;
import com.example.SpringServer.model.PossibleFlights;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/methods")
public class WebController {

    private final Controller controller;
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public WebController(Controller controller) {
        this.controller = controller;
    }

    @GetMapping("/getAllPossibleFlights")
    private List<PossibleFlights> getAllPossibleFlights() {
        return controller.getAllPossibleFlights();
    }

    @GetMapping("/getAllFlights")
    private List<FlightsData> getAllFlights() {
        return controller.getAllFlights();
    }

    @GetMapping("/getAvailableFlightsByDate/{date}")
    private List<PossibleFlights> getAvailableFlightsByDate(@PathVariable("date") String date){
            return controller.getAvaliableFlightsByDate(date);
    }

    @PutMapping(value = "/registration/{id}")
    private void createRegistration(@PathVariable("id") String id, @RequestBody String user) {
        try {
            Map<String, String> localSave = objectMapper.readValue(user
                    , new TypeReference<Map<String, String>>() {
                    });
            controller.createRegist(localSave, id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/deleteBookedFlight/{id}")
    private void deleteBookedFlight(@PathVariable("id") String id) {
        controller.deleteBookedFlight(id);
    }


}
