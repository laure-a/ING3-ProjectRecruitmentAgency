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
public interface AgencyDAO {
    
    public abstract void addAgency(Agency agency);
    public abstract void deleteAgency(Agency agency);
    public abstract String reviewPopularity();
    public abstract Agency findAgency(String login);
    
}
