package com.siyang.SwipeJobsAssess.Match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Availability implements Serializable {
    private String title;
    private int dayIndex;

    public Availability(String title, int dayIndex) {
        this.title = title;
        this.dayIndex = dayIndex;
    }

    public Availability() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public void setDayIndex(int dayIndex) {
        this.dayIndex = dayIndex;
    }
}
