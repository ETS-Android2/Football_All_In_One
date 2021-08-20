package com.example.football_all_in_one;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.football_all_in_one.model.teams_response.Team;
import com.example.football_all_in_one.model.teams_response.Venue;

import java.util.List;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.TeamListViewHolder> {
  private Context context;
  private List<TeamListItem> teamListItems;
  private int leagueId, season;

  public TeamListAdapter(Context context, List<TeamListItem> teamListItems, int leagueId, int season) {
    this.context = context;
    this.teamListItems = teamListItems;
    this.leagueId = leagueId;
    this.season = season;
  }

  @NonNull
  @Override
  public TeamListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.team_list_item, parent, false);
    return new TeamListViewHolder(view);
  }

  @SuppressLint("SetTextI18n")
  @Override
  public void onBindViewHolder(@NonNull TeamListViewHolder holder, int position) {
    TeamListItem teamListItem = teamListItems.get(position);
    Team team = teamListItem.getTeam();
    Venue venue = teamListItem.getVenue();

    holder.teamName.setText(team.getName());
    holder.founded.setText("Founded: " + team.getFounded());
    holder.country.setText("Country: " + team.getCountry());
    holder.national.setText("National: " + (team.getNational() ? "Yes" : "No"));
    holder.venueName.setText("Name: " + venue.getName());
    holder.venueAddress.setText("Address: " + venue.getAddress());
    holder.venueCity.setText("City: " + venue.getCity());
    holder.venueCapacity.setText("Capacity: " + venue.getCapacity());
    Glide.with(context).load(team.getLogo()).into(holder.teamLogo);

    holder.itemView.setOnClickListener(v -> {
      if (holder.detailsGroup.getVisibility() == View.VISIBLE) {
        holder.detailsGroup.setVisibility(View.GONE);
        holder.constraintLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.collapsed_team_item_bg));
      } else {
        holder.detailsGroup.setVisibility(View.VISIBLE);
        holder.constraintLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.expanded_team_item_bg));
      }
    });

    holder.statsButton.setOnClickListener(v -> visitStatisticsPage(team.getId(), team.getName(), team.getLogo(), leagueId, season));

    holder.fixturesButton.setOnClickListener(v -> visitFixturesPage(team.getId(), leagueId, season));

    holder.playersButton.setOnClickListener(v -> visitPlayersPage(team.getId(), team.getName(), team.getLogo(), leagueId, season));
  }

  @Override
  public int getItemCount() {
    return teamListItems.size();
  }

  public class TeamListViewHolder extends RecyclerView.ViewHolder {
    ImageView teamLogo;
    TextView teamName, founded, country, national, venueName, venueAddress, venueCity, venueCapacity;
    Button statsButton, fixturesButton, playersButton;
    View detailsGroup;
    ConstraintLayout constraintLayout;

    public TeamListViewHolder(@NonNull View itemView) {
      super(itemView);
      teamLogo = itemView.findViewById(R.id.team_logo);
      teamName = itemView.findViewById(R.id.team_name);
      founded = itemView.findViewById(R.id.founded);
      country = itemView.findViewById(R.id.country);
      national = itemView.findViewById(R.id.national);
      venueName = itemView.findViewById(R.id.venue_name);
      venueAddress = itemView.findViewById(R.id.venue_address);
      venueCity = itemView.findViewById(R.id.venue_city);
      venueCapacity = itemView.findViewById(R.id.venue_capacity);
      statsButton = itemView.findViewById(R.id.stats_button);
      fixturesButton = itemView.findViewById(R.id.fixtures_button);
      playersButton = itemView.findViewById(R.id.players_button);
      detailsGroup = itemView.findViewById(R.id.details_group);
      constraintLayout = itemView.findViewById(R.id.card_bg);

      teamName.setSelected(true);
    }
  }

  private void visitStatisticsPage(int teamId, String teamName, String teamLogo, int leagueId, int season) {
    Intent intent = new Intent(context, StatisticsActivity.class);
    intent.putExtra(MainActivity.EXTRA + "teamId", teamId);
    intent.putExtra(MainActivity.EXTRA + "teamName", teamName);
    intent.putExtra(MainActivity.EXTRA + "teamLogo", teamLogo);
    intent.putExtra(MainActivity.EXTRA + "leagueId", leagueId);
    intent.putExtra(MainActivity.EXTRA + "season", season);
    context.startActivity(intent);
  }

  private void visitFixturesPage(int teamId, int leagueId, int season) {
    Intent intent = new Intent(context, FixturesActivity.class);
    intent.putExtra(MainActivity.EXTRA + "teamId", teamId);
    intent.putExtra(MainActivity.EXTRA + "leagueId", leagueId);
    intent.putExtra(MainActivity.EXTRA + "season", season);
    context.startActivity(intent);
  }

  private void visitPlayersPage(int teamId, String teamName, String teamLogo, int leagueId, int season) {
    Intent intent = new Intent(context, PlayersActivity.class);
    intent.putExtra(MainActivity.EXTRA + "teamId", teamId);
    intent.putExtra(MainActivity.EXTRA + "teamName", teamName);
    intent.putExtra(MainActivity.EXTRA + "teamLogo", teamLogo);
    intent.putExtra(MainActivity.EXTRA + "leagueId", leagueId);
    intent.putExtra(MainActivity.EXTRA + "season", season);
    context.startActivity(intent);
  }
}
