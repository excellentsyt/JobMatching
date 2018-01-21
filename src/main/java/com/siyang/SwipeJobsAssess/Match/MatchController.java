package com.siyang.SwipeJobsAssess.Match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @RequestMapping("/match/{id}")
    public List<Worker> getMatch(@PathVariable String workerId) {
        Worker[] workers = matchService.getWorkers();
        Job[] jobs = matchService.getJobs();

        Worker workerWithId = matchService.getWorkerWithId(workerId);

        return Arrays.asList(workers);
    }
}
