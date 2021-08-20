package com.example.football_all_in_one.model.fixtures_response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FixturesResponseList {
    private Fixture fixture;
    private Teams teams;
    private Goals goals;

    public FixturesResponseList(Fixture fixture, Teams teams, Goals goals) {
        this.fixture = fixture;
        this.teams = teams;
        this.goals = goals;
    }

    public Fixture getFixture() { return fixture; }

    public Teams getTeams() { return teams; }

    public Goals getGoals() { return goals; }
}
