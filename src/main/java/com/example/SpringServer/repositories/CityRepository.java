/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SpringServer.repositories;

import com.example.SpringServer.model.City;
import com.example.SpringServer.model.GuestCard;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CityRepository extends MongoRepository<City, String> {
    List<City> findAll();
    City insert(City city);
}
