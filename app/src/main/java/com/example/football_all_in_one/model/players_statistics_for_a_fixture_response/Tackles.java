package com.example.football_all_in_one.model.players_statistics_for_a_fixture_response;

public class Tackles {
    private int total;
    private int blocks;
    private int interceptions;

    public Tackles(int total, int blocks, int interceptions) {
        this.total = total;
        this.blocks = blocks;
        this.interceptions = interceptions;
    }

    public int getTotal() { return total; }

    public int getBlocks() { return blocks; }

    public int getInterceptions() { return interceptions; }
}
