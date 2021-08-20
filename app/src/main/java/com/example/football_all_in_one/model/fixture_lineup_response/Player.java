package com.example.football_all_in_one.model.fixture_lineup_response;

import com.google.gson.annotations.SerializedName;

public class Player {
    @SerializedName("player")
    private PlayerInfo playerInfo;

    public Player(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }
}
