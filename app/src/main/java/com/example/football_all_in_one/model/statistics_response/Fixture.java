package com.example.football_all_in_one.model.statistics_response;

public class Fixture {
    private Score played;
    private Score wins;
    private Score draws;
    private Score loses;

    public Fixture(Score played, Score wins, Score draws, Score loses) {
        this.played = played;
        this.wins = wins;
        this.draws = draws;
        this.loses = loses;
    }

    public Score getPlayed() {
        return played;
    }

    public Score getWins() {
        return wins;
    }

    public Score getDraws() {
        return draws;
    }

    public Score getLoses() {
        return loses;
    }
}
