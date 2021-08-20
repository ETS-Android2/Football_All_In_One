package com.example.football_all_in_one;

public class StandingListItem {
  private int teamId;
  private int rank;
  private String teamName;
  private String teamLogo;
  private int points;
  private String group;

  public StandingListItem(int teamId, int rank, String teamName, String teamLogo, int points, String group) {
    this.teamId = teamId;
    this.rank = rank;
    this.teamName = teamName;
    this.teamLogo = teamLogo;
    this.points = points;
    this.group = group;
  }

  public int getTeamId() {
    return teamId;
  }

  public int getRank() {
    return rank;
  }

  public String getTeamName() {
    return teamName;
  }

  public String getTeamLogo() {
    return teamLogo;
  }

  public int getPoints() {
    return points;
  }

  public String getGroup() {
    return group;
  }
}
