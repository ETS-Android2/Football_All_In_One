package com.example.football_all_in_one;

import android.app.Application;

public class IdMap extends Application {
  private final int leagueNumber = 11;
  private final int[] leagueId = new int[leagueNumber];
  private final int[] season = new int[leagueNumber];
  private final int[] leagueImg = new int[leagueNumber];
  private final String[] leagueName = new String[leagueNumber];

  public IdMap() {
    initializeLeagueName();
    initializeLeagueId();
    initializeLeagueImg();
    initializeSeasonId();
  }

  private void initializeLeagueName() {
    leagueName[0] = "champions_league";
    leagueName[1] = "la_liga";
    leagueName[2] = "premier_league";
    leagueName[3] = "fa_cup";
    leagueName[4] = "europa_league";
    leagueName[5] = "serie_a";
    leagueName[6] = "ligue1";
    leagueName[7] = "bundesliga";
    leagueName[8] = "copa_del_rey";
    leagueName[9] = "copa_america";
    leagueName[10] = "world_cup";
  }

  private void initializeLeagueId() {
    leagueId[0] = 2;
    leagueId[1] = 140;
    leagueId[2] = 39;
    leagueId[3] = 45;
    leagueId[4] = 3;
    leagueId[5] = 71;
    leagueId[6] = 61;
    leagueId[7] = 78;
    leagueId[8] = 143;
    leagueId[9] = 9;
    leagueId[10] = 1;
  }

  private void initializeSeasonId() {
    season[0] = 2020;
    season[1] = 2020;
    season[2] = 2020;
    season[3] = 2020;
    season[4] = 2020;
    season[5] = 2020;
    season[6] = 2020;
    season[7] = 2020;
    season[8] = 2020;
    season[9] = 2020;
    season[10] = 2018;
  }

  private void initializeLeagueImg() {
    leagueImg[0] = R.drawable.uefa;
    leagueImg[1] = R.drawable.la_liga;
    leagueImg[2] = R.drawable.premier_league;
    leagueImg[3] = R.drawable.fa_cup;
    leagueImg[4] = R.drawable.europa;
    leagueImg[5] = R.drawable.serie_a;
    leagueImg[6] = R.drawable.ligue_1;
    leagueImg[7] = R.drawable.bundesliga;
    leagueImg[8] = R.drawable.copa_del_rey;
    leagueImg[9] = R.drawable.copa_america;
    leagueImg[10] = R.drawable.world_cup;
  }

  public int getLeagueNumber() {
    return leagueNumber;
  }

  public String getLeagueName(int idx) {
    return leagueName[idx];
  }

  public int getLeagueId(int idx) {
    return leagueId[idx];
  }

  public int getIdxOfLeague(int id) {
    int ret = -1;
    for (int i = 0; i < leagueNumber; i++) {
      if (leagueId[i] == id) {
        ret = i;
        break;
      }
    }
    return ret;
  }

  public int getImgOfLeague(int id) {
    int ret = -1;
    for (int i = 0; i < leagueNumber; i++) {
      if (leagueId[i] == id) {
        ret = leagueImg[i];
        break;
      }
    }
    return ret;
  }

  public int getSeason(int idx) {
    return season[idx];
  }

  public void setSeason(int idx, int current_season) {
    season[idx] = current_season;
  }
}
