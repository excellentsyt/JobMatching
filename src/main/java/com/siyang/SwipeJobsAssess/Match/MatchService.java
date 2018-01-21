package com.siyang.SwipeJobsAssess.Match;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MatchService {

    public Worker[] getWorkers() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://test.swipejobs.com/api/workers";
//        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
//        return response.getBody();

        return restTemplate.getForObject(fooResourceUrl, Worker[].class);
    }
}
