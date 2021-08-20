package com.example.football_all_in_one.model.teams_response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TeamsResponse {
    @SerializedName("response")
    ArrayList<TeamsResponseList> teamsResponseList;

    public TeamsResponse(ArrayList<TeamsResponseList> teamsResponseList) { this.teamsResponseList = teamsResponseList; }

    public ArrayList<TeamsResponseList> getTeamsResponseList() { return teamsResponseList; }
}
