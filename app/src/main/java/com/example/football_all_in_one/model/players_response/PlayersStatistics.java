package com.example.football_all_in_one.model.players_response;


import java.util.ArrayList;

public class PlayersStatistics {
    private Games games;
    private Goals goals;
    private Penalty penalty;
    private Cards cards;

    public PlayersStatistics(Games games, Goals goals, Penalty penalty, Cards cards) {
        this.games = games;
        this.goals = goals;
        this.penalty = penalty;
        this.cards = cards;
    }

    public Games getGames() {
        return games;
    }

    public Goals getGoals() {
        return goals;
    }

    public Penalty getPenalty() {
        return penalty;
    }

    public Cards getCards() {
        return cards;
    }
}
