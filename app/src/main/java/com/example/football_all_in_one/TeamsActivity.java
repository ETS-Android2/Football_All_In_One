package com.example.football_all_in_one;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.AndroidException;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.football_all_in_one.model.teams_response.Team;
import com.example.football_all_in_one.model.teams_response.TeamsResponse;
import com.example.football_all_in_one.model.teams_response.TeamsResponseList;
import com.example.football_all_in_one.model.teams_response.Venue;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamsActivity extends AppCompatActivity {
  ConstraintLayout constraintLayout;
  ImageView football;
  View pageView, loadingView;
  List<TeamListItem> teamListItems = new ArrayList<>();
  RecyclerView teamList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_teams);

    initializeLoading();
    displayLoading();

    Intent intent = getIntent();
    int leagueId = intent.getIntExtra(MainActivity.EXTRA + "leagueId", 0);
    int season = intent.getIntExtra(MainActivity.EXTRA + "season", 0);

    setLeagueImg(leagueId);
    getTeams(leagueId, season);

//    Team team = new Team(1,"Manchester United", "England", 1878, false, "https://media.api-sports.io/football/teams/33.png");
//    Venue venue = new Venue(1, "Old Trafford", "Sir Matt Busby Way", "Manchester", 76212);
//
//    teamListItems.add(new TeamListItem(team, venue));
//    teamListItems.add(new TeamListItem(team, venue));
//    teamListItems.add(new TeamListItem(team, venue));
//    teamListItems.add(new TeamListItem(team, venue));
//    teamListItems.add(new TeamListItem(team, venue));
//    teamListItems.add(new TeamListItem(team, venue));
//    teamListItems.add(new TeamListItem(team, venue));
//    teamListItems.add(new TeamListItem(team, venue));
//    teamListItems.add(new TeamListItem(team, venue));
//    teamListItems.add(new TeamListItem(team, venue));
//
//    teamList = findViewById(R.id.team_list);
//    teamList.setLayoutManager(new LinearLayoutManager(this));
//    teamList.setAdapter(new TeamListAdapter(this, teamListItems, leagueId, season));
  }

  private void initializeLoading() {
    constraintLayout = findViewById(R.id.layout);
    football = findViewById(R.id.football);
    pageView = findViewById(R.id.page_view);
    loadingView = findViewById(R.id.loading_view);
    Glide.with(this).load(R.drawable.hexagonal_football).into(football);
  }

  private void displayLoading() {
    pageView.setVisibility(View.GONE);
    loadingView.setVisibility(View.VISIBLE);
    constraintLayout.setBackgroundResource(R.drawable.league_select_bg);
  }

  private void hideLoading() {
    pageView.setVisibility(View.VISIBLE);
    loadingView.setVisibility(View.GONE);
    constraintLayout.setBackgroundResource(R.drawable.highlight_bg);
  }

  private void setLeagueImg(int leagueId) {
    IdMap idMap = new IdMap();
    ImageView leagueHighlight = findViewById(R.id.league_highlight);
    leagueHighlight.setImageResource(idMap.getImgOfLeague(leagueId));
  }

  private void getTeams(int leagueId, int season) {
    ApiFootball api = MainActivity.api;
    Call<TeamsResponse> call = api.getTeamsResponse(leagueId, season);
    call.enqueue(new Callback<TeamsResponse>() {
      @Override
      public void onResponse(Call<TeamsResponse> call, Response<TeamsResponse> response) {
        if (!response.isSuccessful()) {
          Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
          return;
        }

        hideLoading();

        TeamsResponse teamsResponse = response.body();
        ArrayList<TeamsResponseList> teamsResponseList = teamsResponse.getTeamsResponseList();
        for (TeamsResponseList teamsResponseItem : teamsResponseList) {
          Team team = teamsResponseItem.getTeam();
          Venue venue = teamsResponseItem.getVenue();
          teamListItems.add(new TeamListItem(team, venue));
        }

        teamList = findViewById(R.id.team_list);
        teamList.setLayoutManager(new LinearLayoutManager(TeamsActivity.this));
        teamList.setAdapter(new TeamListAdapter(TeamsActivity.this, teamListItems, leagueId, season));
      }

      @Override
      public void onFailure(Call<TeamsResponse> call, Throwable t) {
        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });
  }
}