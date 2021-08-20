package com.example.football_all_in_one;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.football_all_in_one.model.players_response.Birth;
import com.example.football_all_in_one.model.players_response.Cards;
import com.example.football_all_in_one.model.players_response.Games;
import com.example.football_all_in_one.model.players_response.Goals;
import com.example.football_all_in_one.model.players_response.Penalty;
import com.example.football_all_in_one.model.players_response.Player;

import java.util.List;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerListViewHolder> {
  Context context;
  List<PlayerListItem> playerListItems;

  public PlayerListAdapter(Context context, List<PlayerListItem> playerListItems) {
    this.context = context;
    this.playerListItems = playerListItems;
  }

  @NonNull
  @Override
  public PlayerListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.player_list_item, parent, false);
    return new PlayerListViewHolder(view);
  }

  @SuppressLint("SetTextI18n")
  @Override
  public void onBindViewHolder(@NonNull PlayerListViewHolder holder, int position) {
    PlayerListItem playerListItem = playerListItems.get(position);
    Player player = playerListItem.getPlayer();
    Birth birth = player.getBirth();
    Games games = playerListItem.getGames();
    Goals goals = playerListItem.getGoals();
    Cards cards = playerListItem.getCards();
    Penalty penalty = playerListItem.getPenalty();

    holder.playerName.setText(player.getName());
    holder.playerPosition.setText(games.getPosition());
    holder.age.setText("Age: " + String.valueOf(player.getAge()));
    holder.height.setText("Height: " + player.getHeight());
    holder.weight.setText("Weight: " + player.getWeight());
    holder.nationality.setText("Nationality: " + player.getNationality());
    holder.injured.setText("Injured: " + (player.isInjured() ? "Yes" : "No"));
    holder.birthDate.setText("Date: " + birth.getDate());
    holder.birthPlace.setText("Place: " + birth.getPlace());
    holder.birthCountry.setText("Country: " + birth.getCountry());
    holder.appearances.setText("Appearances: " + String.valueOf(games.getAppearances()));
    holder.lineups.setText("Lineups: " + String.valueOf(games.getLineups()));
    holder.rating.setText("Rating: " + games.getRating());
    holder.captain.setText("Captain: " + (games.isCaptain() ? "Yes" : "No"));
    holder.goalsScored.setText("Scored: " + String.valueOf(goals.getTotal()));
    holder.goalsAssists.setText("Assists: " + String.valueOf(goals.getAssists()));
    holder.goalsSaved.setText("Saves: " + String.valueOf(goals.getSaves()));
    holder.yellowCards.setText("Yellow: " + String.valueOf(cards.getYellow()));
    holder.redCards.setText("Red: " + String.valueOf(cards.getRed()));
    holder.yellowRedCards.setText("Yellow Red: " + String.valueOf(cards.getYellowRed()));
    holder.penaltyScored.setText("Scored: " + String.valueOf(penalty.getScored()));
    holder.penaltyMissed.setText("Missed: " + String.valueOf(penalty.getMissed()));
    holder.penaltySaved.setText("Saved: " + String.valueOf(penalty.getSaved()));
    Glide.with(context).load(player.getPhoto()).into(holder.playerImg);

    holder.itemView.setOnClickListener(v -> {
      if (holder.detailsGroup.getVisibility() == View.VISIBLE) {
        holder.detailsGroup.setVisibility(View.GONE);
        holder.constraintLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.collapsed_player_item_bg));
      } else {
        holder.detailsGroup.setVisibility(View.VISIBLE);
        holder.constraintLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.expanded_player_item_bg));
      }
    });
  }

  @Override
  public int getItemCount() {
    return playerListItems.size();
  }

  public class PlayerListViewHolder extends RecyclerView.ViewHolder {
    ImageView playerImg;
    TextView playerNumber, playerName, playerPosition;
    TextView age, height, weight, nationality, injured;
    TextView birthDate, birthPlace, birthCountry;
    TextView appearances, lineups, rating, captain;
    TextView goalsScored, goalsAssists, goalsSaved;
    TextView yellowCards, redCards, yellowRedCards;
    TextView penaltyScored, penaltyMissed, penaltySaved;
    View detailsGroup;
    ConstraintLayout constraintLayout;

    public PlayerListViewHolder(@NonNull View itemView) {
      super(itemView);
      playerImg = itemView.findViewById(R.id.player_img);
      playerName = itemView.findViewById(R.id.player_name);
      playerPosition = itemView.findViewById(R.id.player_position);
      age = itemView.findViewById(R.id.age);
      height = itemView.findViewById(R.id.height);
      weight = itemView.findViewById(R.id.weight);
      nationality = itemView.findViewById(R.id.nationality);
      injured = itemView.findViewById(R.id.injured);
      birthDate = itemView.findViewById(R.id.birth_date);
      birthPlace = itemView.findViewById(R.id.birth_place);
      birthCountry = itemView.findViewById(R.id.birth_country);
      appearances = itemView.findViewById(R.id.appearances);
      lineups = itemView.findViewById(R.id.lineups);
      rating = itemView.findViewById(R.id.rating);
      captain = itemView.findViewById(R.id.captain);
      goalsScored = itemView.findViewById(R.id.goals_scored);
      goalsAssists = itemView.findViewById(R.id.assists);
      goalsSaved = itemView.findViewById(R.id.goals_saved);
      yellowCards = itemView.findViewById(R.id.yellow_cards);
      redCards = itemView.findViewById(R.id.red_cards);
      yellowRedCards = itemView.findViewById(R.id.yellow_red_cards);
      penaltyScored = itemView.findViewById(R.id.penalty_scored);
      penaltyMissed = itemView.findViewById(R.id.penalty_missed);
      penaltySaved = itemView.findViewById(R.id.penalty_saved);
      detailsGroup = itemView.findViewById(R.id.details_group);
      constraintLayout = itemView.findViewById(R.id.card_bg);

      playerName.setSelected(true);
    }
  }
}
