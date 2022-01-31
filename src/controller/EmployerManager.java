/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//imports + package
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.*;
import view.CreateJobOffer;
import view.EmployerRegister;
import view.EmployerMenu;
import view.LogGui;
import view.UpdateOffer;




//action listener pour les boutons du menu employer
public class EmployerManager implements ActionListener {

    private final EmployerRegister view;
    private EmployerMenu menuView;
    private EmployerDAOImpl userE;
    private Employer user;

    public EmployerManager(EmployerMenu view) {
        menuView = view;
        this.view = null;
        userE = new EmployerDAOImpl();
    }

    public EmployerManager(EmployerRegister view) {
        this.view = view;
        menuView = null;
        userE = new EmployerDAOImpl();
    }
    
    
//action performed : ce qu'il se passe quand action lsitener déclanché
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (view != null) {
            if (ae.getSource() == view.create_button) {
                signUp();
            }

        } else {
            user = userE.findEmployer(menuView.getUserID());

            if (ae.getSource() == menuView.submit_button) {

                if (menuView.deleteU_button.isSelected()) {
                    userE.deleteEmployer(user);
                    menuView.dispose();
                    JOptionPane.showMessageDialog(null, "Your account has been deleted.");
                    LogGui start = new LogGui();
                    start.setVisible(true);
                }

                if (menuView.update_button.isSelected()) {
                    ArrayList<String> list = userE.getOffers(user);
                    if (!list.isEmpty()) {
                        menuView.dispose();
                        UpdateOffer update = new UpdateOffer(user.getName(), list);//pour afficher les offres de notre user dans la combo box
                        update.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "No job offer available currently.");
                    }
                }

                if (menuView.add_button.isSelected()) {
                    menuView.dispose();
                    CreateJobOffer nouv = new CreateJobOffer(user.getName());
                    nouv.setVisible(true);
                }
                
                if(menuView.bill_button.isSelected()){
                    billCalculation();
                }
                
                if(menuView.viewall_button.isSelected()){
                    JOptionPane.showMessageDialog(null, userE.browseCandidates(null));
                }
                
                if(menuView.view_button.isSelected()){
                    JOptionPane.showMessageDialog(null, userE.browseCandidates(user));
                }
            }
        }
    }
    
    
    
    
    //fonctions utilisées dans action performed
    public void signUp() {

        Employer newUser = new Employer(view.login.getText(), new String(view.password.getPassword()), false);
        userE.addEmployer(newUser);
        JOptionPane.showMessageDialog(null, "Your account has been successfully created.");

        view.dispose();
        menuView = new EmployerMenu(newUser.getName());
        menuView.setVisible(true);

    }
    
    public void billCalculation() {
        double total_bill = 0;
        if (user.getMember()) {
            total_bill = user.getBillMember();
            JOptionPane.showMessageDialog(null,"You are a member employer. Each job offer costs you $" + total_bill);
        } else {
            total_bill = user.getBillNoMember();
            JOptionPane.showMessageDialog(null,"You are a new employer. Each job offer costs you $" + total_bill);
        }
    }

}
