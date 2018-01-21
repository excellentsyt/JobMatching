package com.siyang.SwipeJobsAssess.Match;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MatchService {

    public Worker[] getWorkers() {
        RestTemplate restTemplate = new RestTemplate();
        String workersResourceUrl = "http://test.swipejobs.com/api/workers";
        return restTemplate.getForObject(workersResourceUrl, Worker[].class);
    }

    public Job[] getJobs() {
        RestTemplate restTemplate = new RestTemplate();
        String jobsResourceUrl = "http://test.swipejobs.com/api/jobs";
        return restTemplate.getForObject(jobsResourceUrl, Job[].class);
    }

    public Worker getWorkerWithId(String workerId, List<Worker> workers) {
        return workers.stream().filter(worker -> worker.getUserId() == Integer.parseInt(workerId)).findFirst().get();
    }


    /**
     * Must meet conditions:
     -- isActive
     -- driver licence
     -- certificates matching
     -- distance matching

     Sorting conditions:
     -- pay rate
     -- distance
     -- number workers required

     * @param worker - the worker
     * @param jobs - all jobs that are currently available
     * @return - top three jobs if there is any
     */
    public List<Worker> makeMatch(Worker worker, List<Job> jobs) {

    }
}
