package com.example.football_all_in_one.model.players_statistics_for_a_fixture_response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PlayerFixtureStatisticsResponseList {
    private ArrayList<Players> players;

    public PlayerFixtureStatisticsResponseList(ArrayList<Players> players) {
        this.players = players;
    }

    public ArrayList<Players> getPlayers() { return players; }
}
