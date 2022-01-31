/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author laure
 */
public class JobSeekerDAOImpl implements JobSeekerDAO {

    @Override
    public void addJobSeeker(JobSeeker jobseeker) {

        Connection dbConnection = null;
        Statement statement = null;

        String sql = "insert into Job_seeker values( '" + jobseeker.getLastName() + "', '" + jobseeker.getFirstName()
                + "', '" + jobseeker.getEmail() + "', '" + jobseeker.getProfile() + "', '" + jobseeker.getDate().get(Calendar.YEAR)
                + "/" + jobseeker.getDate().get(Calendar.MONTH) + "/" + jobseeker.getDate().get(Calendar.DATE) + "', " + jobseeker.getPhone()
                + ", '" + jobseeker.getPassword() + "');";

        try {
            DataSource dataSource = new DataSource();
            dbConnection = dataSource.createConnection();
            statement = dbConnection.createStatement();

            statement.executeUpdate(sql);

            System.out.println("fonctionne");

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    @Override
    public void deleteJobSeeker(JobSeeker jobseeker) {

        Connection dbConnection = null;
        Statement statement = null;

        String sql = "delete from Job_seeker where email= '" + jobseeker.getEmail() + "';";

        try {
            DataSource dataSource = new DataSource();
            dbConnection = dataSource.createConnection();
            statement = dbConnection.createStatement();

            statement.executeUpdate(sql);

            System.out.println("fonctionne");

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public ArrayList<JobOffer> browseOffers(String profile, String duration, Date date, double salary, String location) {

        Connection dbConnection = null;
        Statement stmt = null;
        ArrayList<JobOffer> offers = new ArrayList<>();

        try {
            DataSource dataSource = new DataSource();
            dbConnection = dataSource.createConnection();
            stmt = dbConnection.createStatement();
            ResultSet fd;

            String query = "";
            if (profile != null || duration != null || date != null || salary != -1 || location != null) {
                query += " WHERE ";
            }

            if (profile != null) {
                query += "candidateProfile = '" + profile + "'";
                if (duration != null || date != null || salary != -1 || location != null) {
                    query += " and ";
                }
            }
            if (duration != null) {
                query += "duration='" + duration + "'";
                if (date != null || salary != -1 || location != null) {
                    query += " and ";
                }
            }
            if (date != null) {
                query += "startDate='" + date + "'";
                if (salary != -1 || location != null) {
                    query += " and ";
                }
            }
            if (salary != -1) {
                query += "salary=" + salary;
                if (location != null) {
                    query += " and ";
                }
            }
            if (location != null) {
                query += "location='" + location + "'";
            }

            fd = stmt.executeQuery("SELECT * FROM Job_offer" + query);

            while (fd.next()) {
                JobOffer tempo = new JobOffer(fd.getString("title"), fd.getString("employer"), fd.getString("candidateProfile"),
                        fd.getString("duration"), fd.getDate("startDate"),fd.getDouble("salary"),
                        fd.getString("location"), fd.getInt("offerId"));
                
                offers.add(tempo);
            }
            
            

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return offers;
    }

    @Override
    public boolean apply(JobOffer offer, JobSeeker jobseeker) {
        boolean done=false;
        if (jobseeker.getProfile().equals(offer.getProfile())) {
            JobOfferDAOImpl cand = new JobOfferDAOImpl();
            cand.addCandidate(jobseeker, offer);
            done=true;
        }
        return done;
    }

    
    @Override
    public JobSeeker findJobSeeker(String login) {
        Connection dbConnection = null;
        Statement stmt = null;
        JobSeeker jobSeeker = null;

        try {
            DataSource dataSource = new DataSource();
            dbConnection = dataSource.createConnection();
            stmt = dbConnection.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM Job_seeker WHERE email='" + login + "'");

            while (rs.next()) {
                jobSeeker = new JobSeeker(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"),
                        rs.getString("profile"), new GregorianCalendar(rs.getDate("birthDate").getYear(),
                                rs.getDate("birthDate").getMonth(), rs.getDate("birthDate").getDate()),
                        rs.getDouble("phoneNumber"), rs.getString("password"));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return jobSeeker;
    }
}
