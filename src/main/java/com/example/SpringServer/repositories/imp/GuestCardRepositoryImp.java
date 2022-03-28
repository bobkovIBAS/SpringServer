package com.example.SpringServer.repositories.imp;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.example.SpringServer.model.GuestCard;
import org.bson.types.ObjectId;
import com.example.SpringServer.repositories.GuestCardRepository;
import com.example.SpringServer.server.PUtill;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class GuestCardRepositoryImp implements GuestCardRepository {
    private MongoCollection<GuestCard> collectionGuestCard = PUtill.getCollection("guestcard",GuestCard.class);

    @Override
    public GuestCard getById(ObjectId getId) {
        return (GuestCard)collectionGuestCard.find(Filters.eq(getId));
    }

    @Override
    public List<GuestCard> findAll() {
        List<GuestCard> gcId = new ArrayList<>();
        collectionGuestCard.find().forEach((Consumer<GuestCard>) doc ->{
            gcId.add(doc);
        });
        return gcId;
    }
    @Override
    public void save(GuestCard guestCard) {
        collectionGuestCard.insertOne(guestCard);
    }
}
