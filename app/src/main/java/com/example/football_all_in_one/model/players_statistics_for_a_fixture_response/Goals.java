package com.example.football_all_in_one.model.players_statistics_for_a_fixture_response;

public class Goals {
    private int total;
    private int conceded;
    private int assists;
    private int saves;

    public Goals(int total, int conceded, int assists, int saves) {
        this.total = total;
        this.conceded = conceded;
        this.assists = assists;
        this.saves = saves;
    }

    public int getTotal() { return total; }

    public int getConceded() { return conceded; }

    public int getAssists() { return assists; }

    public int getSaves() { return saves; }
}
