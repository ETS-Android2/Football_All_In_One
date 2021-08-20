package com.example.football_all_in_one.model.players_response;

public class Birth {
    private String date;
    private String place;
    private String country;

    public Birth(String date, String place, String country) {
        this.date = date;
        this.place = place;
        this.country = country;
    }

    public String getDate() { return date; }

    public String getPlace() { return place; }

    public String getCountry() { return country; }
}

