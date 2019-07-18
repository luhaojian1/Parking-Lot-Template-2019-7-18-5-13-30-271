package com.thoughtworks.parking_lot.module;

import javax.persistence.*;

@Entity
@Table(name = "parking_lot")
public class ParkingLot {

    @Id
    private String id;

    private String name;

    private int capacity;

    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
