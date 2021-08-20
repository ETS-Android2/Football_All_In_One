package com.example.football_all_in_one.model.teams_response;

public class Team {
    private int id;
    private String name;
    private String country;
    private int founded;
    private boolean national;
    private String logo;

    // Constructor
    public Team(int id, String name, String country, int founded, boolean national, String logo) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.founded = founded;
        this.national = national;
        this.logo = logo;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getCountry() { return country; }

    public int getFounded() { return founded; }

    public boolean getNational() { return national; }

    public String getLogo() { return logo; }
}
