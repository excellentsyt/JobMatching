package com.siyang.SwipeJobsAssess.Match;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MatchService {

    public Worker[] getWorkers() {
        RestTemplate restTemplate = new RestTemplate();
        String workersResourceUrl = "http://test.swipejobs.com/api/workers";
//        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
//        return response.getBody();

        return restTemplate.getForObject(workersResourceUrl, Worker[].class);
    }

    public Job[] getJobs() {
        RestTemplate restTemplate = new RestTemplate();
        String jobsResourceUrl = "http://test.swipejobs.com/api/jobs";

        return restTemplate.getForObject(jobsResourceUrl, Job[].class);
    }
}
