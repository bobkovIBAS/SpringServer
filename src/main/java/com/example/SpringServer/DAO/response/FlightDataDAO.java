package com.example.SpringServer.DAO.response;

import com.example.SpringServer.model.City;
import com.example.SpringServer.model.GuestCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDataDAO {
    private String id;
    private GuestCard guestCard;
    private City fromId;
    private City toId;
    private String planeTypes;
    private String dateFlight;
}
