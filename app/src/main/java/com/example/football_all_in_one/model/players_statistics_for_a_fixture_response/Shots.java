package com.example.football_all_in_one.model.players_statistics_for_a_fixture_response;

public class Shots {
    private int total;
    private int on;

    public Shots(int total, int on) {
        this.total = total;
        this.on = on;
    }

    public int getTotal() { return total; }

    public int getOn() { return on; }
}
