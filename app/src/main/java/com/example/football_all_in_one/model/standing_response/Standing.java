package com.example.football_all_in_one.model.standing_response;

public class Standing {
    private int rank;
    private Team team;
    private int points;
    private String group;

    public Standing(int rank, Team team, int points, String group) {
        this.rank = rank;
        this.team = team;
        this.points = points;
        this.group = group;
    }

    public int getRank() {
        return rank;
    }

    public Team getTeam() {
        return team;
    }

    public int getPoints() {
        return points;
    }

    public String getGroup() {
        return group;
    }
}
