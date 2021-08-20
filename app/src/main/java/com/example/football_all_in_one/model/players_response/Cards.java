package com.example.football_all_in_one.model.players_response;

import com.google.gson.annotations.SerializedName;

public class Cards {
    private int yellow;
    private int red;
    @SerializedName("yellowred")
    private int yellowRed;

    public Cards(int yellow, int red, int yellowRed) {
        this.yellow = yellow;
        this.red = red;
        this.yellowRed = yellowRed;
    }

    public int getYellow() { return yellow; }

    public int getRed() { return red; }

    public int getYellowRed() { return yellowRed; }
}

