package com.example.football_all_in_one.model.fixtures_response;

public class Fixture {
    private int id;
    private String referee;
    private long timestamp;
    private String date;
    private Venue venue;
    private Status status;

    public Fixture(int id, String referee, long timestamp, String date, Venue venue, Status status) {
        this.id = id;
        this.referee = referee;
        this.timestamp = timestamp;
        this.date = date;
        this.venue = venue;
        this.status = status;
    }

    public int getId() { return id; }

    public String getReferee() { return referee; }

    public long getTimestamp() { return timestamp; }

    public String getDate() { return date; }

    public Venue getVenue() { return venue; }

    public Status getStatus() { return status; }
}
