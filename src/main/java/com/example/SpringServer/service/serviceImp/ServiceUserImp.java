package com.example.SpringServer.service.serviceImp;

import com.example.SpringServer.DAO.response.CreateFlightDataDAO;
import com.example.SpringServer.DAO.PossibleFlightDAO;
import com.example.SpringServer.DAO.SearchPossibleFlightDAO;
import com.example.SpringServer.DAO.response.FlightDataDAO;
import com.example.SpringServer.model.*;
import com.example.SpringServer.repositories.*;
import com.example.SpringServer.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        List<FlightsData> flightsDataList = new ArrayList<>();
        User user = userRepository.findById(id).get();
        user.getGuestCard().stream().forEach(i->{
            flightRepository.findByGuestCard(i);
        });
        return flightsDataList;
    }

    public List<PossibleFlightDAO> getAvaliableFlightsByFilter(SearchPossibleFlightDAO filter){
        List<PossibleFlight> listPossibleFlight = possibleFlightsRepository.findAll();
        List<PossibleFlightDAO> filterPossibleFlight = new ArrayList<>();
        String dateFilter = getDateString(filter.getDateFlight());
        for (PossibleFlight possibleFlight:listPossibleFlight){
            possibleFlight.getFlightDate().stream().forEach(i->{
                String dateBase = getDateString(i.getDateFlight());
                if(dateFilter.equals(dateBase)){
                    String dateFilterNow = getDateString(i.getDateFlight());
                    filterPossibleFlight.add(new PossibleFlightDAO(
                            possibleFlight.getId(),
                            possibleFlight.getFromId(),
                            possibleFlight.getToId(),
                            possibleFlight.getPlaneTypes(),
                            dateFilterNow,
                            i.getFreePlaces(),
                            i.getSumTicket()));
                }
            });
        }
        return filterPossibleFlight;

    }

    public List<FlightDataDAO> getAllFlightsGuestCard(String id, GuestCard guestCard){
        List<FlightDataDAO> flightDataDAOList = new ArrayList<>();
        User user = userRepository.findById(id).get();
        GuestCard findGuestCard = user.getGuestCard().stream().filter(i->i.getPassport().equals(guestCard.getPassport())).findFirst().get();
        List<FlightsData>  flightsDataList = flightRepository.findByGuestCard(findGuestCard);
        flightsDataList.stream().forEach(s->{
            flightDataDAOList.add(new FlightDataDAO(s.getId(),
                    s.getGuestCard(),
                    s.getPossibleFlights().getFromId(),
                    s.getPossibleFlights().getToId(),
                    s.getPossibleFlights().getPlaneTypes(),
                    getDateString(s.getBookingDate())));
        });
        return flightDataDAOList;
    }

    public List<PossibleFlightDAO> getAvaliableFlightsByDate(String date) {
        List<PossibleFlight> listPossibleFlight = possibleFlightsRepository.findAll();
        List<PossibleFlightDAO> filterPossibleFlight = new ArrayList<>();
        for (PossibleFlight possibleFlight:listPossibleFlight){
            possibleFlight.getFlightDate().stream().forEach(i->{
                String dateBase = getDateString(i.getDateFlight());
                if(date.equals(dateBase)){
                    String dateFilter = getDateString(i.getDateFlight());
                    filterPossibleFlight.add(new PossibleFlightDAO(
                            possibleFlight.getId(),
                            possibleFlight.getFromId(),
                            possibleFlight.getToId(),
                            possibleFlight.getPlaneTypes(),
                            dateFilter,
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
        FlightsData flightsData= flightRepository.findById(id).get();
        String dateFilter = getDateString(flightsData.getBookingDate());
        PossibleFlight possibleFlight = possibleFlightsRepository.findById(flightsData.getPossibleFlights().getId()).get();
        possibleFlight.getFlightDate().stream().forEach(s->{
            String date = getDateString(s.getDateFlight());
            if(dateFilter.equals(date)) {
                s.setFreePlaces(s.getFreePlaces() + 1);
            }
        });
        possibleFlightsRepository.save(possibleFlight);
        flightRepository.deleteById(id);
    }

    public void saveGuestCard(GuestCard gc) {
        guestRepository.save(gc);
    }

    public CreateFlightDataDAO createRegist(GuestCard guestCard, String id, String id_user, String date) {
        GuestCard guestCardCreate = new GuestCard(guestCard.getSurname(),guestCard.getName(),guestCard.getPassport());
        User findUser = userRepository.findById(id_user).get();
        List<GuestCard> findUserGuestCard = findUser.getGuestCard();
        saveGuestCard(guestCardCreate);
        findUserGuestCard.add(guestCardCreate);
        findUser.setGuestCard(findUserGuestCard);
        userRepository.save(findUser);
        CreateFlightDataDAO createFlightDataDAO = createFlightData(guestCardCreate, id, date);
        return createFlightDataDAO;
    }

    @Override
    public CreateFlightDataDAO createRegistWithGuestCard(String id, String id_user, GuestCard guestCard, String date) {
        User findUser = userRepository.findById(id_user).get();
        GuestCard findUserGuestCard = findUser.getGuestCard().stream().filter(i->i.getId().equals(guestCard.getId())).findFirst().get();
        return createFlightData(findUserGuestCard, id, date);
    }

    public CreateFlightDataDAO createFlightData(GuestCard guestCard, String id, String date) {
        PossibleFlight possibleFligths = possibleFlightsRepository.findById(id).get();
        FlightsData flightData = new FlightsData();
        possibleFligths.getFlightDate().stream().forEach(i->{
            String dateFilter = getDateString(i.getDateFlight());
            if(dateFilter.equals(date)){
                i.setFreePlaces(i.getFreePlaces()-1);
                flightData.setBookingDate(i.getDateFlight());
            }
        });
        possibleFlightsRepository.save(possibleFligths);
        flightData.setPossibleFlights(possibleFligths);
        flightData.setGuestCard(guestCard);
        saveFlightsData(flightData);
        CreateFlightDataDAO createFlightDataDAO = new CreateFlightDataDAO(flightData.getPossibleFlights().getFromId()
                ,flightData.getPossibleFlights().getToId()
                ,flightData.getPossibleFlights().getPlaneTypes()
                ,getDateString(flightData.getBookingDate()));
        return createFlightDataDAO;
    }

    public void saveFlightsData(FlightsData flightData) {
        flightRepository.insert(flightData);
    }

    public List<PossibleFlightDAO> getAllPossibleFlightsDateNow() {
        List<PossibleFlight> listPossibleFlight = possibleFlightsRepository.findAll();
        List<PossibleFlightDAO> filterPossibleFlight = new ArrayList<>();
        for (PossibleFlight possibleFlight:listPossibleFlight){
            possibleFlight.getFlightDate().stream().forEach(i->{
                if(i.getDateFlight().getTime()>= new Date().getTime()){
                    if(i.getFreePlaces()> 0) {
                        String dateFilter = getDateString(i.getDateFlight());
                        filterPossibleFlight.add(new PossibleFlightDAO(
                                possibleFlight.getId(),
                                possibleFlight.getFromId(),
                                possibleFlight.getToId(),
                                possibleFlight.getPlaneTypes(),
                                dateFilter,
                                i.getFreePlaces(),
                                i.getSumTicket()));
                    }
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

}
