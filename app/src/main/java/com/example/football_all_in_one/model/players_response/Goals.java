package com.example.football_all_in_one.model.players_response;

import com.google.gson.annotations.SerializedName;

public class Goals {
    private int total;
    private int assists;
    private int saves;

    public Goals(int total,int assists, int saves) {
        this.total = total;
        this.assists = assists;
        this.saves = saves;
    }

    public int getTotal() { return total; }

    public int getAssists() { return assists; }

    public int getSaves() { return saves; }
}

