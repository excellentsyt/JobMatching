package com.siyang.SwipeJobsAssess.Match;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MatchService {

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

        // Set up return list
        List<Worker> ret = new ArrayList<>();

        // Set up a temporary Set
        Set<Job> tempMatchedJobs = new HashSet<>(jobs);

        // 1. Is active
        if (!worker.isActive()) {
            return ret;
        }

        // 2. Driver licence matching
        for (Job job : tempMatchedJobs) {
            if (job.isDriverLicenseRequired() && !worker.isHasDriversLicense()) {
                tempMatchedJobs.remove(job);
            }
        }

        // 3. Certificates matching
        for (Job job : tempMatchedJobs) {
            if (!hasAllCertificates(worker, job)) {
                tempMatchedJobs.remove(job);
            }
        }

        // 4. Distance matching
        for (Job job : tempMatchedJobs) {
            if (!withinRange(worker, job)) {
                tempMatchedJobs.remove(job);
            }
        }



        return ret;
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
        double distance = distance(Double.parseDouble(worker.getJobSearchAddress().getLatitude()),
                                   Double.parseDouble(worker.getJobSearchAddress().getLongitude()),
                                   Double.parseDouble(job.getLocation().getLatitude()),
                                   Double.parseDouble(job.getLocation().getLongitude()),
                                   "K");

        return !(distance > worker.getJobSearchAddress().getMaxJobDistance());
    }

    /**
     * Calculate the distance between two points based on long and lat.
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @param unit
     * @return
     */
    private double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        if (unit.equals("K")) {
            dist = dist * 1.609344;
        } else if (unit.equals("N")) {
            dist = dist * 0.8684;
        }

        return dist;
    }


    /**
     * This function converts decimal degrees to radians
     * @param deg
     * @return
     */
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }


    /**
     * This function converts radians to decimal degrees
     * @param rad
     * @return
     */
    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
