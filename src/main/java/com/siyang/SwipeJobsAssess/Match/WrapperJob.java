package com.siyang.SwipeJobsAssess.Match;

public class WrapperJob {
    private Job originalJob;
    private Worker worker;
    private double distanceToWorker;

    public WrapperJob() {
    }

    public WrapperJob(Job originalJob, Worker worker) {
        this.originalJob = originalJob;
        this.worker = worker;

        // Calculate distance
        this.distanceToWorker = distance(Double.parseDouble(this.worker.getJobSearchAddress().getLatitude()),
                Double.parseDouble(this.worker.getJobSearchAddress().getLongitude()),
                Double.parseDouble(this.originalJob.getLocation().getLatitude()),
                Double.parseDouble(this.originalJob.getLocation().getLongitude()),
                "K");

    }

    public Job getOriginalJob() {
        return originalJob;
    }

    public void setOriginalJob(Job originalJob) {
        this.originalJob = originalJob;
    }

    public double getDistanceToWorker() {
        return distanceToWorker;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
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
