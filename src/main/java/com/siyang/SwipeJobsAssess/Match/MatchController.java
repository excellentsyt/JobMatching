package com.siyang.SwipeJobsAssess.Match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @RequestMapping("/match/{workerId}")
    public List<Job> getMatch(@PathVariable String workerId) {

        // Obtain all workers
        Worker[] workers = matchService.getWorkers();

        // Find the worker to be queried
        Worker workerWithId = matchService.getWorkerWithId(workerId, Arrays.asList(workers));

        // Obtain all jobs
        Job[] jobs = matchService.getJobs();

        // Make a match
        return matchService.makeMatch(workerWithId, Arrays.asList(jobs));

//        return new ArrayList<Worker>(Arrays.asList(workerWithId));
//        return Arrays.asList(workers);
    }
}
