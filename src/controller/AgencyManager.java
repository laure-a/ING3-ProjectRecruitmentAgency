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
import view.AgencyMenu;
import view.AgencyRegister;
import view.LogGui;




//action listener pour les boutons du menu agency
public class AgencyManager implements ActionListener {

    private AgencyMenu menuView;
    private final AgencyRegister view;
    private AgencyDAOImpl userA;
    private Agency user;

    public AgencyManager(AgencyMenu menuView) {
        this.menuView = menuView;
        view= null;
        userA = new AgencyDAOImpl();
    }

    public AgencyManager(AgencyRegister view) {
        this.view = view;
        menuView = null;
        userA = new AgencyDAOImpl();
    }
    
    //action performed : ce qu'il se passe quand action lsitener déclanché
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (view != null) {
            if (ae.getSource() == view.create_button) {
                signUp();
            }
        } else {
            user = userA.findAgency(menuView.getUserID());
            
            if (ae.getSource() == menuView.submit_button) {
                
                if (menuView.deleteU_button.isSelected()) {
                    
                    userA.deleteAgency(user);
                    menuView.dispose();
                    JOptionPane.showMessageDialog(null, "Your account has been deleted.");
                    LogGui start = new LogGui();
                    start.setVisible(true);
                }
                
                if (menuView.popularity_button.isSelected()){
                    JOptionPane.showMessageDialog(null, userA.reviewPopularity());
                }
                
            }
        }
    }

    //fonctions utilisées dans action performed
    public void signUp() {

        Agency newUser = new Agency(view.login.getText(), new String(view.password.getPassword()));
        userA.addAgency(newUser);
        JOptionPane.showMessageDialog(null, "Your account has been successfully created.");

        view.dispose();
        menuView = new AgencyMenu(newUser.getName());
        menuView.setVisible(true);

    }

}
