package com.example.football_all_in_one.model.players_response;

import com.google.gson.annotations.SerializedName;

public class Penalty {
    private int scored;
    private int missed;
    private int saved;

    public Penalty(int scored,int missed, int saved) {
        this.scored = scored;
        this.missed = missed;
        this.saved = saved;
    }


    public int getScored() { return scored; }

    public int getMissed() { return missed; }

    public int getSaved() { return saved; }
}

