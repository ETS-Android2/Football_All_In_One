package com.example.football_all_in_one;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.football_all_in_one.model.standing_response.League;
import com.example.football_all_in_one.model.standing_response.Standing;
import com.example.football_all_in_one.model.standing_response.StandingResponse;
import com.example.football_all_in_one.model.standing_response.StandingResponseList;
import com.example.football_all_in_one.model.standing_response.Team;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StandingsActivity extends AppCompatActivity {
  ConstraintLayout constraintLayout;
  ImageView football;
  View pageView, loadingView;
  List<StandingListItem> standingListItems = new ArrayList<>();
  RecyclerView standingList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_standings);

    initializeLoading();
    displayLoading();

    Intent intent = getIntent();
    int leagueId = intent.getIntExtra(MainActivity.EXTRA + "leagueId", 0);
    int season = intent.getIntExtra(MainActivity.EXTRA + "season", 0);

    setLeagueImg(leagueId);
    getStandings(leagueId, season);

//    standingListItems.add(new StandingListItem(1,1,"Athletico Madridddddddddddddddddddddddddd", "https://media.api-sports.io/football/teams/530.png", 32, "N/A"));
//    standingListItems.add(new StandingListItem(1,2,"Real Madrid", "https://media.api-sports.io/football/teams/541.png", 32, "N/A"));
//    standingListItems.add(new StandingListItem(1, 3,"Real Sociedad", "https://media.api-sports.io/football/teams/548.png", 26, "N/A"));
//    standingListItems.add(new StandingListItem(1,4,"Villarreal", "https://media.api-sports.io/football/teams/533.png", 26, "N/A"));
//    standingListItems.add(new StandingListItem(1,5,"Barcelona", "https://media.api-sports.io/football/teams/529.png", 24, "N/A"));
//    standingListItems.add(new StandingListItem(1,6,"Sevilla", "https://media.api-sports.io/football/teams/536.png", 23, "N/A"));
//    standingListItems.add(new StandingListItem(1,7,"Granada CF", "https://media.api-sports.io/football/teams/715.png", 21, "N/A"));
//    standingListItems.add(new StandingListItem(1,8,"Celta Vigo", "https://media.api-sports.io/football/teams/538.png", 20, "N/A"));
//    standingListItems.add(new StandingListItem(1,9,"Real Betis", "https://media.api-sports.io/football/teams/543.png", 19, "N/A"));
//    standingListItems.add(new StandingListItem(1,10,"Athletic Club", "https://media.api-sports.io/football/teams/531.png", 18, "N/A"));
//
//    standingList = findViewById(R.id.standing_list);
//    standingList.setLayoutManager(new LinearLayoutManager(this));
//    standingList.setAdapter(new StandingListAdapter(this, standingListItems, leagueId, season));
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

  private void getStandings(int leagueId, int season) {
    ApiFootball api = MainActivity.api;
    Call<StandingResponse> call = api.getStandingResponse(leagueId, season);
    call.enqueue(new Callback<StandingResponse>() {
      @Override
      public void onResponse(Call<StandingResponse> call, Response<StandingResponse> response) {
        if (!response.isSuccessful()) {
          Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
          return;
        }

        hideLoading();

        StandingResponse standingResponse = response.body();
        ArrayList<StandingResponseList> standingResponseList = standingResponse.getStandingResponseList();
        if (standingResponseList.isEmpty()) {
          Toast.makeText(getApplicationContext(), "No standings available", Toast.LENGTH_SHORT).show();
          return;
        }

        League league = standingResponseList.get(0).getLeague();
        ArrayList<ArrayList<Standing>> groups = league.getGroups();
        for (ArrayList<Standing> group : groups) {
          for (Standing standing : group) {
            Team team = standing.getTeam();
            String currentGroup = standing.getGroup();
            currentGroup = currentGroup.substring(currentGroup.length() - 7);
            standingListItems.add(new StandingListItem(team.getId(), standing.getRank(), team.getName(), team.getLogo(), standing.getPoints(), groups.size() == 1 ? "N/A" : currentGroup));
          }
        }

        standingList = findViewById(R.id.standing_list);
        standingList.setLayoutManager(new LinearLayoutManager(StandingsActivity.this));
        standingList.setAdapter(new StandingListAdapter(StandingsActivity.this, standingListItems, leagueId, season));
      }

      @Override
      public void onFailure(Call<StandingResponse> call, Throwable t) {
        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });
  }
}