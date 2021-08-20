package com.example.football_all_in_one.model.players_response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PlayersResponseList {
    private Player player;
    @SerializedName("statistics")
    private ArrayList<PlayersStatistics> playersStatistics;

    public PlayersResponseList(Player player, ArrayList<PlayersStatistics> playersStatistics) {
        this.player = player;
        this.playersStatistics = playersStatistics;
    }

    public Player getPlayer() { return player; }

    public ArrayList<PlayersStatistics> getPlayersStatistics() { return playersStatistics; }
}
