/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//imports + package
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.*;
import view.*;




//action listener pour les boutons du login 
public class LoginManager implements ActionListener {

    private final LogGui logView;

    public LoginManager(LogGui view) {
        logView = view;
    }

    
    //action performed : ce qu'il se passe quand action lsitener déclanché
    @Override
    public void actionPerformed(ActionEvent ae) {

        //user connexion
        if (ae.getSource() == logView.submit_button) {

            if (logView.job_seeker_button.isSelected()) {
                JobSeekerDAOImpl userJ = new JobSeekerDAOImpl();
                JobSeeker jobSeekerUser = userJ.findJobSeeker(logView.email.getText());
                if (jobSeekerUser != null) {
                    if (jobSeekerUser.getPassword().equals(new String(logView.password.getPassword()))) {
                        logView.dispose();
                        Guiseeker seekView = new Guiseeker(jobSeekerUser.getEmail());
                        seekView.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Your password is incorrect. Try again");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Login not found. Try again or create an account");
                }
            }

            if (logView.employer_button.isSelected()) {
                EmployerDAOImpl userJ = new EmployerDAOImpl();
                Employer employerUser = userJ.findEmployer(logView.email.getText());
                if (employerUser != null) {
                    if (employerUser.getPassword().equals(new String(logView.password.getPassword()))) {
                        logView.dispose();
                        EmployerMenu employerView = new EmployerMenu(employerUser.getName());
                        employerView.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Your password is incorrect. Try again");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Login not found. Try again or create an account");
                }
            }

            if (logView.agency_button.isSelected()) {
                AgencyDAOImpl userJ = new AgencyDAOImpl();
                Agency agencyUser = userJ.findAgency(logView.email.getText());
                if (agencyUser != null) {
                    if (agencyUser.getPassword().equals(new String(logView.password.getPassword()))) {
                        logView.dispose();
                        AgencyMenu agencyView = new AgencyMenu(agencyUser.getName());
                        agencyView.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Your password is incorrect. Try again");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Login not found. Try again or create an account");
                }
            }
        }

        //sign up user
        if (ae.getSource() == logView.sign_button) {
            logView.dispose();
            SignUp sign = new SignUp();
            sign.setVisible(true);
        }

    }
}
