/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author laure
 */
public interface JobSeekerDAO {
    
    public abstract void addJobSeeker (JobSeeker jobseeker);
    public abstract void deleteJobSeeker (JobSeeker jobseeker);
    public abstract ArrayList<JobOffer> browseOffers(String profile, String duration, Date date, double salary, String location);
    public abstract boolean apply(JobOffer offer, JobSeeker jobseeker);
    public abstract JobSeeker findJobSeeker(String login);
    
}
