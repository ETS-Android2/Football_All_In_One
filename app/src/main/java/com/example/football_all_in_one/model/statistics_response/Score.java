package com.example.football_all_in_one.model.statistics_response;

public class Score {
    private int home;
    private int away;
    private int total;

    public Score(int home, int away, int total) {
        this.home = home;
        this.away = away;
        this.total = total;
    }

    public int getHome() {
        return home;
    }

    public int getAway() {
        return away;
    }

    public int getTotal() {
        return total;
    }
}
