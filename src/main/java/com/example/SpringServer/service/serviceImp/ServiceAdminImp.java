package com.example.SpringServer.service.serviceImp;

import com.example.SpringServer.DAO.response.FlightDataDAO;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        if(checkCreate!=null){
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

    @Override
    public void deleteFlightDataUser(String id) {
        FlightsData flightsData = flightRepository.findById(id).get();
        PossibleFlight possibleFligths = flightsData.getPossibleFlights();
        String flightDataDate = getDateString(flightsData.getBookingDate());
        possibleFligths.getFlightDate().stream().forEach(i->{
            String dateFilter = getDateString(i.getDateFlight());
            if(dateFilter.equals(flightDataDate)){
                i.setFreePlaces(i.getFreePlaces()-1);
            }
        });
        flightRepository.deleteById(id);
    }

    @Override
    public List<FlightDataDAO> getAllFlightData() {
        List<FlightDataDAO> flightDataDAOList = new ArrayList<>();
        List<FlightsData> flightsData = flightRepository.findAllNowDate(new Date());
        flightsData.stream().forEach(s->{
            flightDataDAOList.add(new FlightDataDAO(s.getId(),
                    s.getGuestCard(),
                    s.getPossibleFlights().getFromId(),
                    s.getPossibleFlights().getToId(),
                    s.getPossibleFlights().getPlaneTypes(),
                    getDateString(s.getBookingDate())));
        });
        return flightDataDAOList;
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


}
