package com.example.football_all_in_one.model.fixtures_response;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Teams {
    private Team home;
    private Team away;

    public Teams(Team home, Team away) {
        this.home = home;
        this.away = away;
    }

    public Team getHome() {
        return home;
    }

    public Team getAway() {
        return away;
    }
}
