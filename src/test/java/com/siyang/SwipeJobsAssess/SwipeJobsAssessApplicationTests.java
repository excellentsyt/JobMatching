package com.siyang.SwipeJobsAssess;

import com.siyang.SwipeJobsAssess.Match.Job;
import com.siyang.SwipeJobsAssess.Match.MatchService;
import com.siyang.SwipeJobsAssess.Match.Worker;
import com.siyang.SwipeJobsAssess.Match.WrapperJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
		Worker[] workers = matchService.getWorkers();
		Job[] jobs = matchService.getJobs();

		for (Worker worker : workers) {
			List<Job> result = matchService.makeMatch(worker, Arrays.asList(jobs));
			assertTrue(result.size() <= 3);
			PriorityQueue<WrapperJob> pq = matchService.getPq();

			/*
			 *Must meet condition:
			 -- isActive
			 */
			if (!worker.isIsActive()) {
				assertEquals(0, pq.size());
			} else {
				while(pq.size() > 0) {
					WrapperJob wj = pq.poll();
					assertWorkerMatchJob(worker, wj);
				}
			}
		}
	}

	/**
	 *Must meet conditions:
	 -- isActive
	 -- driver licence
	 -- certificates matching
	 -- distance matching
	 */
	private void assertWorkerMatchJob(Worker worker, WrapperJob job) {
		assertTrue(worker.isIsActive());
		if (job.getOriginalJob().isDriverLicenseRequired()) {
			assertTrue(worker.isHasDriversLicense() == job.getOriginalJob().isDriverLicenseRequired());

		}
		for(String certificate : job.getOriginalJob().getRequiredCertificates()) {
			assertTrue(worker.getCertificates().contains(certificate));
		}
		assertTrue(job.getDistanceToWorker() < (double)worker.getJobSearchAddress().getMaxJobDistance());
	}
}
