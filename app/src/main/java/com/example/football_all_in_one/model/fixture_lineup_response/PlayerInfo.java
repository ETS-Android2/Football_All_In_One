package com.example.football_all_in_one.model.fixture_lineup_response;

public class PlayerInfo {
  private int id;
  private String name;
  private int number;
  private String pos;

  public PlayerInfo(int id, String name, int number, String pos) {
    this.id = id;
    this.name = name;
    this.number = number;
    this.pos = pos;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getNumber() {
    return number;
  }

  public String getPos() {
    return pos;
  }
}
