package com.example.football_all_in_one.model.fixtures_response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FixturesResponse {
    @SerializedName("response")
    ArrayList<FixturesResponseList> fixturesResponseList;

    public FixturesResponse(ArrayList<FixturesResponseList> fixturesResponseList) {
        this.fixturesResponseList = fixturesResponseList;
    }

    public ArrayList<FixturesResponseList> getFixturesResponseList() {
        return fixturesResponseList;
    }
}
