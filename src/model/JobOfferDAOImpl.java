/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author laure
 */
public class JobOfferDAOImpl implements JobOfferDAO {

    @Override
    public void addJoboffer(JobOffer joboffer) {

        Connection dbConnection = null;
        Statement statement = null;

        String sql = "insert into Job_Offer (title, employer, candidateProfile,duration,salary,startDate,location) values ( '"
                + joboffer.getTitle() + "', '" + joboffer.getEmployer() + "', '" + joboffer.getProfile() + "', '" + joboffer.getDuration() + "',"
                + joboffer.getSalary() + ",'" + joboffer.getDate() + "', '" + joboffer.getLocation() + "');";

        try {
            DataSource dataSource = new DataSource();
            dbConnection = dataSource.createConnection();
            statement = dbConnection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs;
            rs = statement.executeQuery("SELECT name, totalPosts, member FROM Employer WHERE name='" + joboffer.getEmployer()+"'");
            rs.beforeFirst();
            

            while (rs.next()) {
                int total = rs.getInt("totalPosts");
              
                total += 1;
                if (total == 20){
                    rs.updateBoolean("member", true);
                }
                rs.updateInt("totalPosts", total);
                rs.updateRow();
                
            }
            statement.executeUpdate(sql);

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
    public void deleteJoboffer(JobOffer joboffer) {

        Connection dbConnection = null;
        Statement statement = null;

        String sql = "delete from Job_Offer where offerId= '" + joboffer.getId() + "';";

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
    public void addCandidate(JobSeeker jobseeker, JobOffer joboffer) {
        Connection dbConnection = null;
        Statement stmt = null;

        try {
            DataSource dataSource = new DataSource();
            dbConnection = dataSource.createConnection();
            stmt = dbConnection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs;
            rs = stmt.executeQuery("SELECT offerId, candidates FROM Job_offer WHERE offerId=" + joboffer.getId());

            rs.beforeFirst();

            while (rs.next()) {
                String list = rs.getString("candidates");
                list += "/" + jobseeker.getEmail();

                rs.updateString("candidates", list);
                rs.updateRow();
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
    }
    
    
    @Override
    public JobOffer findJobOffer(int id) {
        Connection dbConnection = null;
        Statement stmt = null;
        JobOffer offer = null;

        try {
            DataSource dataSource = new DataSource();
            dbConnection = dataSource.createConnection();
            stmt = dbConnection.createStatement();
            ResultSet fd;

            fd = stmt.executeQuery("SELECT * FROM Job_offer WHERE offerId=" + id );

            while (fd.next()) {
                offer = new JobOffer(fd.getString("title"), fd.getString("employer"), fd.getString("candidateProfile"),
                        fd.getString("duration"), fd.getDate("startDate"),fd.getDouble("salary"),
                        fd.getString("location"), fd.getInt("offerId"));

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
        return offer;
    }
    
    @Override
    public JobOffer findJobOffer2(String title, Employer employer) {
        Connection dbConnection = null;
        Statement stmt = null;
        JobOffer offer = null;

        try {
            DataSource dataSource = new DataSource();
            dbConnection = dataSource.createConnection();
            stmt = dbConnection.createStatement();
            ResultSet fd;
            

            fd = stmt.executeQuery("SELECT * FROM Job_offer WHERE title='" + title + "' and employer='"+ employer.getName()+"'");
            
            while (fd.next()) {
                offer = new JobOffer(fd.getString("title"), fd.getString("employer"), fd.getString("candidateProfile"),
                        fd.getString("duration"), fd.getDate("startDate"),fd.getDouble("salary"),
                        fd.getString("location"), fd.getInt("offerId"));

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
        return offer;
    }

}
