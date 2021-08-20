package com.example.football_all_in_one.model.players_statistics_for_a_fixture_response;

public class Games {
    private int minutes;
    private int number;
    private String position;
    private String rating;
    private boolean captain;
    private boolean substitute;

    public Games(int minutes, int number, String position, String rating, boolean captain, boolean substitute) {
        this.minutes = minutes;
        this.number = number;
        this.position = position;
        this.rating = rating;
        this.captain = captain;
        this.substitute = substitute;
    }

    public int getMinutes() { return minutes; }

    public int getNumber() { return number; }

    public String getPosition() { return position; }

    public String getRating() { return rating; }

    public boolean isCaptain() { return captain; }

    public boolean isSubstitute() { return substitute; }
}
