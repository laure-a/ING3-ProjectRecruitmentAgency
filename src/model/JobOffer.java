/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.*;
/**
 *
 * @author vince
 */
public class JobOffer {
    
    private String title, employer, profile, duration, location;
    private Date start_date;
    private double salary;
    private int id;
    private ArrayList<String> candidates;
    
    public JobOffer (String title, String employer, String profile, String duration, Date start_date, double salary, String location, int id){
        
        this.title = title;
        this.employer = employer;
        this.profile = profile;
        this.duration = duration;
        this.start_date = start_date;
        this.salary = salary;
        this.candidates = new ArrayList<>(); 
        this.location = location;
        this.id= id;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getProfile (){
        return profile;
    }
    
    public String getDuration (){
        return duration;
    }
            
    public String getLocation(){
        return location;
    }
    
    public double getSalary (){
        return salary;
    }
    
    public Date getDate(){
        return start_date;
    }  
    
    public String getEmployer (){
        return employer;
    }
    
    public int getId(){
        return id;
    }
}
