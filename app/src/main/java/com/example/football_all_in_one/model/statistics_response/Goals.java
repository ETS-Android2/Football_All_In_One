package com.example.football_all_in_one.model.statistics_response;

import com.google.gson.annotations.SerializedName;

public class Goals {
    @SerializedName("for")
    private GoalsFor goalsFor;
    @SerializedName("against")
    private GoalsAgainst goalsAgainst;

    public Goals(GoalsFor goalsFor, GoalsAgainst goalsAgainst) {
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
    }

    public GoalsFor getGoalsFor() {
        return goalsFor;
    }

    public GoalsAgainst getGoalsAgainst() {
        return goalsAgainst;
    }
}
