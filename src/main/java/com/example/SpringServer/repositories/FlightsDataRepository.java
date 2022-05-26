
package com.example.SpringServer.repositories;

import com.example.SpringServer.model.FlightsData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface FlightsDataRepository extends MongoRepository<FlightsData, String> {
    List<FlightsData> findAll();

    FlightsData insert(FlightsData flightData);

    FlightsData deleteById(ObjectId id);

}
