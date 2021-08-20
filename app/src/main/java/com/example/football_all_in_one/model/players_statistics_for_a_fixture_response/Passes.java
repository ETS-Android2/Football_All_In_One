package com.example.football_all_in_one.model.players_statistics_for_a_fixture_response;

public class Passes {
    private int total;
    private int key;
    private String accuracy;

    public Passes(int total, int key, String accuracy) {
        this.total = total;
        this.key = key;
        this.accuracy = accuracy;
    }

    public int getTotal() { return total; }

    public int getKey() { return key; }

    public String getAccuracy() { return accuracy; }
}
