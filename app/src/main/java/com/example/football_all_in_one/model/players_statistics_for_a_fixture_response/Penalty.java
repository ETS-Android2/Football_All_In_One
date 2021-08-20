package com.example.football_all_in_one.model.players_statistics_for_a_fixture_response;

import com.google.gson.annotations.SerializedName;

public class Penalty {
    private int won;
    @SerializedName("commited")
    private int committed;
    private int score;
    private int missed;
    private int saved;

    public Penalty(int won, int committed, int score, int missed, int saved) {
        this.won = won;
        this.committed = committed;
        this.score = score;
        this.missed = missed;
        this.saved = saved;
    }

    public int getWon() { return won; }

    public int getCommitted() { return committed; }

    public int getScore() { return score; }

    public int getMissed() { return missed; }

    public int getSaved() { return saved; }
}
