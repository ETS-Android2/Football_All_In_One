package com.example.football_all_in_one.model.statistics_response;

public class GoalsAgainst {
    private Score total;
    private Average average;

    public GoalsAgainst(Score total, Average average) {
        this.total = total;
        this.average = average;
    }

    public Score getTotal() {
        return total;
    }

    public Average getAverage() {
        return average;
    }
}
