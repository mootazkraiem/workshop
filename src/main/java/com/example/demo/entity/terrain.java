package com.example.demo.entity;

public class terrain {

    private int id;
    private String name;
    private String location;
    private int capacity;
    private Status availability_status;

    public terrain(int id, String name, String location, int capacity, Status availability_status) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.availability_status = availability_status;
    }

    public terrain() {
    }

    public Status getAvailability_status() {
        return availability_status;
    }

    public void setAvailability_status(Status availability_status) {
        this.availability_status = availability_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
