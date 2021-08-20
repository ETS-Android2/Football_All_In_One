package com.example.football_all_in_one.model.fixture_lineup_response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FixtureLineupResponse {
    @SerializedName("response")
    ArrayList<FixtureLineupResponseList> fixtureLineupResponseList;

    public FixtureLineupResponse(ArrayList<FixtureLineupResponseList> fixtureLineupResponseList) {
        this.fixtureLineupResponseList = fixtureLineupResponseList;
    }

    public ArrayList<FixtureLineupResponseList> getFixtureLineupResponseList() {
        return fixtureLineupResponseList;
    }
}
