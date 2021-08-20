package com.example.football_all_in_one;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.football_all_in_one.model.season_response.Season;
import com.example.football_all_in_one.model.season_response.SeasonResponse;
import com.example.football_all_in_one.model.season_response.SeasonResponseList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
  public static ApiFootball api;
  private static final String BASE_URL = "https://v3.football.api-sports.io/";

  public static final String EXTRA = "com.example.football_all_in_one_";

  IdMap idMap = new IdMap();

  private final int leagueNumber = idMap.getLeagueNumber();
  Button[] leagueButton = new Button[leagueNumber];
  Button[] teamsButton = new Button[leagueNumber];
  Button[] standingsButton = new Button[leagueNumber];
  View[] group = new View[leagueNumber];

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initialize();
    hideAllGroups(-1);
    createOnclickListeners();
  }

  private void initialize() {
    initializeApi();

    for (int i = 0; i < leagueNumber; i++) {
      String idString = idMap.getLeagueName(i) + "_btn";
      int idInt = getResources().getIdentifier(idString, "id", getPackageName());
      leagueButton[i] = findViewById(idInt);

      idString = idMap.getLeagueName(i) + "_teams_btn";
      idInt = getResources().getIdentifier(idString, "id", getPackageName());
      teamsButton[i] = findViewById(idInt);

      idString = idMap.getLeagueName(i) + "_standings_btn";
      idInt = getResources().getIdentifier(idString, "id", getPackageName());
      standingsButton[i] = findViewById(idInt);

      idString = idMap.getLeagueName(i) + "_group";
      idInt = getResources().getIdentifier(idString, "id", getPackageName());
      group[i] = findViewById(idInt);

//      didn't use in order to save api calls
//      getSeason(i);
    }
  }

  private void initializeApi() {
    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build();

    api = retrofit.create(ApiFootball.class);
  }

  private void getSeason(int idx) {
    Call<SeasonResponse> call = api.getSeasonResponse(idMap.getLeagueId(idx),true);
    call.enqueue(new Callback<SeasonResponse>() {
      @Override
      public void onResponse(Call<SeasonResponse> call, Response<SeasonResponse> response) {
        if (!response.isSuccessful()) {
          Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
          return;
        }

        SeasonResponse seasonResponse = response.body();
        ArrayList<SeasonResponseList> seasonResponseList = seasonResponse.getSeasonResponseList();
        ArrayList<Season> seasons = seasonResponseList.get(0).getSeasons();
        int currentSeason = seasons.get(0).getYear();
        idMap.setSeason(idx, currentSeason);
      }

      @Override
      public void onFailure(Call<SeasonResponse> call, Throwable t) {
        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void visitTeamsPage(int idx) {
    Intent intent = new Intent(this, TeamsActivity.class);
    intent.putExtra(EXTRA + "leagueId", idMap.getLeagueId(idx));
    intent.putExtra(EXTRA + "season", idMap.getSeason(idx));
    startActivity(intent);
  }

  private void visitStandingsPage(int idx) {
    Intent intent = new Intent(this, StandingsActivity.class);
    intent.putExtra(EXTRA + "leagueId", idMap.getLeagueId(idx));
    intent.putExtra(EXTRA + "season", idMap.getSeason(idx));
    startActivity(intent);
  }

  private void createOnclickListeners() {
    for (int i = 0; i < leagueNumber; i++) {
      final int buttonId = i;

      leagueButton[i].setOnClickListener(v -> {
        hideAllGroups(buttonId);
        if (group[buttonId].getVisibility() == View.VISIBLE) {
          group[buttonId].setVisibility(View.GONE);
        } else {
          group[buttonId].setVisibility(View.VISIBLE);
        }
      });

      teamsButton[i].setOnClickListener(v -> visitTeamsPage(buttonId));
      standingsButton[i].setOnClickListener(v -> visitStandingsPage(buttonId));
    }
  }

  private void hideAllGroups(int except) {
    for (int i = 0; i < leagueNumber; i++) {
      if (i == except) continue;
      group[i].setVisibility(View.GONE);
    }
  }
}