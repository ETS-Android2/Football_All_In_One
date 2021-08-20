package com.example.football_all_in_one.model.fixture_lineup_response;

import java.util.ArrayList;

public class FixtureLineupResponseList {
    private String formation;
    private ArrayList<Player> startXI;
    private ArrayList<Player> substitutes;

    public FixtureLineupResponseList(String formation, ArrayList<Player> startXI, ArrayList<Player> substitutes) {
        this.formation = formation;
        this.startXI = startXI;
        this.substitutes = substitutes;
    }

    public String getFormation() { return formation; }

    public ArrayList<Player> getStartXI() { return startXI; }

    public ArrayList<Player> getSubstitutes() { return substitutes; }
}
