package com.example.football_all_in_one;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.football_all_in_one.model.fixture_lineup_response.FixtureLineupResponse;
import com.example.football_all_in_one.model.fixture_lineup_response.FixtureLineupResponseList;
import com.example.football_all_in_one.model.fixture_lineup_response.Player;
import com.example.football_all_in_one.model.fixtures_response.Fixture;
import com.example.football_all_in_one.model.fixtures_response.FixturesResponse;
import com.example.football_all_in_one.model.fixtures_response.FixturesResponseList;
import com.example.football_all_in_one.model.fixtures_response.Goals;
import com.example.football_all_in_one.model.fixtures_response.Status;
import com.example.football_all_in_one.model.fixtures_response.Team;
import com.example.football_all_in_one.model.fixtures_response.Teams;
import com.example.football_all_in_one.model.fixtures_response.Venue;
import com.example.football_all_in_one.model.team_statistics_for_a_fixture_response.Statistics;
import com.example.football_all_in_one.model.team_statistics_for_a_fixture_response.TeamFixtureStatisticsResponse;
import com.example.football_all_in_one.model.team_statistics_for_a_fixture_response.TeamFixtureStatisticsResponseList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FixturesActivity extends AppCompatActivity {
  ConstraintLayout constraintLayout;
  ImageView football;
  View pageView, loadingView;
  ArrayList<FixturesResponseList> fixturesResponseList = new ArrayList<>();
  ArrayList<ArrayList<Statistics>> homeStats = new ArrayList<>(), awayStats = new ArrayList<>();
  ArrayList<FixtureLineupResponseList> homeLineup = new ArrayList<>(), awayLineup = new ArrayList<>();
  List<FixtureListItem> fixtureListItems = new ArrayList<>();
  RecyclerView fixtureList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fixtures);

    initializeLoading();
    displayLoading();

    Intent intent = getIntent();
    int teamId = intent.getIntExtra(MainActivity.EXTRA + "teamId", 0);
    int leagueId = intent.getIntExtra(MainActivity.EXTRA + "leagueId", 0);
    int season = intent.getIntExtra(MainActivity.EXTRA + "season", 0);

    setLeagueImg(leagueId);
    new GetFixtures(teamId, leagueId, season).execute();
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

  private class GetFixtures extends AsyncTask<Boolean, Boolean, Boolean> {
    int teamId, leagueId, season;

    public GetFixtures(int teamId, int leagueId, int season) {
      this.teamId = teamId;
      this.leagueId = leagueId;
      this.season = season;
    }

    @Override
    protected Boolean doInBackground(Boolean... booleans) {
      ApiFootball api = MainActivity.api;

      Call<FixturesResponse> call = api.getFixturesResponse(leagueId, season, teamId, 2, null);
      FixturesResponse fixturesResponse;
      try {
        fixturesResponse = call.execute().body();
        ArrayList<FixturesResponseList> fixturesResponseListLast = fixturesResponse.getFixturesResponseList();
        fixturesResponseList.addAll(fixturesResponseListLast);
      } catch (IOException e) {
        e.printStackTrace();
      }

      call = api.getFixturesResponse(leagueId, season, teamId, null, 2);
      try {
        fixturesResponse = call.execute().body();
        ArrayList<FixturesResponseList> fixturesResponseListNext = fixturesResponse.getFixturesResponseList();
        fixturesResponseList.addAll(fixturesResponseListNext);
      } catch (IOException e) {
        e.printStackTrace();
      }

      try {
        getStatistics();
      } catch (IOException e) {
        e.printStackTrace();
      }

      try {
        getLineups();
      } catch (IOException e) {
        e.printStackTrace();
      }

      for (int i = 0; i < fixturesResponseList.size(); i++) {
        Fixture fixture = fixturesResponseList.get(i).getFixture();
        Teams teams = fixturesResponseList.get(i).getTeams();
        Goals goals = fixturesResponseList.get(i).getGoals();

        String homeFormation = "";
        String awayFormation = "";
        ArrayList<Player> homeStartingXI = new ArrayList<>();
        ArrayList<Player> awayStartingXI = new ArrayList<>();
        ArrayList<Player> homeSubstitutes = new ArrayList<>();
        ArrayList<Player> awaySubstitutes = new ArrayList<>();

        if (homeLineup.get(i) != null && awayLineup.get(i) != null) {
          homeFormation = homeLineup.get(i).getFormation();
          awayFormation = awayLineup.get(i).getFormation();
          homeStartingXI = homeLineup.get(i).getStartXI();
          awayStartingXI = awayLineup.get(i).getStartXI();
          homeSubstitutes = homeLineup.get(i).getSubstitutes();
          awaySubstitutes = awayLineup.get(i).getSubstitutes();
        }

        fixtureListItems.add(new FixtureListItem(fixture, teams, goals, homeStats.get(i), awayStats.get(i), homeFormation, awayFormation, homeStartingXI, awayStartingXI, homeSubstitutes, awaySubstitutes));
      }

      return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
      hideLoading();
      RecyclerView fixtureList = findViewById(R.id.fixture_list);
      fixtureList.setLayoutManager(new LinearLayoutManager(FixturesActivity.this));
      fixtureList.setAdapter(new FixtureListAdapter(FixturesActivity.this, fixtureListItems, leagueId, season));
    }
  }

  private void getStatistics() throws IOException {
    ApiFootball api = MainActivity.api;
    for (FixturesResponseList fixturesResponseItem : fixturesResponseList) {
      int fixtureId = fixturesResponseItem.getFixture().getId();
      Call<TeamFixtureStatisticsResponse> call = api.getTeamFixtureStatisticsResponse(fixtureId);
      TeamFixtureStatisticsResponse teamFixtureStatisticsResponse = call.execute().body();
      ArrayList<TeamFixtureStatisticsResponseList> teamFixtureStatisticsResponseList = teamFixtureStatisticsResponse.getTeamFixtureStatisticsResponseList();

      if (teamFixtureStatisticsResponseList.isEmpty()) {
        homeStats.add(null);
        awayStats.add(null);
      } else {
        homeStats.add(teamFixtureStatisticsResponseList.get(0).getStatistics());
        awayStats.add(teamFixtureStatisticsResponseList.get(1).getStatistics());
      }
    }
  }

  private void getLineups() throws IOException {
    ApiFootball api = MainActivity.api;
    for (FixturesResponseList fixturesResponseItem : fixturesResponseList) {
      int fixtureId = fixturesResponseItem.getFixture().getId();
      Call<FixtureLineupResponse> call = api.getFixtureLineupResponse(fixtureId);
      FixtureLineupResponse fixtureLineupResponse = call.execute().body();
      ArrayList<FixtureLineupResponseList> fixtureLineupResponseList = fixtureLineupResponse.getFixtureLineupResponseList();

      if (fixtureLineupResponseList.isEmpty()) {
        homeLineup.add(null);
        awayLineup.add(null);
      } else {
        homeLineup.add(fixtureLineupResponseList.get(0));
        awayLineup.add(fixtureLineupResponseList.get(1));
      }
    }
  }
}