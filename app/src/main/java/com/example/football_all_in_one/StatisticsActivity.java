package com.example.football_all_in_one;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.football_all_in_one.model.statistics_response.Fixture;
import com.example.football_all_in_one.model.statistics_response.Goals;
import com.example.football_all_in_one.model.statistics_response.GoalsAgainst;
import com.example.football_all_in_one.model.statistics_response.GoalsFor;
import com.example.football_all_in_one.model.statistics_response.Score;
import com.example.football_all_in_one.model.statistics_response.StatisticsResponse;
import com.example.football_all_in_one.model.statistics_response.StatisticsResponseObject;
import com.example.football_all_in_one.model.team_statistics_for_a_fixture_response.Statistics;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticsActivity extends AppCompatActivity {
  ConstraintLayout constraintLayout;
  ImageView football;
  View pageView, loadingView;

  TextView gamesPlayedHome, gamesPlayedAway, gamesPlayedTotal;
  TextView winsHome, winsAway, winsTotal;
  TextView drawsHome, drawsAway, drawsTotal;
  TextView lossHome, lossAway, lossTotal;
  TextView goalsForHome, goalsForAway, goalsForTotal;
  TextView goalsAgainstHome, goalsAgainstAway, goalsAgainstTotal;
  TextView avgGoalsForHome, avgGoalsForAway, avgGoalsForTotal;
  TextView avgGoalsAgainstHome, avgGoalsAgainstAway, avgGoalsAgainstTotal;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_statistics);

    initializeLoading();
    displayLoading();

    Intent intent = getIntent();
    int teamId = intent.getIntExtra(MainActivity.EXTRA + "teamId", 0);
    String teamName = intent.getStringExtra(MainActivity.EXTRA + "teamName");
    String teamLogo = intent.getStringExtra(MainActivity.EXTRA + "teamLogo");
    int leagueId = intent.getIntExtra(MainActivity.EXTRA + "leagueId", 0);
    int season = intent.getIntExtra(MainActivity.EXTRA + "season", 0);

    gamesPlayedHome = findViewById(R.id.games_played_home);
    gamesPlayedAway = findViewById(R.id.games_played_away);
    gamesPlayedTotal = findViewById(R.id.games_played_all);

    winsHome = findViewById(R.id.wins_home);
    winsAway = findViewById(R.id.wins_away);
    winsTotal = findViewById(R.id.wins_all);

    drawsHome = findViewById(R.id.draws_home);
    drawsAway = findViewById(R.id.draws_away);
    drawsTotal = findViewById(R.id.draws_all);

    lossHome = findViewById(R.id.loss_home);
    lossAway = findViewById(R.id.loss_away);
    lossTotal = findViewById(R.id.loss_all);

    goalsForHome = findViewById(R.id.goals_for_home);
    goalsForAway = findViewById(R.id.goals_for_away);
    goalsForTotal = findViewById(R.id.goals_for_all);

    goalsAgainstHome = findViewById(R.id.goals_against_home);
    goalsAgainstAway = findViewById(R.id.goals_against_away);
    goalsAgainstTotal = findViewById(R.id.goals_against_all);

    avgGoalsForHome = findViewById(R.id.avg_goals_for_home);
    avgGoalsForAway = findViewById(R.id.avg_goals_for_away);
    avgGoalsForTotal = findViewById(R.id.avg_goals_for_all);

    avgGoalsAgainstHome = findViewById(R.id.avg_goals_against_home);
    avgGoalsAgainstAway = findViewById(R.id.avg_goals_against_away);
    avgGoalsAgainstTotal = findViewById(R.id.avg_goals_against_all);

    setLeagueImg(leagueId);
    setTeam(teamName, teamLogo);
    getStatistics(teamId, leagueId, season);
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

  private void setTeam(String name, String logo) {
    ImageView teamLogo = findViewById(R.id.team_logo);
    TextView teamName = findViewById(R.id.team_name);
    teamName.setText(name);
    Glide.with(this).load(logo).into(teamLogo);
  }

  private void getStatistics(int teamId, int leagueId, int season) {
    ApiFootball api = MainActivity.api;
    Call<StatisticsResponse> call = api.getStatisticsResponse(leagueId, season, teamId);
    call.enqueue(new Callback<StatisticsResponse>() {
      @Override
      public void onResponse(Call<StatisticsResponse> call, Response<StatisticsResponse> response) {
        if (!response.isSuccessful()) {
          Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
          return;
        }

        hideLoading();

        StatisticsResponse statisticsResponse = response.body();
        StatisticsResponseObject statisticsResponseObject = statisticsResponse.getStatisticsResponseObject();
        Fixture fixture = statisticsResponseObject.getFixture();
        Goals goals = statisticsResponseObject.getGoals();

        gamesPlayedHome.setText(String.valueOf(fixture.getPlayed().getHome()));
        gamesPlayedAway.setText(String.valueOf(fixture.getPlayed().getAway()));
        gamesPlayedTotal.setText(String.valueOf(fixture.getPlayed().getTotal()));

        winsHome.setText(String.valueOf(fixture.getWins().getHome()));
        winsAway.setText(String.valueOf(fixture.getWins().getAway()));
        winsTotal.setText(String.valueOf(fixture.getWins().getTotal()));

        drawsHome.setText(String.valueOf(fixture.getDraws().getHome()));
        drawsAway.setText(String.valueOf(fixture.getDraws().getAway()));
        drawsTotal.setText(String.valueOf(fixture.getDraws().getTotal()));

        lossHome.setText(String.valueOf(fixture.getLoses().getHome()));
        lossAway.setText(String.valueOf(fixture.getLoses().getAway()));
        lossTotal.setText(String.valueOf(fixture.getLoses().getTotal()));

        goalsForHome.setText(String.valueOf(goals.getGoalsFor().getTotal().getHome()));
        goalsForAway.setText(String.valueOf(goals.getGoalsFor().getTotal().getAway()));
        goalsForTotal.setText(String.valueOf(goals.getGoalsFor().getTotal().getTotal()));

        goalsAgainstHome.setText(String.valueOf(goals.getGoalsAgainst().getTotal().getHome()));
        goalsAgainstAway.setText(String.valueOf(goals.getGoalsAgainst().getTotal().getAway()));
        goalsAgainstTotal.setText(String.valueOf(goals.getGoalsAgainst().getTotal().getHome()));

        avgGoalsForHome.setText(goals.getGoalsFor().getAverage().getHome());
        avgGoalsForAway.setText(goals.getGoalsFor().getAverage().getAway());
        avgGoalsForTotal.setText(goals.getGoalsFor().getAverage().getTotal());

        avgGoalsAgainstHome.setText(goals.getGoalsAgainst().getAverage().getHome());
        avgGoalsAgainstAway.setText(goals.getGoalsAgainst().getAverage().getAway());
        avgGoalsAgainstTotal.setText(goals.getGoalsAgainst().getAverage().getTotal());
      }

      @Override
      public void onFailure(Call<StatisticsResponse> call, Throwable t) {
        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });
  }
}