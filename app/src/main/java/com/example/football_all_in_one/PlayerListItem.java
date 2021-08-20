package com.example.football_all_in_one;

import com.example.football_all_in_one.model.players_response.Cards;
import com.example.football_all_in_one.model.players_response.Games;
import com.example.football_all_in_one.model.players_response.Goals;
import com.example.football_all_in_one.model.players_response.Penalty;
import com.example.football_all_in_one.model.players_response.Player;

public class PlayerListItem {
  private Player player;
  private Games games;
  private Goals goals;
  private Cards cards;
  private Penalty penalty;

  public PlayerListItem(Player player, Games games, Goals goals, Cards cards, Penalty penalty) {
    this.player = player;
    this.games = games;
    this.goals = goals;
    this.cards = cards;
    this.penalty = penalty;
  }

  public Player getPlayer() {
    return player;
  }

  public Games getGames() {
    return games;
  }

  public Goals getGoals() {
    return goals;
  }

  public Cards getCards() {
    return cards;
  }

  public Penalty getPenalty() {
    return penalty;
  }
}
