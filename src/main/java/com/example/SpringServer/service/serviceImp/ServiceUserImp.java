package com.example.SpringServer.service.serviceImp;

import com.example.SpringServer.DAO.PossibleFlightDAO;
import com.example.SpringServer.DAO.SearchPossibleFlightDAO;
import com.example.SpringServer.model.*;
import com.example.SpringServer.repositories.*;
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
    private UserRepository userRepository;
    @Autowired
    public ServiceUserImp(GuestCardRepository guestRepository, PossibleFlightsRepository possibleFlightsRepository, CityRepository cityRepository, FlightsDataRepository flightRepository, UserRepository userRepository) {
        this.guestRepository = guestRepository;
        this.possibleFlightsRepository = possibleFlightsRepository;
        this.cityRepository = cityRepository;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<FlightsData> getAllFlights(String id) {
        User user = userRepository.findById(id).get();
        return flightRepository.findByGuestCard(user.getGuestCard().get(0));
    }

    public List<PossibleFlightDAO> getAvaliableFlightsByFilter(SearchPossibleFlightDAO filter){
        List<PossibleFlight> listPossibleFlight = possibleFlightsRepository.findAll();
        List<PossibleFlightDAO> filterPossibleFlight = new ArrayList<>();
        String dateFilter = getDateString(filter.getDateFlight());
        for (PossibleFlight possibleFlight:listPossibleFlight){
            possibleFlight.getFlightDate().stream().forEach(i->{
                String dateBase = getDateString(i.getDateFlight());
                if(dateFilter.equals(dateBase)){
                    filterPossibleFlight.add(new PossibleFlightDAO(
                            possibleFlight.getId(),
                            possibleFlight.getFromId(),
                            possibleFlight.getToId(),
                            possibleFlight.getPlaneTypes(),
                            i.getDateFlight(),
                            i.getFreePlaces(),
                            i.getSumTicket()));
                }
            });
        }
        return filterPossibleFlight;

    }

    public List<PossibleFlightDAO> getAvaliableFlightsByDate(String date) {
        List<PossibleFlight> listPossibleFlight = possibleFlightsRepository.findAll();
        List<PossibleFlightDAO> filterPossibleFlight = new ArrayList<>();
        for (PossibleFlight possibleFlight:listPossibleFlight){
            possibleFlight.getFlightDate().stream().forEach(i->{
                String dateBase = getDateString(i.getDateFlight());
                if(date.equals(dateBase)){
                    filterPossibleFlight.add(new PossibleFlightDAO(
                            possibleFlight.getId(),
                            possibleFlight.getFromId(),
                            possibleFlight.getToId(),
                            possibleFlight.getPlaneTypes(),
                            i.getDateFlight(),
                            i.getFreePlaces(),
                            i.getSumTicket()));
                }
            });
        }
        return filterPossibleFlight;

    }

    @Override
    public GuestCard userGuestCard(String id_user, String id_guestCard) {
        User findUser = userRepository.findById(id_user).get();
        GuestCard findGuestCard = findUser.getGuestCard().stream().filter(i->i.getId().equals(id_guestCard)).findFirst().get();
        return findGuestCard;
    }
    @Override
    public List<GuestCard> userGuestCardAll(String id_user) {
        User findUser = userRepository.findById(id_user).get();
        List<GuestCard> findGuestCardAll = findUser.getGuestCard();
        return findGuestCardAll;
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

    public void createRegist(GuestCard guestCard, String id, String id_user) {
        GuestCard guestCardCreate = new GuestCard(guestCard.getSurname(),guestCard.getName(),guestCard.getPassport());
        User findUser = userRepository.findById(id_user).get();
        List<GuestCard> findUserGuestCard = findUser.getGuestCard();
        saveGuestCard(guestCardCreate);
        findUserGuestCard.add(guestCardCreate);
        findUser.setGuestCard(findUserGuestCard);
        userRepository.save(findUser);
        createFlightData(guestCardCreate, id);
    }

    @Override
    public void createRegistWithGuestCard(String id, String id_user, GuestCard guestCard) {
        User findUser = userRepository.findById(id_user).get();
        GuestCard findUserGuestCard = findUser.getGuestCard().stream().filter(i->i.getId().equals(guestCard.getId())).findFirst().get();
        createFlightData(findUserGuestCard, id);
    }

    public void createFlightData(GuestCard guestCard, String id) {
        PossibleFlight possibleFligths = possibleFlightsRepository.findById(id).get();
        FlightsData flightData = new FlightsData();
        flightData.setPossibleFlights(possibleFligths);
        flightData.setGuestCard(guestCard);
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

    public List<PossibleFlightDAO> getAllPossibleFlightsDateNow() {
        List<PossibleFlight> listPossibleFlight = possibleFlightsRepository.findAll();
        List<PossibleFlightDAO> filterPossibleFlight = new ArrayList<>();
        //String dateNow = getDateString(new Date());
        for (PossibleFlight possibleFlight:listPossibleFlight){
            possibleFlight.getFlightDate().stream().forEach(i->{
                if(i.getDateFlight().getTime()>= new Date().getTime()){
                    filterPossibleFlight.add(new PossibleFlightDAO(
                            possibleFlight.getId(),
                            possibleFlight.getFromId(),
                            possibleFlight.getToId(),
                            possibleFlight.getPlaneTypes(),
                            i.getDateFlight(),
                            i.getFreePlaces(),
                            i.getSumTicket()));
                }
            });
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
