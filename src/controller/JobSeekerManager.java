/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//imports + package
package controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import model.*;
import view.*;




//action listener pour les boutons du menu jobseeker
public class JobSeekerManager implements ActionListener {

    private Guiseeker seekView;
    private final JobSeekerRegister view;
    private final JobSeekerDAOImpl userJ;
    private JobSeeker user;

    public JobSeekerManager(Guiseeker view) {
        seekView = view;
        this.view = null;
        userJ = new JobSeekerDAOImpl();
    }

    public JobSeekerManager(JobSeekerRegister view) {
        this.view = view;
        seekView = null;
        userJ = new JobSeekerDAOImpl();
    }

    //action performed : ce qu'il se passe quand action lsitener déclanché
    @Override
    public void actionPerformed(ActionEvent ae) {

        if (view != null) {
            if (ae.getSource() == view.create_button) {
                signUp();
            }
        } else {
            user = userJ.findJobSeeker(seekView.getUserID());

            if (ae.getSource() == seekView.search_button) {
                browseOffers();
            }

            for (int i = 0; i < seekView.applyButtons.size(); i++) {
                if (ae.getSource() == seekView.applyButtons.get(i)) {
                    JobOfferDAOImpl nv = new JobOfferDAOImpl();
                    JobOffer tempo = nv.findJobOffer(Integer.parseInt(seekView.applyButtons.get(i).getActionCommand()));
                    if (userJ.apply(tempo, user)) {
                        JOptionPane.showMessageDialog(null, "Your application has been registered.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Can't apply to this offer, your profile doesn't match.");
                    }
                }
            }

            if (ae.getSource() == seekView.delete_button) {
                userJ.deleteJobSeeker(user);
                seekView.dispose();
                JOptionPane.showMessageDialog(null, "Your account has been deleted.");
                LogGui start = new LogGui();
                start.setVisible(true);
            }
        }
    }

    
    
    
    
    //fonctions utilisées dans action performed
    public void signUp() {

        Boolean okay = true;
        if ((view.first.getText().isEmpty())
                || (view.last.getText().isEmpty())
                || (view.email.getText().isEmpty())
                || (view.year.getText().isEmpty())
                || (view.phone.getText().isEmpty())) {

            JOptionPane.showMessageDialog(null, "All fields must be filled.");
        } else {
            try {
                Integer.parseInt((String) view.year.getText());
                Double.parseDouble((String) view.phone.getText());
                if (Integer.parseInt((String) view.year.getText()) < 1000) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                okay = false;
                JOptionPane.showMessageDialog(null, "Year or phone value is incorrect.\nMust be valid numbers");
            }
            if (okay) {
                JobSeeker newUser = new JobSeeker(view.first.getText(), view.last.getText(), view.email.getText(), view.bg.getSelection().getActionCommand(),
                        new GregorianCalendar(Integer.parseInt(view.year.getText()), Integer.parseInt((String) view.month.getSelectedItem()),
                                Integer.parseInt((String) view.day.getSelectedItem())), Double.parseDouble(view.phone.getText()),
                        new String(view.password.getPassword()));

                userJ.addJobSeeker(newUser);
                JOptionPane.showMessageDialog(null, "Your account has been successfully created.");

                view.dispose();
                seekView = new Guiseeker(newUser.getEmail());
                seekView.setVisible(true);
            }
        }
    }

    public void browseOffers() {

        String profile = null, duration = null, location = null;
        Date date = null;
        double salary = -1;

        if (seekView.bg.getSelection() != null) {
            profile = seekView.bg.getSelection().getActionCommand();
        }
        if (seekView.bg2.getSelection() != null) {
            duration = seekView.bg2.getSelection().getActionCommand();
        }
        if (!seekView.year.getText().isEmpty()) {
             try {
                Integer.parseInt((String) seekView.year.getText());
                if (Integer.parseInt((String) seekView.year.getText()) < 1000) {
                    throw new NumberFormatException();
                }
                date = Date.valueOf(seekView.year.getText() + "-" + (String) seekView.month.getSelectedItem() + "-" + (String) seekView.day.getSelectedItem());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Year value is incorrect.\nMust be valid number. Ignored.");
            }
        }
        if (seekView.salary.getValue() != 0) {
            salary = seekView.salary.getValue();
        }
        if (!seekView.location.getText().isEmpty()) {
            location = seekView.location.getText();
        }

        ArrayList<JobOffer> offers = userJ.browseOffers(profile, duration, date, salary, location);

        JPanel generalDisp = new JPanel();
        generalDisp.setLayout(new GridLayout(0, 2));
        seekView.scroll.setViewportView(generalDisp);

        for (int i = 0; i < offers.size(); i++) {
            JButton apply;
            apply = new JButton();
            apply.setActionCommand(String.valueOf(offers.get(i).getId()));
            apply.setText("Apply");
            apply.addActionListener(new JobSeekerManager(this.seekView));
            seekView.applyButtons.add(apply);
            JobOffer o = offers.get(i);

            String data = "Title : " + o.getTitle() + "\nEmployer : " + o.getEmployer() + "\nStart date : " + o.getDate().toString()
                    + "\nType of contract: " + o.getDuration() + "\nSalary (total or monthly for long-term contracts): $" + o.getSalary()
                    + "\nLocation: " + o.getLocation() + "\nWhat we're looking for: " + o.getProfile();

            JTextArea offer = new JTextArea(data);
            generalDisp.add(offer);
            generalDisp.add(apply);

        }

        seekView.bg.clearSelection();
        seekView.bg2.clearSelection();

    }

}
