package com.example.SpringServer;

import com.example.SpringServer.controller.Controller;
import com.example.SpringServer.model.City;
import com.example.SpringServer.model.FlightsData;
import com.example.SpringServer.model.GuestCard;
import com.example.SpringServer.model.PossibleFlights;
import com.example.SpringServer.repositories.FlightsDataRepository;
import com.example.SpringServer.repositories.GuestCardRepository;
import com.example.SpringServer.repositories.PossibleFlightsRepository;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=4.4.2")
public class FlightDataTestMongo {
    @Autowired
    private Controller controller;
    @Autowired
    private PossibleFlightsRepository possibleFlightsRepository;
    @Autowired
    private GuestCardRepository guestCardRepository;
    @Autowired
    private FlightsDataRepository flightDataRepository;
    public MongodExecutable mongodExecutable;
    public MongoTemplate mongoTemplate;

    @Before
    public void loadDataBaseTest(){
        String ip = "localhost";
        int port = 27017;
        ImmutableMongodConfig mongodConfig = MongodConfig
                .builder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(ip, port, false))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);
        try {
            mongodExecutable.start();
            mongoTemplate = new MongoTemplate(MongoClients.create("mongodb://"+ip+":"+port+""), "test");
            possibleFlightsRepository.insert(new PossibleFlights(new City("Сочи",02),
                    new City("Мохнатово",33),
                    "Ту-14", 10 , "2022-04-26", 500));
            possibleFlightsRepository.insert(new PossibleFlights(new City("Москва",77),
                    new City("Подольск",22),
                    "TukishAir", 5 ,"2022-04-25",500));
            possibleFlightsRepository.insert(new PossibleFlights(new City("Тольятти",63),
                    new City("Мохнатово",33),
                    "Ту-145",10 ,"2022-04-26",500));
        } catch (IOException e) {
            mongodExecutable.stop();
        }
    }

    @Test
    public void getAllPossibleFligthTest(){
        List<PossibleFlights> possibleFlightsList = controller.getAllPossibleFlights();
        boolean checkEmptyPossibleFlightsList = possibleFlightsList.isEmpty();
        Assert.assertFalse(checkEmptyPossibleFlightsList);
    }

    @Test
    public void getAvaliableFlightsByDateTestTrue(){
        String dateFind = "2022-04-26";
        List<PossibleFlights> getAvaliableFlightsByDate = controller.getAvaliableFlightsByDate(dateFind);
        boolean checkGetAvaliableFlightsByDate = getAvaliableFlightsByDate.stream().anyMatch(find->find.getDateFlights().equals(dateFind));
        Assert.assertTrue(checkGetAvaliableFlightsByDate);
        PossibleFlights findFlight = getAvaliableFlightsByDate.stream().filter(s->s.getDateFlights().equals(dateFind)).findFirst().get();
        FlightsData flightData = new FlightsData();
        flightData.setPossibleFlights(findFlight);
        flightData.setGuestCard(new GuestCard("test","testiv","2234352"));
        controller.createFlightData(flightData.getGuestCard(),findFlight.getId());
    }
    @Test
    public void createRegistTest(){
        List<PossibleFlights> possibleFlightsList = controller.getAllPossibleFlights();
        GuestCard guestCard = new GuestCard("test","testovich","36127643");
        Map<String,String> localSave = new HashMap<>();
        localSave.put("name",guestCard.getName());
        localSave.put("surname",guestCard.getSurname());
        localSave.put("passport",guestCard.getPassport());
        boolean checkExistGuestCard = guestCardRepository.findAll().stream().anyMatch(g->g.getPassport().equals(guestCard.getPassport()));
        Assert.assertFalse(checkExistGuestCard);
        controller.createRegist(localSave,possibleFlightsList.get(0).getId());
        boolean checkCreateRegistGuestCard =  guestCardRepository.findAll().stream().anyMatch(g->g.getId().equals(guestCard.getId()));
        Assert.assertTrue(checkCreateRegistGuestCard);
        boolean checkCreateRegistFlightData=  flightDataRepository.findAll().stream().anyMatch(g->g.getId().equals(guestCard.getId()));
        Assert.assertTrue(checkCreateRegistFlightData);
    }
    @Test
    public void createOnlyFlightDataTest() {
        GuestCard guestCard = new GuestCard("test","guveit","228");
        List<PossibleFlights> possibleFlightsList = controller.getAllPossibleFlights();
        FlightsData flightData = new FlightsData();
        flightData.setPossibleFlights(possibleFlightsList.get(0));
        flightData.setGuestCard(guestCard);
        boolean checkFlightDataExist = flightDataRepository.findAll().stream().anyMatch(f->
                f.getPossibleFlights().equals(possibleFlightsList.get(0))&& f.getGuestCard().getPassport().equals(guestCard.getPassport()));
        Assert.assertFalse(checkFlightDataExist);
        controller.saveFlightsData(flightData);
        boolean checkCreateFlightData = flightDataRepository.findAll().stream().anyMatch(f->f.getGuestCard().getSurname().equals("guveit"));
        Assert.assertTrue(checkCreateFlightData);
    }
    @Test
    public void getAllGuestCardListTest(){
        List<GuestCard> guestCardList = controller.getAllGuestCard();
        boolean checkEmptyGuestCardList = guestCardList.isEmpty();
        Assert.assertFalse(checkEmptyGuestCardList);
    }

    @Test
    public void getDateMaskStringDate(){
        Date getDate = controller.getMaskDate("2022-04-04");
        Assert.assertEquals(getDate.getClass(),Date.class);
    }
    @Test
    public void deleteBookedFlightTestTrue(){
        GuestCard guestCard = new GuestCard("deleted","flightdata","228");
        List<PossibleFlights> possibleFlightsList = controller.getAllPossibleFlights();
        FlightsData flightData = new FlightsData();
        flightData.setPossibleFlights(possibleFlightsList.get(0));
        flightData.setGuestCard(guestCard);
        controller.saveFlightsData(flightData);
        FlightsData flightsDataList = flightDataRepository.findAll().stream().findFirst().get();
        String flightDataId = flightsDataList.getId();
        controller.deleteBookedFlight(flightsDataList.getId());
        FlightsData checkDeleted = flightDataRepository.findById(flightDataId).get();
        Assert.assertNotNull(checkDeleted);
    }

    @After
    public void closeDataBase(){
        mongodExecutable.stop();
    }
}
