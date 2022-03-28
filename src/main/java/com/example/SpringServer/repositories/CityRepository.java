/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SpringServer.repositories;

import com.example.SpringServer.model.City;
import org.bson.types.ObjectId;

import java.util.List;


public interface CityRepository {
    City getCityById(ObjectId id);
    List<City> findAll();
    void save(City city);
}
