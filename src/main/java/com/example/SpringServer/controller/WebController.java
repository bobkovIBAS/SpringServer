package com.example.SpringServer.controller;

import com.example.SpringServer.model.FlightsData;
import com.example.SpringServer.model.PossibleFlights;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/methods")
public class WebController {
    Controller controller = new Controller();

    @GetMapping("/getAllPossibleFlights")
    private List<PossibleFlights> getAllPossibleFlights() {
        return controller.getAllPossibleFlights();
    }

    @GetMapping("/getAllFlights")
    private List<FlightsData> getAllFlights() {
        return controller.getAllFlights();
    }

    @PutMapping("/registration/{id}/{name}/{surname}/{passport}")
    private void createRegistration(@PathVariable("id") String id, @PathVariable("name") String name,
                                    @PathVariable("surname") String surname, @PathVariable("passport") String passport) {
        Map<String, String> localSave = new HashMap<>();
        localSave.put("name", name);
        localSave.put("surname", surname);
        localSave.put("passport", passport);
        controller.createRegist(localSave, id);
    }

    @PutMapping("/deleteBookedFlight/{id}")
    private void deleteBookedFlight(@PathVariable("id") String id) {
        controller.deleteBookedFlight(id);
    }
}
