package com.example.football_all_in_one.model.season_response;

import java.util.ArrayList;

public class SeasonResponseList {
  private ArrayList<Season> seasons;

  public SeasonResponseList(ArrayList<Season> seasons) {
    this.seasons = seasons;
  }

  public ArrayList<Season> getSeasons() {
    return seasons;
  }
}
