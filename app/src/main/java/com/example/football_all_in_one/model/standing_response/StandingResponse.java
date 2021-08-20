package com.example.football_all_in_one.model.standing_response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StandingResponse {
    @SerializedName("response")
    ArrayList<StandingResponseList> standingResponseList;

    public StandingResponse(ArrayList<StandingResponseList> standingResponseList) {
        this.standingResponseList = standingResponseList;
    }

    public ArrayList<StandingResponseList> getStandingResponseList() {
        return standingResponseList;
    }
}
