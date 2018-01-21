package com.siyang.SwipeJobsAssess.Match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobSearchAddress implements Serializable {
    private String unit;
    private int maxJobDistance;
    private String longitude;
    private String latitude;

    public JobSearchAddress(String unit, int maxJobDistance, String longitude, String latitude) {
        this.unit = unit;
        this.maxJobDistance = maxJobDistance;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public JobSearchAddress() {
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getMaxJobDistance() {
        return maxJobDistance;
    }

    public void setMaxJobDistance(int maxJobDistance) {
        this.maxJobDistance = maxJobDistance;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
