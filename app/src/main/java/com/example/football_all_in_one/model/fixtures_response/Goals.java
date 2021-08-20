package com.example.football_all_in_one.model.fixtures_response;

public class Goals {
    private int home;
    private int away;

    public Goals(int home, int away) {
        this.home = home;
        this.away = away;
    }

    public int getHome() { return home; }

    public int getAway() { return away; }
}
