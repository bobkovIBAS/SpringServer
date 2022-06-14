package com.example.SpringServer.repositories;

import com.example.SpringServer.model.City;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CityRepository extends MongoRepository<City, String> {
    List<City> findAll();
    City findByRegion(int region);
    City insert(City city);
}
