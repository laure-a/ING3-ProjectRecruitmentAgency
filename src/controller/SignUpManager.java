/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//imports + package
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.*;




//action listener pour les boutons de l'inscription
public class SignUpManager implements ActionListener {
    
    private final SignUp view;

    public SignUpManager(SignUp view) {
        this.view = view;
    }

    
    //action performed : ce qu'il se passe quand action lsitener déclanché
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == view.continue_button){
            if (view.job_seeker_button.isSelected()){
                view.dispose();
                JobSeekerRegister user = new JobSeekerRegister();
                user.setVisible(true);
            }
            
            if(view.employer_button.isSelected()){
                view.dispose();
                EmployerRegister newUser = new EmployerRegister();
                newUser.setVisible(true);
            }
            
            if(view.agency_button.isSelected()){
                view.dispose();
                AgencyRegister newUser = new AgencyRegister();
                newUser.setVisible(true);
            }
            
        }
    }
    
}
