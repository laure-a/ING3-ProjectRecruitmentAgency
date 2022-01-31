package model;

import java.sql.Connection;
import static java.sql.Date.valueOf;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author laure
 */
public class EmployerDAOImpl implements EmployerDAO {

    @Override
    public void addEmployer(Employer employer) {
        Connection dbConnection = null;
        Statement statement = null;

        String sql = "insert into Employer values( '" + employer.getName() + "'," + employer.getMember() + ",'" + employer.getPassword() + "'," + 0 + ");";

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
    public void deleteEmployer(Employer employer) {

        Connection dbConnection = null;
        Statement statement = null;

        String sql = "delete from Employer where name= '" + employer.getName() + "';";

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
    public String browseCandidates(Employer employer) {

        Connection dbConnection = null;
        Statement stmt = null;
        String email = new String();

        try {
            DataSource dataSource = new DataSource();
            dbConnection = dataSource.createConnection();
            stmt = dbConnection.createStatement();
            ResultSet rs;

            if (employer == null) {
                rs = stmt.executeQuery("SELECT email FROM Job_seeker");
            } else {
                rs = stmt.executeQuery("SELECT offerId,candidates FROM Job_offer WHERE employer='" + employer.getName() + "'");
            }

            while (rs.next()) {
                if (employer == null) {
                    email += rs.getString("email") + "\n";
                } else {
                    email += "Job " + rs.getInt("offerId") +" :    "+ rs.getString("candidates") + "\n";
                }
            }
            System.out.println("fonctionne");

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
        
        return email;
    }

    @Override
    public void updateOffers(JobOffer jobOffer, String column, String newValue) {

        Connection dbConnection = null;
        Statement stmt = null;

        try {
            DataSource dataSource = new DataSource();
            dbConnection = dataSource.createConnection();
            stmt = dbConnection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM Job_offer WHERE offerId=" + jobOffer.getId());
            rs.beforeFirst();

            while (rs.next()) {
                if (null != column) {
                    switch (column) {
                        case "salary":
                            rs.updateDouble(column, Double.parseDouble(newValue));
                            break;
                        case "startDate":
                            rs.updateDate(column, valueOf(newValue));
                            break;
                        default:
                            rs.updateString(column, newValue);
                            break;
                    }

                    rs.updateRow();
                }
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
    public Employer findEmployer(String login) {
        Connection dbConnection = null;
        Statement stmt = null;
        Employer employer = null;

        try {
            DataSource dataSource = new DataSource();
            dbConnection = dataSource.createConnection();
            stmt = dbConnection.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM Employer WHERE name='" + login + "'");

            while (rs.next()) {
                employer = new Employer(rs.getString("name"), rs.getString("password"), rs.getBoolean("member"));

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
        return employer;
    }
    
    
    @Override
    public ArrayList<String> getOffers(Employer employer) {

        Connection dbConnection = null;
        Statement stmt = null;
        ArrayList<String> offers=new ArrayList<>();

        try {
            DataSource dataSource = new DataSource();
            dbConnection = dataSource.createConnection();
            stmt = dbConnection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs;
            rs = stmt.executeQuery("SELECT title, offerId FROM Job_offer WHERE employer='" + employer.getName()+"'");
            
            rs.beforeFirst();

            while (rs.next()) {
                offers.add(rs.getString("title"));
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
}
