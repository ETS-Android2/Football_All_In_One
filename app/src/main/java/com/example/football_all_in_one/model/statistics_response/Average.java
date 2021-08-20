package com.example.football_all_in_one.model.statistics_response;

public class Average {
    private String home;
    private String away;
    private String total;

    public Average(String home, String away, String total) {
        this.home = home;
        this.away = away;
        this.total = total;
    }

    public String getHome() {
        return home;
    }

    public String getAway() {
        return away;
    }

    public String getTotal() {
        return total;
    }
}
