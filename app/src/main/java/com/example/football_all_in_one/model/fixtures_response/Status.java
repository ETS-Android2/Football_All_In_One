package com.example.football_all_in_one.model.fixtures_response;

import com.google.gson.annotations.SerializedName;

public class Status {
    @SerializedName("long")
    private String longstatus;
    private int elapsed;

    public Status(String longstatus, int elapsed) {
        this.longstatus = longstatus;
        this.elapsed = elapsed;
    }

    public String getLongstatus() { return longstatus;
    }

    public int getElapsed() { return elapsed; }
}


