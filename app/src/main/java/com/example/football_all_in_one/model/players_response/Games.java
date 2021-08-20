package com.example.football_all_in_one.model.players_response;

import com.google.gson.annotations.SerializedName;

public class Games {
    @SerializedName("appearences")
    private int appearances;
    private int lineups;
    private int number;
    private String position;
    private String rating;
    private boolean captain;

    public Games(int appearances, int lineups, int number, String position, String rating, boolean captain) {
        this.appearances = appearances;
        this.lineups = lineups;
        this.number = number;
        this.position = position;
        this.rating = rating;
        this.captain = captain;
    }

    public int getAppearances() { return appearances; }

    public int getLineups() { return lineups; }

    public int getNumber() { return number; }

    public String getPosition() { return position; }

    public String getRating() { return rating; }

    public boolean isCaptain() { return captain; }
}

