/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author laure
 */
public interface JobOfferDAO {
    
    public abstract void addJoboffer (JobOffer joboffer);
    public abstract void deleteJoboffer (JobOffer joboffer);
    public abstract void addCandidate (JobSeeker jobseeker, JobOffer joboffer);
    public abstract JobOffer findJobOffer(int id);
    public abstract JobOffer findJobOffer2(String title, Employer employer);
}
