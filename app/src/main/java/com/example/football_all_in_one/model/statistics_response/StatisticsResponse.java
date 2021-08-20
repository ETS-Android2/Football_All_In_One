package com.example.football_all_in_one.model.statistics_response;

import com.google.gson.annotations.SerializedName;

public class StatisticsResponse {
    @SerializedName("response")
    private StatisticsResponseObject statisticsResponseObject;

    public StatisticsResponse(StatisticsResponseObject statisticsResponseObject) {
        this.statisticsResponseObject = statisticsResponseObject;
    }

    public StatisticsResponseObject getStatisticsResponseObject() {
        return statisticsResponseObject;
    }
}
