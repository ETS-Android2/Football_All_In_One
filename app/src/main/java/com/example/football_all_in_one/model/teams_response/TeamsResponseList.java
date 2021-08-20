package com.example.football_all_in_one.model.teams_response;

public class TeamsResponseList {
    private Team team;
    private Venue venue;

    public TeamsResponseList(Team team, Venue venue) {
        this.team = team;
        this.venue = venue;
    }

    public Team getTeam() { return team; }

    public Venue getVenue() { return venue; }

}
