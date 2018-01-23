package com.siyang.SwipeJobsAssess.Match;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class MatchService {
    PriorityQueue<WrapperJob> pq;

    /**
     * @return
     */
    public Worker[] getWorkers() {
        RestTemplate restTemplate = new RestTemplate();
        String workersResourceUrl = "http://test.swipejobs.com/api/workers";
        return restTemplate.getForObject(workersResourceUrl, Worker[].class);
    }

    /**
     * @return
     */
    public Job[] getJobs() {
        RestTemplate restTemplate = new RestTemplate();
        String jobsResourceUrl = "http://test.swipejobs.com/api/jobs";
        return restTemplate.getForObject(jobsResourceUrl, Job[].class);
    }

    /**
     * @param workerId
     * @param workers
     * @return
     */
    public Worker getWorkerWithId(String workerId, List<Worker> workers) {
        return workers.stream().filter(worker -> worker.getUserId() == Integer.parseInt(workerId)).findFirst().get();
    }


    /**
     *Must meet conditions:
     -- isActive
     -- driver licence
     -- certificates matching
     -- distance matching

     Sorting jobs with conditions:
     -- 1. pay rate
     -- 2. distance
     -- 3. number workers required

     * @param worker - the worker
     * @param jobs - all jobs that are currently available
     * @return - top three jobs if there is any
     */
    public List<Job> makeMatch(Worker worker, List<Job> jobs) {

        // Set up return list
        List<Job> ret = new ArrayList<>();

        // Set up a temporary Set to remove any duplicate jobs in case
        Set<Job> tempMatchedJobs = new HashSet<>(jobs);

        // 1. Is active - assume only active workers can get recommended jobs returned
        if (!worker.isIsActive()) {
            return ret;
        }

        // Check other must conditions
        for(Iterator<Job> i = tempMatchedJobs.iterator(); i.hasNext();) {
            Job job = i.next();

            // 2. Driver licence matching
            if (job.isDriverLicenseRequired() && !worker.isHasDriversLicense()) {
                i.remove();
                continue;
            }

            // 3. Certificates matching
            if (!hasAllCertificates(worker, job)) {
                i.remove();
                continue;
            }

            // 4. Distance matching
            if (!withinRange(worker, job)) {
                i.remove();
            }
        }

        // Sort the matching jobs according to a few conditions
        // Here I define the sorting order but it is changeable according to different requirements
        this.pq = new PriorityQueue<>((o1, o2) -> {
            double bill1 = Double.parseDouble(o1.getOriginalJob().getBillRate().substring(1));
            double bill2 = Double.parseDouble(o2.getOriginalJob().getBillRate().substring(1));

            // Assume we recommend jobs which have higher rates
            if (bill1 > bill2) {
                return -1;
            } else if (bill1 < bill2) {
                return 1;
            } else {
                // Assume we recommend jobs which are closer to the workers
                if (o1.getDistanceToWorker() > o2.getDistanceToWorker()) {
                    return 1;
                } else if (o1.getDistanceToWorker() < o2.getDistanceToWorker()) {
                    return -1;
                } else {
                    //Assume we recommend jobs which have more vacancies
                    return o1.getOriginalJob().getWorkersRequired() > o2.getOriginalJob().getWorkersRequired() ? -1 : 1;
                }
            }
        });

        // Push into maxHeap
        for (Job job : tempMatchedJobs) {
            pq.offer(new WrapperJob(job, worker));
        }

        // Output no more than three jobs
        for (int i = 0; i < 3; i++) {
            if (!pq.isEmpty()) {
                ret.add(pq.poll().getOriginalJob());
            }
        }

        return ret;
    }

    public PriorityQueue<WrapperJob> getPq() {
        return pq;
    }

    /**
     * @param worker
     * @param job
     * @return
     */
    private boolean hasAllCertificates(Worker worker, Job job) {
        for(String certificate : job.getRequiredCertificates()) {
            if (!worker.getCertificates().contains(certificate)) {
                return false;
            }
        }
        return true;
    }

    private boolean withinRange(Worker worker, Job job) {
        WrapperJob wrapperJob = new WrapperJob(job, worker);
        return (double)worker.getJobSearchAddress().getMaxJobDistance() > wrapperJob.getDistanceToWorker();
    }
}
