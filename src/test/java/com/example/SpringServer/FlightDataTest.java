package com.example.SpringServer;


import com.example.SpringServer.controller.Controller;
import com.example.SpringServer.model.City;
import com.example.SpringServer.model.PossibleFlights;
import com.example.SpringServer.repositories.FlightsDataRepository;
import com.example.SpringServer.repositories.GuestCardRepository;
import com.example.SpringServer.repositories.PossibleFlightsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class FlightDataTest {

    @Autowired
    private Controller controller;

    @MockBean
    private PossibleFlightsRepository possibleFlightsRepository;

    @MockBean
    private FlightsDataRepository flightDataRepository;

    @MockBean
    private GuestCardRepository guestCardRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private List<PossibleFlights> listPossibleFlights = new ArrayList<>();

    @Before
    public void loadDataBaseTest() {
        listPossibleFlights.add(new PossibleFlights(new City("Тольятти",63),
                new City("Мохнатово",33),
                "Ту-145",10 ,"2022-04-26",500));
        listPossibleFlights.add(new PossibleFlights(new City("Сочи",02),
                new City("Мохнатово",33),
                "Ту-14", 10 , "2022-04-26", 500));
        listPossibleFlights.add(new PossibleFlights(new City("Москва",77),
                new City("Подольск",22),
                "TukishAir", 5 ,"2022-04-25",500));
        //mongoTemplate.save(listPossibleFlights, "possible_flight");
    }

    @Test
    public void getAllPossibleFlightTest(){
        List<PossibleFlights> possibleFligths = controller.getAllPossibleFlights();
        Mockito.verify(possibleFlightsRepository, Mockito.times(1)).findAll();
    }

    /*@Test
    public void getAvaliableFlightsByDateTestTrue(){
        String dateFind = "2022-01-02";
        List<PossibleFlights> getAvaliableFlightsByDate = controller.getAvaliableFlightsByDate(dateFind);
        Mockito.verify(possibleFlightsRepository, Mockito.times(1)).getFlightsEqualBydate(dateFind);
        Mockito.when(possibleFlightsRepository.getFlightsEqualBydate(dateFind)).thenReturn(getAvaliableFlightsByDate=
                mongoTemplate.findAll(PossibleFlights.class,"possible_flights"));
        boolean checkgetAvaliableFlightsByDate = getAvaliableFlightsByDate.stream().anyMatch(find->find.getDateFlights().equals(dateFind));
        Assert.assertTrue(checkgetAvaliableFlightsByDate);
        PossibleFlights findFlight = getAvaliableFlightsByDate.stream().filter(s->s.getDateFlights().equals(dateFind)).findFirst().get();
        FlightsData flightData = new FlightsData();
        flightData.setPossibleFlights(findFlight);
        flightData.setGuestCard(new GuestCard("test","testiv","2234352"));
        controller.createFlightData(flightData.getGuestCard(),findFlight.getId());
        Mockito.verify(flightDataRepository, Mockito.times(1)).insert(flightData);
        Mockito.when(flightDataRepository.insert(flightData)).thenReturn(mongoTemplate.insert(flightData,"flights_data"));

    }

    @Test
    private void getAllFlightsTest(){
        List<FlightsData> getFlight = controller.getAllFlights();
        Mockito.verify(flightDataRepository, Mockito.times(1)).findAll();
        Mockito.when(flightDataRepository.findAll()).thenReturn(getFlight=mongoTemplate.findAll(FlightsData.class,"flights_data"));
    }

    @Test
    public void createFlightDataTrueTest() {
        String idTest = "623c40c50cafc8000881b75f";
        GuestCard guestCard = new GuestCard("test","testovich","228");
        List<PossibleFlights> possibleFligths = new ArrayList<>();
        //Mockito.when(loadPossibleFlightJson()).thenReturn(possibleFligths);
        Mockito.doReturn(possibleFligths).when(loadPossibleFlightJson());
        FlightsData flightData = new FlightsData();
        possibleFligths.forEach(item -> {
            if (item.getId().equals(idTest)) {
                flightData.setId(String.valueOf(flightData.hashCode()));
                flightData.setPossibleFlights(item);
                flightData.setGuestCard(guestCard);
            }
        });
        controller.saveGuestCard(guestCard);
        Mockito.verify(guestCardRepository, Mockito.times(1)).save(guestCard);
        Assert.assertNotNull(flightData.getGuestCard());
        controller.saveFlightsData(flightData);
        Mockito.verify(flightDataRepository, Mockito.times(1)).insert(flightData);
        saveFlightData(flightData,"insert");
    }

    @Test
    public void getAvaliableFlightsByDateTestTrue(){
        String dateFind = "2022-01-02";
        List<PossibleFlights> getAvaliableFlightsByDate = controller.getAvaliableFlightsByDate(dateFind);
        Mockito.verify(possibleFlightsRepository, Mockito.times(1)).getFlightsEqualBydate(dateFind);
        Mockito.when(loadPossibleFlightJson()).thenReturn(getAvaliableFlightsByDate);
        boolean checkgetAvaliableFlightsByDate = getAvaliableFlightsByDate.stream().anyMatch(find->find.getDateFlights().equals(dateFind));
        Assert.assertTrue(checkgetAvaliableFlightsByDate);
        PossibleFlights findFlight = getAvaliableFlightsByDate.stream().filter(s->s.getDateFlights().equals(dateFind)).findFirst().get();
        FlightsData flightData = new FlightsData();
        flightData.setPossibleFlights(findFlight);
        flightData.setGuestCard(new GuestCard("test","testiv","2234352"));
        controller.createFlightData(flightData.getGuestCard(),findFlight.getId());
        controller.saveFlightsData(flightData);
        Mockito.verify(flightDataRepository, Mockito.times(1)).insert(flightData);
        saveFlightData(flightData,"insert");
    }

    @Test
    public void getAvaliableFlightsByDateTestFalse(){
        String dateFind = "2022-01-05";
        List<PossibleFlights> getAvaliableFlightsByDate = controller.getAvaliableFlightsByDate(dateFind);
        Mockito.verify(possibleFlightsRepository, Mockito.times(1)).getFlightsEqualBydate(dateFind);
        Mockito.when(loadPossibleFlightJson()).thenReturn(getAvaliableFlightsByDate);
        boolean checkgetAvaliableFlightsByDate = getAvaliableFlightsByDate.stream().anyMatch(find->find.getDateFlights().equals(dateFind));
        Assert.assertFalse(checkgetAvaliableFlightsByDate);
    }

    @Test
    public void deleteBookedFlightTestTrue(){
        String id = "623c40d10cafc8000881b760";
        controller.deleteBookedFlight(id);
        Mockito.verify(flightDataRepository, Mockito.times(1)).deleteById(id);
        List<FlightsData> flightData= new ArrayList<>();
        Mockito.when(getAllFlightsTestJson()).thenReturn(flightData);
        FlightsData delete = flightData.stream().filter(s->s.getPossibleFlights().getId().equals(id)).findFirst().get();
        Assert.assertNotNull(delete);
        flightData.remove(delete);
        boolean checkDelete = flightData.stream().anyMatch(s->s.equals(delete));
        Assert.assertFalse(checkDelete);
        saveFlightData(delete,"update");

    }
    @Test
    public void deleteBookedFlightTestFalse(){
        String id = "623c40d10cafc8000881b760";
        controller.deleteBookedFlight(id);
        Mockito.verify(flightDataRepository, Mockito.times(1)).deleteById(id);
        List<FlightsData> flightData= new ArrayList<>();
        Mockito.when(getAllFlightsTestJson()).thenReturn(flightData);
        Optional<FlightsData> delete = flightData.stream().filter(s->s.getPossibleFlights().getId().equals(id)).findFirst();
        Assert.assertTrue(delete.isEmpty());
    }

    @After
    public void saveFlightData(FlightsData flightsData, String type){
        ObjectMapper mapper = new ObjectMapper();
        String line = "";

        try {
            if(type.equals("insert")) {
                try (FileWriter fw = new FileWriter(pathFlightData, true);
                     BufferedWriter bw = new BufferedWriter(fw)) {
                    String flightsDataInputJson = mapper.writeValueAsString(flightsData);
                    bw.write(flightsDataInputJson);
                    bw.newLine();
                }
            } if(type.equals("update")){
                BufferedReader reader = new BufferedReader(new FileReader(pathFlightData));
                BufferedWriter writer = new BufferedWriter(new FileWriter(pathFlightData));
                String flightsDataInputJson = mapper.writeValueAsString(flightsData);
                while ((line = reader.readLine()) != "") {
                    if (!line.equals(flightsDataInputJson)) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
                reader.close();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void savePossibleFlight(PossibleFlights possibleFlights, String type){
        ObjectMapper mapper = new ObjectMapper();
        String line = "";

        try {
            if(type.equals("insert")) {
                try (FileWriter fw = new FileWriter(pathPossibleFlight, true);
                     BufferedWriter bw = new BufferedWriter(fw)) {
                    String possibleFlightInputJson = mapper.writeValueAsString(possibleFlights);
                    bw.write(possibleFlightInputJson);
                    bw.newLine();
                }
            } if(type.equals("update")){
                BufferedReader reader = new BufferedReader(new FileReader(pathPossibleFlight));
                BufferedWriter writer = new BufferedWriter(new FileWriter(pathPossibleFlight));
                String possibleFlightInputJson = mapper.writeValueAsString(possibleFlights);
                while ((line = reader.readLine()) != "") {
                    if (!line.equals(possibleFlightInputJson)) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
                reader.close();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

}
