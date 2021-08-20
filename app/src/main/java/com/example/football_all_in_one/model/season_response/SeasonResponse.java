package com.example.football_all_in_one.model.season_response;

import com.example.football_all_in_one.model.season_response.SeasonResponseList;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SeasonResponse {
  @SerializedName("response")
  private ArrayList<SeasonResponseList> seasonResponseList;

  public SeasonResponse(ArrayList<SeasonResponseList> seasonResponseList) {
    this.seasonResponseList = seasonResponseList;
  }

  public ArrayList<SeasonResponseList> getSeasonResponseList() {
    return seasonResponseList;
  }
}
