package com.example.football_all_in_one.model.players_response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PlayersResponse {
    @SerializedName("response")
    ArrayList<PlayersResponseList> playersResponseList;

    public PlayersResponse(ArrayList<PlayersResponseList> playersResponseList) {
        this.playersResponseList = playersResponseList;
    }

    public ArrayList<PlayersResponseList> getPlayersResponseList() {
        return playersResponseList;
    }
}

