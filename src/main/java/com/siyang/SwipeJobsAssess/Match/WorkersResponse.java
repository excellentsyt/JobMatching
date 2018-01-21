package com.siyang.SwipeJobsAssess.Match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkersResponse implements Serializable {

    private List<Worker> workers = new ArrayList<>();

    public WorkersResponse() {
    }

    public WorkersResponse(List<Worker> workers) {
        this.workers = workers;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}
