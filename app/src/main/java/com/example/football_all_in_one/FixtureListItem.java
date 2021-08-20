package com.example.football_all_in_one;

import com.example.football_all_in_one.model.fixture_lineup_response.Player;
import com.example.football_all_in_one.model.fixtures_response.Fixture;
import com.example.football_all_in_one.model.fixtures_response.Goals;
import com.example.football_all_in_one.model.fixtures_response.Teams;
import com.example.football_all_in_one.model.team_statistics_for_a_fixture_response.Statistics;

import java.util.ArrayList;

public class FixtureListItem {
  Fixture fixture;
  Teams teams;
  Goals goals;
  ArrayList<Statistics> homeStats, awayStats;
  String homeFormation, awayFormation;
  ArrayList<Player> homeStartingXI, awayStartingXI;
  ArrayList<Player> homeSubstitutes, awaySubstitutes;
  boolean statsVisible = true;

  public FixtureListItem(Fixture fixture, Teams teams, Goals goals, ArrayList<Statistics> homeStats, ArrayList<Statistics> awayStats, String homeFormation, String awayFormation, ArrayList<Player> homeStartingXI, ArrayList<Player> awayStartingXI, ArrayList<Player> homeSubstitutes, ArrayList<Player> awaySubstitutes) {
    this.fixture = fixture;
    this.teams = teams;
    this.goals = goals;
    this.homeStats = homeStats;
    this.awayStats = awayStats;
    this.homeFormation = homeFormation;
    this.awayFormation = awayFormation;
    this.homeStartingXI = homeStartingXI;
    this.awayStartingXI = awayStartingXI;
    this.homeSubstitutes = homeSubstitutes;
    this.awaySubstitutes = awaySubstitutes;
  }

  public Fixture getFixture() {
    return fixture;
  }

  public Teams getTeams() {
    return teams;
  }

  public Goals getGoals() {
    return goals;
  }

  public ArrayList<Statistics> getHomeStats() {
    return homeStats;
  }

  public ArrayList<Statistics> getAwayStats() {
    return awayStats;
  }

  public boolean isStatsVisible() {
    return statsVisible;
  }

  public String getHomeFormation() {
    return homeFormation;
  }

  public String getAwayFormation() {
    return awayFormation;
  }

  public ArrayList<Player> getHomeStartingXI() {
    return homeStartingXI;
  }

  public ArrayList<Player> getAwayStartingXI() {
    return awayStartingXI;
  }

  public ArrayList<Player> getHomeSubstitutes() {
    return homeSubstitutes;
  }

  public ArrayList<Player> getAwaySubstitutes() {
    return awaySubstitutes;
  }

  public void setStatsVisible(boolean statsVisible) {
    this.statsVisible = statsVisible;
  }
}
