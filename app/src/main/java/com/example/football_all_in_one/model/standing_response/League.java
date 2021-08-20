package com.example.football_all_in_one.model.standing_response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class League {
    @SerializedName("standings")
    private ArrayList<ArrayList<Standing>> groups;

    public League(ArrayList<ArrayList<Standing>> groups) {
        this.groups = groups;
    }

    public ArrayList<ArrayList<Standing>> getGroups() {
        return groups;
    }
}
