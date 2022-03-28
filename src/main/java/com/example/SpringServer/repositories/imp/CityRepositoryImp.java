package com.example.SpringServer.repositories.imp;

import com.example.SpringServer.repositories.CityRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.example.SpringServer.model.City;
import org.bson.types.ObjectId;
import com.example.SpringServer.server.PUtill;

import java.util.List;

public class CityRepositoryImp implements CityRepository {
     private MongoCollection<City> collectionCity = PUtill.getCollection("city",City.class);
    
   @Override
    public City getCityById(ObjectId id) {
        return collectionCity.find(Filters.eq("id", id)).first();
    }

    @Override
    public List<City> findAll() {
        List<City> cityId = (List<City>)collectionCity.find();
        return cityId;
    }
    
    public List<City> loadCity(){
        List<City> city = (List<City>)collectionCity.find();
        return city;
    }
    
    @Override
    public void save(City city) {
        collectionCity.insertOne(city);
    }
    
}
