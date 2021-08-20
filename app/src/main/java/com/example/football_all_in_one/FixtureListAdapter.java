package com.example.football_all_in_one;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.football_all_in_one.model.fixture_lineup_response.Player;
import com.example.football_all_in_one.model.fixtures_response.Fixture;
import com.example.football_all_in_one.model.fixtures_response.Goals;
import com.example.football_all_in_one.model.fixtures_response.Status;
import com.example.football_all_in_one.model.fixtures_response.Teams;
import com.example.football_all_in_one.model.fixtures_response.Venue;
import com.example.football_all_in_one.model.team_statistics_for_a_fixture_response.Statistics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class FixtureListAdapter extends RecyclerView.Adapter<FixtureListAdapter.FixtureListViewHolder> {
  private Context context;
  private List<FixtureListItem> fixtureListItems;
  private int leagueId, season;

  public FixtureListAdapter(Context context, List<FixtureListItem> fixtureListItems, int leagueId, int season) {
    this.context = context;
    this.fixtureListItems = fixtureListItems;
    this.leagueId = leagueId;
    this.season = season;
  }

  @NonNull
  @Override
  public FixtureListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.fixture_list_item, parent, false);
    return new FixtureListViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull FixtureListViewHolder holder, int position) {
    FixtureListItem fixtureListItem = fixtureListItems.get(position);
    Fixture fixture = fixtureListItem.getFixture();
    Venue venue = fixture.getVenue();
    Status status = fixture.getStatus();
    Teams teams = fixtureListItem.getTeams();
    Goals goals = fixtureListItem.getGoals();

    ArrayList<Statistics> homeStats = fixtureListItem.getHomeStats();
    ArrayList<Statistics> awayStats = fixtureListItem.getAwayStats();

    String homeFormation = fixtureListItem.getHomeFormation();
    String awayFormation = fixtureListItem.getAwayFormation();

    ArrayList<Player> homeStartingXI = fixtureListItem.getHomeStartingXI();
    ArrayList<Player> awayStartingXI = fixtureListItem.getAwayStartingXI();

    ArrayList<Player> homeSubstitutes = fixtureListItem.getHomeSubstitutes();
    ArrayList<Player> awaySubstitutes = fixtureListItem.getAwaySubstitutes();

    holder.venueName.setText(venue.getName());
    holder.venueCity.setText(venue.getCity());
    holder.homeScore.setText(String.valueOf(goals.getHome()));
    holder.awayScore.setText(String.valueOf(goals.getAway()));
    holder.elapsedTime.setText(String.valueOf(status.getElapsed()) + "'");
    holder.dateTime.setText(holder.getDateTime(fixture.getTimestamp()));
    holder.status.setText(status.getLongstatus());
    holder.homeTeamName.setText(teams.getHome().getName());
    holder.awayTeamName.setText(teams.getAway().getName());
    Glide.with(context).load(teams.getHome().getLogo()).into(holder.homeTeamLogo);
    Glide.with(context).load(teams.getAway().getLogo()).into(holder.awayTeamLogo);

    if (homeStats != null) {
      for (int i = 0; i < homeStats.size(); i++) {
        holder.stats[i].setText(homeStats.get(i).getType());

        int homeStatValue, awayStatValue;

        if (homeStats.get(i).getValue() == null) homeStatValue = 0;
        else homeStatValue = holder.convertStatValueToInt(homeStats.get(i).getValue().toString());

        if (awayStats.get(i).getValue() == null) awayStatValue = 0;
        else awayStatValue = holder.convertStatValueToInt(awayStats.get(i).getValue().toString());

        holder.statsValueHome[i].setText(String.valueOf(homeStatValue));
        holder.statsValueAway[i].setText(String.valueOf(awayStatValue));

        holder.statsPercentageHome[i].setProgress(holder.getHomePercentage(homeStatValue, awayStatValue));
        holder.statsPercentageAway[i].setProgress(holder.getAwayPercentage(homeStatValue, awayStatValue));
      }
    } else {
      for (int i = 0; i < 16; i++) {
        holder.stats[i].setVisibility(View.INVISIBLE);
        holder.statsValueHome[i].setVisibility(View.INVISIBLE);
        holder.statsPercentageHome[i].setVisibility(View.INVISIBLE);
        holder.statsValueAway[i].setVisibility(View.INVISIBLE);
        holder.statsPercentageAway[i].setVisibility(View.INVISIBLE);
      }
    }

    holder.homeFormation.setText("Formation: " + homeFormation);
    holder.awayFormation.setText("Formation: " + awayFormation);

    for (int i = 0; i < 11; i++) {
      if (homeStartingXI.isEmpty()) break;

      holder.homeStartingNumber[i].setText(String.valueOf(homeStartingXI.get(i).getPlayerInfo().getNumber()));
      holder.homeStartingName[i].setText(homeStartingXI.get(i).getPlayerInfo().getName());
      holder.homeStartingPosition[i].setText(homeStartingXI.get(i).getPlayerInfo().getPos());

      holder.awayStartingNumber[i].setText(String.valueOf(awayStartingXI.get(i).getPlayerInfo().getNumber()));
      holder.awayStartingName[i].setText(awayStartingXI.get(i).getPlayerInfo().getName());
      holder.awayStartingPosition[i].setText(awayStartingXI.get(i).getPlayerInfo().getPos());
    }

    for (int i = 0; i < 7; i++) {
      if (homeSubstitutes.isEmpty()) break;

      holder.homeSubstitutesNumber[i].setText(String.valueOf(homeSubstitutes.get(i).getPlayerInfo().getNumber()));
      holder.homeSubstitutesName[i].setText(homeSubstitutes.get(i).getPlayerInfo().getName());
      holder.homeSubstitutesPosition[i].setText(homeSubstitutes.get(i).getPlayerInfo().getPos());

      holder.awaySubstitutesNumber[i].setText(String.valueOf(awaySubstitutes.get(i).getPlayerInfo().getNumber()));
      holder.awaySubstitutesName[i].setText(awaySubstitutes.get(i).getPlayerInfo().getName());
      holder.awaySubstitutesPosition[i].setText(awaySubstitutes.get(i).getPlayerInfo().getPos());
    }

    holder.itemView.setOnClickListener(v -> {
      if (holder.buttonsGroup.getVisibility() == View.VISIBLE) {
        holder.buttonsGroup.setVisibility(View.GONE);
        holder.statsLayout.setVisibility(View.GONE);
        holder.lineupsGroup.setVisibility(View.GONE);
        holder.constraintLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.collapsed_fixture_item_bg));
      } else {
        holder.buttonsGroup.setVisibility(View.VISIBLE);

        if (fixtureListItem.isStatsVisible()) {
          holder.statsButton.setBackgroundResource(R.drawable.clicked_fixture_stats_button);
          holder.lineupsButton.setBackgroundResource(R.drawable.fixture_lineup_button);
          holder.statsLayout.setVisibility(View.VISIBLE);
          holder.lineupsGroup.setVisibility(View.GONE);
          holder.constraintLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.expanded_fixture_item_bg_stats));
        } else {
          holder.statsButton.setBackgroundResource(R.drawable.fixture_stats_button);
          holder.lineupsButton.setBackgroundResource(R.drawable.clicked_fixture_lineup_button);
          holder.statsLayout.setVisibility(View.GONE);
          holder.lineupsGroup.setVisibility(View.VISIBLE);
          holder.constraintLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.expanded_fixture_item_bg_lineups));
        }
      }
    });

    holder.statsButton.setOnClickListener(v -> {
      if (!fixtureListItem.isStatsVisible()) {
        fixtureListItem.setStatsVisible(true);
        holder.statsButton.setBackgroundResource(R.drawable.clicked_fixture_stats_button);
        holder.lineupsButton.setBackgroundResource(R.drawable.fixture_lineup_button);
        holder.statsLayout.setVisibility(View.VISIBLE);
        holder.lineupsGroup.setVisibility(View.GONE);
        holder.constraintLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.expanded_fixture_item_bg_stats));
      }
    });

    holder.lineupsButton.setOnClickListener(v -> {
      if (fixtureListItem.isStatsVisible()) {
        fixtureListItem.setStatsVisible(false);
        holder.statsButton.setBackgroundResource(R.drawable.fixture_stats_button);
        holder.lineupsButton.setBackgroundResource(R.drawable.clicked_fixture_lineup_button);
        holder.statsLayout.setVisibility(View.GONE);
        holder.lineupsGroup.setVisibility(View.VISIBLE);
        holder.constraintLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.expanded_fixture_item_bg_lineups));
      }
    });
  }

  @Override
  public int getItemCount() {
    return  fixtureListItems.size();
  }

  public class FixtureListViewHolder extends RecyclerView.ViewHolder {
    TextView venueName, venueCity, homeScore, awayScore, elapsedTime, dateTime, status, homeTeamName, awayTeamName;
    ImageView homeTeamLogo, awayTeamLogo;
    Button statsButton, lineupsButton;
    View buttonsGroup, lineupsGroup;
    ConstraintLayout constraintLayout;
    LinearLayout statsLayout;

    // Statistics specific
    TextView[] stats = new TextView[16];
    TextView[] statsValueHome = new TextView[16];
    ProgressBar[] statsPercentageHome = new ProgressBar[16];
    TextView[] statsValueAway = new TextView[16];
    ProgressBar[] statsPercentageAway = new ProgressBar[16];

    // Lineups specific
    TextView homeFormation, awayFormation;

    TextView[] homeStartingNumber = new TextView[16];
    TextView[] homeStartingName = new TextView[16];
    TextView[] homeStartingPosition = new TextView[16];
    TextView[] homeSubstitutesNumber = new TextView[7];
    TextView[] homeSubstitutesName = new TextView[7];
    TextView[] homeSubstitutesPosition = new TextView[7];

    TextView[] awayStartingNumber = new TextView[16];
    TextView[] awayStartingName = new TextView[16];
    TextView[] awayStartingPosition = new TextView[16];
    TextView[] awaySubstitutesNumber = new TextView[7];
    TextView[] awaySubstitutesName = new TextView[7];
    TextView[] awaySubstitutesPosition = new TextView[7];

    public FixtureListViewHolder(@NonNull View itemView) {
      super(itemView);
      venueName = itemView.findViewById(R.id.venue_name);
      venueCity = itemView.findViewById(R.id.venue_city);
      homeScore = itemView.findViewById(R.id.score_home);
      awayScore = itemView.findViewById(R.id.score_away);
      elapsedTime = itemView.findViewById(R.id.elapsed_time);
      dateTime = itemView.findViewById(R.id.date_time);
      status = itemView.findViewById(R.id.status);
      homeTeamName = itemView.findViewById(R.id.home_team_name);
      awayTeamName = itemView.findViewById(R.id.away_team_name);
      homeTeamLogo = itemView.findViewById(R.id.home_team_logo);
      awayTeamLogo = itemView.findViewById(R.id.away_team_logo);
      statsButton = itemView.findViewById(R.id.stats_button);
      lineupsButton = itemView.findViewById(R.id.lineups_button);
      statsLayout = itemView.findViewById(R.id.stats_layout);
      lineupsGroup = itemView.findViewById(R.id.lineups_group);
      buttonsGroup = itemView.findViewById(R.id.buttons_group);
      constraintLayout = itemView.findViewById(R.id.card_bg);

      // Statistics specific
      for (int i = 0; i < 16; i++) {
        Integer current = i + 1;

        String idString = "stat_" + current.toString();
        int idInt = itemView.getResources().getIdentifier(idString, "id", context.getPackageName());
        stats[i] = itemView.findViewById(idInt);

        idString = "stat_" + current.toString() + "_value_home";
        idInt = itemView.getResources().getIdentifier(idString, "id", context.getPackageName());
        statsValueHome[i] = itemView.findViewById(idInt);

        idString = "stat_" + current.toString() + "_value_away";
        idInt = itemView.getResources().getIdentifier(idString, "id", context.getPackageName());
        statsValueAway[i] = itemView.findViewById(idInt);

        idString = "stat_" + current.toString() + "_percentage_home";
        idInt = itemView.getResources().getIdentifier(idString, "id", context.getPackageName());
        statsPercentageHome[i] = itemView.findViewById(idInt);

        idString = "stat_" + current.toString() + "_percentage_away";
        idInt = itemView.getResources().getIdentifier(idString, "id", context.getPackageName());
        statsPercentageAway[i] = itemView.findViewById(idInt);
      }

      // Lineup specific
      homeFormation = itemView.findViewById(R.id.home_formation);
      awayFormation = itemView.findViewById(R.id.away_formation);

      for (int i = 0; i < 11; i++) {
        Integer current = i + 1;

        String idString = "home_player_" + current.toString() + "_number";
        int idInt = itemView.getResources().getIdentifier(idString, "id", context.getPackageName());
        homeStartingNumber[i] = itemView.findViewById(idInt);

        idString = "home_player_" + current.toString() + "_name";
        idInt = itemView.getResources().getIdentifier(idString, "id", context.getPackageName());
        homeStartingName[i] = itemView.findViewById(idInt);
        homeStartingName[i].setSelected(true);

        idString = "home_player_" + current.toString() + "_position";
        idInt = itemView.getResources().getIdentifier(idString, "id", context.getPackageName());
        homeStartingPosition[i] = itemView.findViewById(idInt);

        idString = "away_player_" + current.toString() + "_number";
        idInt = itemView.getResources().getIdentifier(idString, "id", context.getPackageName());
        awayStartingNumber[i] = itemView.findViewById(idInt);

        idString = "away_player_" + current.toString() + "_name";
        idInt = itemView.getResources().getIdentifier(idString, "id", context.getPackageName());
        awayStartingName[i] = itemView.findViewById(idInt);
        awayStartingName[i].setSelected(true);

        idString = "away_player_" + current.toString() + "_position";
        idInt = itemView.getResources().getIdentifier(idString, "id", context.getPackageName());
        awayStartingPosition[i] = itemView.findViewById(idInt);
      }

      for (int i = 0; i < 7; i++) {
        Integer current = i + 1;

        String idString = "home_substitute_" + current.toString() + "_number";
        int idInt = itemView.getResources().getIdentifier(idString, "id", context.getPackageName());
        homeSubstitutesNumber[i] = itemView.findViewById(idInt);

        idString = "home_substitute_" + current.toString() + "_name";
        idInt = itemView.getResources().getIdentifier(idString, "id", context.getPackageName());
        homeSubstitutesName[i] = itemView.findViewById(idInt);
        homeSubstitutesName[i].setSelected(true);

        idString = "home_substitute_" + current.toString() + "_position";
        idInt = itemView.getResources().getIdentifier(idString, "id", context.getPackageName());
        homeSubstitutesPosition[i] = itemView.findViewById(idInt);

        idString = "away_substitute_" + current.toString() + "_number";
        idInt = itemView.getResources().getIdentifier(idString, "id", context.getPackageName());
        awaySubstitutesNumber[i] = itemView.findViewById(idInt);

        idString = "away_substitute_" + current.toString() + "_name";
        idInt = itemView.getResources().getIdentifier(idString, "id", context.getPackageName());
        awaySubstitutesName[i] = itemView.findViewById(idInt);
        awaySubstitutesName[i].setSelected(true);

        idString = "away_substitute_" + current.toString() + "_position";
        idInt = itemView.getResources().getIdentifier(idString, "id", context.getPackageName());
        awaySubstitutesPosition[i] = itemView.findViewById(idInt);
      }
    }

    private String getDateTime(long timestamp) {
      Calendar calendar = Calendar.getInstance(Locale.getDefault());
      calendar.setTimeInMillis(timestamp * 1000);
      return DateFormat.format("dd-MM-yyyy hh:mm", calendar).toString();
    }

    int convertStatValueToInt(String statValue) {
      statValue = statValue.substring(0, statValue.length() - 1);
      if (statValue.endsWith(".")) {
        statValue = statValue.substring(0, statValue.length() - 1);
      }
      return Integer.parseInt(statValue);
    }

    int getHomePercentage(int home, int away) {
      int total = home + away;
      return total == 0 ? 0 : ((home * 100) / total);
    }

    int getAwayPercentage(int home, int away) {
      int total = home + away;
      return total == 0 ? 0 : ((away * 100) / total);
    }
  }
}
