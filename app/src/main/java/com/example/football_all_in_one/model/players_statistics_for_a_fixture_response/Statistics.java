package com.example.football_all_in_one.model.players_statistics_for_a_fixture_response;

public class Statistics {
    private Games games;
    private int offsides;
    private Shots shots;
    private Goals goals;
    private Passes passes;
    private Tackles tackles;
    private Fouls fouls;
    private Cards cards;
    private Penalty penalty;

    public Statistics(Games games, int offsides, Shots shots, Goals goals, Passes passes, Tackles tackles, Fouls fouls, Cards cards, Penalty penalty) {
        this.games = games;
        this.offsides = offsides;
        this.shots = shots;
        this.goals = goals;
        this.passes = passes;
        this.tackles = tackles;
        this.fouls = fouls;
        this.cards = cards;
        this.penalty = penalty;
    }

    public Games getGames() { return games; }

    public int getOffsides() { return offsides; }

    public Shots getShots() { return shots; }

    public Goals getGoals() { return goals; }

    public Passes getPasses() { return passes; }

    public Tackles getTackles() { return tackles; }

    public Fouls getFouls() { return fouls; }

    public Cards getCards() { return cards; }

    public Penalty getPenalty() { return penalty; }
}
