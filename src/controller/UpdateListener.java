/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//imports + package
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JOptionPane;
import model.Employer;
import model.EmployerDAOImpl;
import model.JobOffer;
import model.JobOfferDAOImpl;
import view.CreateJobOffer;
import view.EmployerMenu;
import view.UpdateOffer;




//action listener pour les boutons du menu update/créer une offre d'emploi
public class UpdateListener implements ActionListener {

    private UpdateOffer view;
    private CreateJobOffer view2;
    private EmployerDAOImpl userE;
    private Employer user;

    public UpdateListener(UpdateOffer view) {
        this.view = view;
        this.view2 = null;
        userE = new EmployerDAOImpl();
    }

    public UpdateListener(CreateJobOffer view) {
        this.view2 = view;
        this.view = null;
        userE = new EmployerDAOImpl();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (view != null) {
            user = userE.findEmployer(view.getUser());
            if (ae.getSource() == view.update_button) {
                view.dispose();
                CreateJobOffer nouv = new CreateJobOffer((String) view.offers_list.getSelectedItem(), user.getName());
                nouv.setVisible(true);
            }
            if (ae.getSource() == view.delete_button) {
                delete();
            }
        } else {
            user = userE.findEmployer(view2.getUser());
            if (ae.getSource() == view2.submit_button) {
                if (view2.getTitleO() != null) {
                    update();
                } else {
                    addJob();
                }
            }
        }

    }
    
    
//fonctions utilisées dans action performed
    public void delete() {

        JobOfferDAOImpl userO = new JobOfferDAOImpl();

        userO.deleteJoboffer(userO.findJobOffer2((String) view.offers_list.getSelectedItem(), user));
        JOptionPane.showMessageDialog(null, "This job offer has been deleted.");
        view.dispose();
        EmployerMenu nouv = new EmployerMenu(user.getName());
        nouv.setVisible(true);
    }

    public void update() {
        JobOfferDAOImpl userO = new JobOfferDAOImpl();
        JobOffer tempo = userO.findJobOffer2(view2.getTitleO(), userE.findEmployer(view2.getUser()));

        if (!view2.title.getText().isEmpty()) {
            userE.updateOffers(tempo, "title", (String) view2.title.getText());
        }
        if (!view2.name.getText().isEmpty()) {
            userE.updateOffers(tempo, "employer", (String) view2.name.getText());
        }
        if (!view2.year.getText().isEmpty()) {
            try {
                Integer.parseInt((String) view2.year.getText());
                if (Integer.parseInt((String) view2.year.getText()) < 1000) {
                    throw new NumberFormatException();
                }
                userE.updateOffers(tempo, "startDate", (String) view2.year.getText() + "-" + (String) view2.month.getSelectedItem() + "-" + (String) view2.day.getSelectedItem());

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Year value is incorrect.");
            }
        }
        String one = (String) view2.contract.getSelectedItem();
        if (!one.equals(tempo.getDuration())) {
            userE.updateOffers(tempo, "duration", (String) view2.contract.getSelectedItem());
        }
        one = (String) view2.profile.getSelectedItem();
        if (!one.equals(tempo.getProfile())) {
            userE.updateOffers(tempo, "candidateProfile", (String) view2.profile.getSelectedItem());
        }
        if (!view2.salary.getText().isEmpty()) {

            try {
                Double.parseDouble((String) view2.salary.getText());
                userE.updateOffers(tempo, "salary", (String) view2.salary.getText());

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Salary value is incorrect.");
            }
        }
        if (!view2.location.getText().isEmpty()) {
            userE.updateOffers(tempo, "location", (String) view2.location.getText());
        }

        JOptionPane.showMessageDialog(null, "Job offer updated.");

        view2.dispose();
        EmployerMenu nouv = new EmployerMenu(user.getName());
        nouv.setVisible(true);

    }

    public void addJob() {
        JobOfferDAOImpl userO = new JobOfferDAOImpl();
        Boolean okay = true;

        if ((view2.title.getText().isEmpty())
                || (view2.name.getText().isEmpty())
                || (view2.year.getText().isEmpty())
                || (view2.salary.getText().isEmpty())
                || (view2.location.getText().isEmpty())) {

            JOptionPane.showMessageDialog(null, "All fields must be filled.");
        } else {
            try {
                Integer.parseInt((String) view2.year.getText());
                Double.parseDouble((String) view2.salary.getText());
                if (Integer.parseInt((String) view2.year.getText()) < 1000) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                okay = false;
                JOptionPane.showMessageDialog(null, "Year or salary value is incorrect.\nMust be valid numbers");
            }

            if (okay) {
                userO.addJoboffer(new JobOffer((String) view2.title.getText(), user.getName(), (String) view2.profile.getSelectedItem(),
                        (String) view2.contract.getSelectedItem(), Date.valueOf((String) view2.year.getText() + "-"
                                + (String) view2.month.getSelectedItem() + "-" + (String) view2.day.getSelectedItem()),
                        Double.parseDouble((String) view2.salary.getText()), view2.location.getText(), 0));

                double total_bill = 0;
                if (user.getMember()) {
                    total_bill = user.getBillMember();
                } else {
                    total_bill = user.getBillNoMember();
                }
                JOptionPane.showMessageDialog(null, "Job offer added. You must pay $" + total_bill);

                view2.dispose();
                EmployerMenu nouv = new EmployerMenu(user.getName());
                nouv.setVisible(true);
            }

        }

    }

}
