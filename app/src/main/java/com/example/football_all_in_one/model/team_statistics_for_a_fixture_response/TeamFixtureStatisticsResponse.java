package com.example.football_all_in_one.model.team_statistics_for_a_fixture_response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TeamFixtureStatisticsResponse {
    @SerializedName("response")
    ArrayList<TeamFixtureStatisticsResponseList> teamFixtureStatisticsResponseList;

    public TeamFixtureStatisticsResponse(ArrayList<TeamFixtureStatisticsResponseList> teamFixtureStatisticsResponseList) {
        this.teamFixtureStatisticsResponseList = teamFixtureStatisticsResponseList;
    }

    public ArrayList<TeamFixtureStatisticsResponseList> getTeamFixtureStatisticsResponseList() {
        return teamFixtureStatisticsResponseList;
    }
}
