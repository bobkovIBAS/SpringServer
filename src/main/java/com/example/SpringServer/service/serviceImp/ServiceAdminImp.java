package com.example.SpringServer.service.serviceImp;

import com.example.SpringServer.model.City;
import com.example.SpringServer.model.FlightsData;
import com.example.SpringServer.model.PossibleFlight;
import com.example.SpringServer.repositories.CityRepository;
import com.example.SpringServer.repositories.FlightsDataRepository;
import com.example.SpringServer.repositories.GuestCardRepository;
import com.example.SpringServer.repositories.PossibleFlightsRepository;
import com.example.SpringServer.service.ServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAdminImp implements ServiceAdmin {
    private GuestCardRepository guestRepository;
    private PossibleFlightsRepository possibleFlightsRepository;
    private CityRepository cityRepository;
    private FlightsDataRepository flightRepository;

    @Autowired
    public ServiceAdminImp(GuestCardRepository guestRepository, PossibleFlightsRepository possibleFlightsRepository, CityRepository cityRepository, FlightsDataRepository flightRepository) {
        this.guestRepository = guestRepository;
        this.possibleFlightsRepository = possibleFlightsRepository;
        this.cityRepository = cityRepository;
        this.flightRepository = flightRepository;
    }
    @Override
    public void createPossibleFlight(PossibleFlight possibleFlight){
        possibleFlightsRepository.insert(possibleFlight);
    }
    @Override
    public Boolean createCity(City city){
        City checkCreate = cityRepository.findByRegion(city.getRegion());
        if(checkCreate==null){
            return false;
        } else {
            cityRepository.insert(city);
            return true;
        }

    }
    @Override
    public void editCity(City city){
        cityRepository.save(city);
    }
    @Override
    public void deleteCity(String id){
        cityRepository.deleteById(id);
    }
    @Override
    public void editPossibleFlight(PossibleFlight possibleFlight){
        possibleFlightsRepository.save(possibleFlight);
    }
    @Override
    public void deletePossibleFlight(String id) {
        possibleFlightsRepository.deleteById(id);
    }


}
