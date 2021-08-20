package com.example.football_all_in_one.model.team_statistics_for_a_fixture_response;

import java.util.ArrayList;

public class TeamFixtureStatisticsResponseList {
    private ArrayList<Statistics> statistics;

    public TeamFixtureStatisticsResponseList(ArrayList<Statistics> statistics) {
        this.statistics = statistics;
    }

    public ArrayList<Statistics> getStatistics() { return statistics; }
}
