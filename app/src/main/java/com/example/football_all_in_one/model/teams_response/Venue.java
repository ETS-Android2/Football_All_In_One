package com.example.football_all_in_one.model.teams_response;

public class Venue {
    private int id;
    private String name;
    private String address;
    private String city;
    private int capacity;

    public Venue(int id, String name, String address, String city, int capacity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.capacity = capacity;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getAddress() { return address; }

    public String getCity() { return city; }

    public int getCapacity() { return capacity; }

}
