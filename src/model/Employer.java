/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author vince
 */
public class Employer {
    
    private String name, password;
    private boolean member;
    private ArrayList<Double> offers;
    private final double BILL_NO_MEMBER = 100.0;
    private final double BILL_MEMBER = 50.0;
    
    public Employer(String name, String password, boolean member){
        
        this.offers = new ArrayList<>(); 
        this.name = name;
        this.password = password;
        this.member = member;
        
    }
    
    public String getName (){
        return name;
    }
    
    public String getPassword (){
        return password;
    }
    
    public boolean getMember (){
        return member;
    }
    public double getBillMember(){
        return BILL_MEMBER;
    }
    public double getBillNoMember(){
        return BILL_NO_MEMBER;
    }
    
    
}
