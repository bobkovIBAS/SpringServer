package com.example.SpringServer.model;

import org.bson.types.ObjectId;

import java.io.Serializable;

public class City implements Serializable{
    private static final long SerialVersionUID = 1L;
    private ObjectId objectId;
    private int id;
    private String name;
    private int region;

    public City() {
    }

    public City(int id, String name, int region) {
        this.id = id;
        this.name = name;
        this.region = region;
    }

    public City(String name, int region) {
        this.name = name;
        this.region = region;
    }
    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }
    
    @Override
    public String toString() {
      return "City [name=" + name + ", region=" + region + "]";
    }
    
}
