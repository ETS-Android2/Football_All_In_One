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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.football_all_in_one.model.players_response.Birth;
import com.example.football_all_in_one.model.players_response.Cards;
import com.example.football_all_in_one.model.players_response.Games;
import com.example.football_all_in_one.model.players_response.Goals;
import com.example.football_all_in_one.model.players_response.Penalty;
import com.example.football_all_in_one.model.players_response.Player;
import com.example.football_all_in_one.model.players_response.PlayersResponse;
import com.example.football_all_in_one.model.players_response.PlayersResponseList;
import com.example.football_all_in_one.model.players_response.PlayersStatistics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayersActivity extends AppCompatActivity {
  ConstraintLayout constraintLayout;
  ImageView football;
  View pageView, loadingView;
  List<PlayerListItem> playerListItems = new ArrayList<>();
  RecyclerView playerList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_players);

    initializeLoading();
    displayLoading();

    Intent intent = getIntent();
    int teamId = intent.getIntExtra(MainActivity.EXTRA + "teamId", 0);
    String teamName = intent.getStringExtra(MainActivity.EXTRA + "teamName");
    String teamLogo = intent.getStringExtra(MainActivity.EXTRA + "teamLogo");
    int leagueId = intent.getIntExtra(MainActivity.EXTRA + "leagueId", 0);
    int season = intent.getIntExtra(MainActivity.EXTRA + "season", 0);

    setLeagueImg(leagueId);
    setTeam(teamName, teamLogo);
    new GetPlayers(leagueId, teamId, season).execute();
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

  private class GetPlayers extends AsyncTask<Boolean, Boolean, Boolean> {
    int leagueId, teamId, season;

    public GetPlayers(int leagueId, int teamId, int season) {
      this.leagueId = leagueId;
      this.teamId = teamId;
      this.season = season;
    }

    @Override
    protected Boolean doInBackground(Boolean... booleans) {
      ApiFootball api = MainActivity.api;
      Call<PlayersResponse> call = api.getPlayersResponse(leagueId, season, teamId, 1);
      try {
        PlayersResponse playersResponse = call.execute().body();
        ArrayList<PlayersResponseList> playersResponseList = playersResponse.getPlayersResponseList();
        for (PlayersResponseList playersResponseItem : playersResponseList) {
          Player player = playersResponseItem.getPlayer();
          PlayersStatistics playersStatistics = playersResponseItem.getPlayersStatistics().get(0);
          Games games = playersStatistics.getGames();
          Goals goals = playersStatistics.getGoals();
          Cards cards = playersStatistics.getCards();
          Penalty penalty = playersStatistics.getPenalty();

          playerListItems.add(new PlayerListItem(player, games, goals, cards, penalty));
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

      call = api.getPlayersResponse(leagueId, season, teamId, 2);
      try {
        PlayersResponse playersResponse = call.execute().body();
        ArrayList<PlayersResponseList> playersResponseList = playersResponse.getPlayersResponseList();
        for (PlayersResponseList playersResponseItem : playersResponseList) {
          Player player = playersResponseItem.getPlayer();
          PlayersStatistics playersStatistics = playersResponseItem.getPlayersStatistics().get(0);
          Games games = playersStatistics.getGames();
          Goals goals = playersStatistics.getGoals();
          Cards cards = playersStatistics.getCards();
          Penalty penalty = playersStatistics.getPenalty();

          playerListItems.add(new PlayerListItem(player, games, goals, cards, penalty));
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

      return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
      hideLoading();
      playerList = findViewById(R.id.player_list);
      playerList.setLayoutManager(new LinearLayoutManager(PlayersActivity.this));
      playerList.setAdapter(new PlayerListAdapter(PlayersActivity.this, playerListItems));
    }
  }
}