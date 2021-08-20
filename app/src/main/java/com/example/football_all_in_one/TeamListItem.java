package com.example.football_all_in_one;

import com.example.football_all_in_one.model.teams_response.Team;
import com.example.football_all_in_one.model.teams_response.Venue;

public class TeamListItem {
  private Team team;
  private Venue venue;

  public TeamListItem(Team team, Venue venue) {
    this.team = team;
    this.venue = venue;
  }

  public Team getTeam() {
    return team;
  }

  public Venue getVenue() {
    return venue;
  }
}
