package com.example.football_all_in_one.model.players_statistics_for_a_fixture_response;

import java.util.ArrayList;

public class Players {
    private Player player;
    private ArrayList<Statistics> statistics;

    public Players(Player player, ArrayList<Statistics> statistics) {
        this.player = player;
        this.statistics = statistics;
    }

    public Player getPlayer() { return player; }

    public ArrayList<Statistics> getStatistics() { return statistics; }
}
