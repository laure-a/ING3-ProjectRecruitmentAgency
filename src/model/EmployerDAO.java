/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author laure
 */
public interface EmployerDAO {
    
    public abstract void addEmployer (Employer employer);
    public abstract void deleteEmployer (Employer employer);
    public abstract String browseCandidates(Employer employer);
    public abstract void updateOffers(JobOffer jobOffer, String column, String newValue);
    public abstract Employer findEmployer(String login);
    public abstract ArrayList<String> getOffers(Employer employer);
}
