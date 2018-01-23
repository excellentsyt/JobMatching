package com.siyang.SwipeJobsAssess;

import com.siyang.SwipeJobsAssess.Match.Job;
import com.siyang.SwipeJobsAssess.Match.MatchService;
import com.siyang.SwipeJobsAssess.Match.Worker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SwipeJobsAssessApplicationTests {

	@Autowired
	private MatchService matchService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetWorkers() {
		Worker[] workers = matchService.getWorkers();
		assertEquals(50, workers.length);
	}

	@Test
	public void testGetJobs() {
		Job[] jobs = matchService.getJobs();
		assertEquals(40, jobs.length);
	}

	@Test
	public void testMatch() {

	}
}
