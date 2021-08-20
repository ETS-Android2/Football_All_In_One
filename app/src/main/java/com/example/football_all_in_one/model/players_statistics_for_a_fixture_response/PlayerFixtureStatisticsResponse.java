package com.example.football_all_in_one.model.players_statistics_for_a_fixture_response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PlayerFixtureStatisticsResponse {
    @SerializedName("response")
    ArrayList<PlayerFixtureStatisticsResponseList> playerFixtureStatisticsResponseList;

    public PlayerFixtureStatisticsResponse(ArrayList<PlayerFixtureStatisticsResponseList> playerFixtureStatisticsResponseList) {
        this.playerFixtureStatisticsResponseList = playerFixtureStatisticsResponseList;
    }

    public ArrayList<PlayerFixtureStatisticsResponseList> getPlayerFixtureStatisticsResponseList() {
        return playerFixtureStatisticsResponseList;
    }
}
