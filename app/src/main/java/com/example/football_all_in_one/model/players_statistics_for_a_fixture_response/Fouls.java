package com.example.football_all_in_one.model.players_statistics_for_a_fixture_response;

public class Fouls {
    private int drawn;
    private int committed;

    public Fouls(int drawn, int committed) {
        this.drawn = drawn;
        this.committed = committed;
    }

    public int getDrawn() { return drawn; }

    public int getCommitted() { return committed; }
}
