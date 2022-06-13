package com.example.SpringServer.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import java.util.Date;

@Getter
@Setter
public class PossibleFlightDate {

    private Date dateFlight;
    private int freePlaces;
    private int sumTicket;

    public PossibleFlightDate(Date dateFlight, int freePlaces, int sumTicket) {
        this.dateFlight = dateFlight;
        this.freePlaces = freePlaces;
        this.sumTicket = sumTicket;
    }
}
