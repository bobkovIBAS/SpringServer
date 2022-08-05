package com.example.SpringServer.DAO.response;

import com.example.SpringServer.model.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFlightDataDAO {
    private City fromId;
    private City toId;
    private String planeTypes;
    private String dateFlight;

}
