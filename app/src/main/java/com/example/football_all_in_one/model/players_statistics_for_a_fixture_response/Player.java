package com.example.football_all_in_one.model.players_statistics_for_a_fixture_response;

public class Player {
    private int id;
    private String name;
    private String photo;

    public Player(int id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getPhoto() { return photo; }
}
