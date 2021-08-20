package com.example.football_all_in_one.model.statistics_response;

import com.google.gson.annotations.SerializedName;

public class StatisticsResponseObject {
    @SerializedName("fixtures")
    private Fixture fixture;
    private Goals goals;

    public StatisticsResponseObject(Fixture fixture, Goals goals) {
        this.fixture = fixture;
        this.goals = goals;
    }

    public Fixture getFixture() {
        return fixture;
    }

    public Goals getGoals() {
        return goals;
    }
}
