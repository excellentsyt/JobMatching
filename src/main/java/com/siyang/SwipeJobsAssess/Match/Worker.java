package com.siyang.SwipeJobsAssess.Match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Worker implements Serializable {

    private int rating;
    private boolean isActive;
    private List<String> certificates;
    private List<String> skills;
    private JobSearchAddress jobSearchAddress;
    private String transportation;
    private boolean hasDriversLicense;
    private List<Availability> availability;
    private String phone;
    private String email;
    private int age;
    private String guid;
    private int userId;

    public Worker(int rating, boolean isActive, List<String> certificates, List<String> skills, JobSearchAddress jobSearchAddress, String transportation, boolean hasDriversLicense, List<Availability> availability, String phone, String email, int age, String guid, int userId) {
        this.rating = rating;
        this.isActive = isActive;
        this.certificates = certificates;
        this.skills = skills;
        this.jobSearchAddress = jobSearchAddress;
        this.transportation = transportation;
        this.hasDriversLicense = hasDriversLicense;
        this.availability = availability;
        this.phone = phone;
        this.email = email;
        this.age = age;
        this.guid = guid;
        this.userId = userId;
    }

    public Worker() {
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public List<String> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<String> certificates) {
        this.certificates = certificates;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public JobSearchAddress getJobSearchAddress() {
        return jobSearchAddress;
    }

    public void setJobSearchAddress(JobSearchAddress jobSearchAddress) {
        this.jobSearchAddress = jobSearchAddress;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public boolean isHasDriversLicense() {
        return hasDriversLicense;
    }

    public void setHasDriversLicense(boolean hasDriversLicense) {
        this.hasDriversLicense = hasDriversLicense;
    }

    public List<Availability> getAvailability() {
        return availability;
    }

    public void setAvailability(List<Availability> availability) {
        this.availability = availability;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
