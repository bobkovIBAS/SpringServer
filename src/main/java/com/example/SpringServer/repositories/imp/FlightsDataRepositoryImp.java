
package com.example.SpringServer.repositories.imp;

import com.example.SpringServer.repositories.FlightsDataRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.example.SpringServer.model.City;
import com.example.SpringServer.model.FlightsData;
import com.example.SpringServer.model.GuestCard;
import org.bson.types.ObjectId;
import com.example.SpringServer.server.PUtill;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class FlightsDataRepositoryImp implements FlightsDataRepository {
    private List<FlightsData> cahceData = new ArrayList<>();
    private MongoCollection<FlightsData> collectionFlightData = PUtill.getCollection("flights_data",FlightsData.class);


    @Override
    public List<FlightsData> getAllFlights() {
        if(cahceData.isEmpty()){
            loadFlightsData();
        }
        return cahceData;

    }

    @Override
    public FlightsData getAllFlightsByUser(GuestCard guestCard) {
        List<FlightsData> find = new ArrayList<>();
        collectionFlightData.find().forEach((Consumer<FlightsData>) doc ->{
            if(doc.getUserId().equals(guestCard)){
                find.add(doc);
            }
        });
        return (FlightsData)find;
    }

    @Override
    public FlightsData getAllByInCity() {
        List<City> find = new ArrayList<>();
        collectionFlightData.find().forEach((Consumer<FlightsData>) doc ->{
                find.add(doc.getPossibleFlights().getToId());
        });
        return (FlightsData)find;
    }

    @Override
    public List<FlightsData> getСache(){
        if(cahceData.isEmpty()){
            List<FlightsData> flightsDataList = new ArrayList<>();
            collectionFlightData.find().forEach((Consumer<FlightsData>) doc ->{
                flightsDataList.add(doc);
            });
            return flightsDataList;
        }
        else{
            return cahceData;
        }
    }

    @Override
    public void deleteFlight(ObjectId id){
        for(FlightsData flighData: cahceData){
            if(flighData.getObjectId().equals(id)){
                collectionFlightData.deleteOne(Filters.eq(id));
                cahceData.remove(flighData);
                break;
            }
        }
    }

    private List<FlightsData> loadFlightsData(){
        List<FlightsData> flightData = new ArrayList<>();
        if(flightData.isEmpty()){
                collectionFlightData.find().forEach((Consumer<FlightsData>) doc ->{
                    flightData.add(doc);
                });
                if(cahceData.isEmpty()){
                    cahceData= flightData;
                }
            }
        else{
            return cahceData;
        }
        return flightData;
    }
    @Override
    public void addCache(FlightsData flightData){
        cahceData.add(flightData);
    }
    @Override
    public void save(FlightsData flightData) {
        collectionFlightData.insertOne(flightData);
    }
 }