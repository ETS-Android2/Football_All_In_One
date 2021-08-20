package com.example.football_all_in_one.model.players_statistics_for_a_fixture_response;

public class Cards {
    private int yellow;
    private int red;

    public Cards(int yellow, int red) {
        this.yellow = yellow;
        this.red = red;
    }

    public int getYellow() { return yellow; }

    public int getRed() { return red; }
}
