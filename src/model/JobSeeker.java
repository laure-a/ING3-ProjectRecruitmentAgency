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
public class JobSeeker {

    private String first_name, last_name, email, profile, password;
    private GregorianCalendar birth_date;
    private double phone_number;
    

    public JobSeeker(String first_name, String last_name, String email, String profile,
            GregorianCalendar birth_date, double phone_number, String password) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.profile = profile;
        this.birth_date = birth_date;
        this.phone_number = phone_number;
        this.password = password;
    }

    public JobSeeker() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getLastName() {
        return last_name;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getEmail() {
        return email;
    }

    public String getProfile() {
        return profile;
    }

    public GregorianCalendar getDate() {
        return birth_date;
    }

    public double getPhone() {
        return phone_number;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "JobSeeker{" + "first_name=" + first_name + ", last_name=" + last_name + ", email=" + email + ", profile=" + profile + ", password=" + password + ", birth_date=" + birth_date + ", phone_number=" + phone_number + '}';
    }
    
    

}
