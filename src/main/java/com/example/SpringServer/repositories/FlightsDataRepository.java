
package com.example.SpringServer.repositories;

import com.example.SpringServer.model.FlightsData;
import com.example.SpringServer.model.GuestCard;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;


public interface FlightsDataRepository extends MongoRepository<FlightsData, String> {
    List<FlightsData> findAll();
    @Query("{ bookingDate : { $gte: ?0 } }")
    List<FlightsData> findAllNowDate(Date date);
    FlightsData insert(FlightsData flightData);
    List<FlightsData> findByGuestCard(GuestCard guestCard);
    FlightsData deleteById(ObjectId id);



}
