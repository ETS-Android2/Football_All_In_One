package com.example.football_all_in_one.model.fixtures_response;

public class Venue {
    private int id;
    private String name;
    private String city;

    public Venue(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getCity() { return city; }
}
