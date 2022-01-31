/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author laure
 */
public class AgencyDAOImpl implements AgencyDAO {

    @Override
    public void addAgency(Agency agency) {

        Connection dbConnection = null;
        Statement statement = null;

        String sql = "insert into Agency values ( '" + agency.getName() + "', '" + agency.getPassword() + "');";

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
    public void deleteAgency(Agency agency) {

        Connection dbConnection = null;
        Statement statement = null;

        String sql = "delete from Agency where name= '" + agency.getName() + "';";

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
    public String reviewPopularity() {
        Connection dbConnection = null;
        Statement stmt = null;
        String popularity = new String();
        int previous = 0;

        try {
            DataSource dataSource = new DataSource();
            dbConnection = dataSource.createConnection();
            stmt = dbConnection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs;
            rs = stmt.executeQuery("SELECT candidates, offerId, employer, totalPosts FROM Job_offer o, Employer e WHERE o.employer=e.name");

            rs.beforeFirst();

            while (rs.next()) {
                String list = rs.getString("candidates");
                if (list!=null) {
                    int count = (list.split("/", -1).length);
                    if (count >= previous) {
                        previous = count;
                        popularity = "The employer that has the biggest number of candidates on a job \noffer is " + rs.getString("employer")
                                + " with " + count + " candidates. \nThey have a total of " + rs.getInt("totalPosts") + " posts for now.";
                    }
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
        return popularity;
    }

    @Override
    public Agency findAgency(String login) {
        Connection dbConnection = null;
        Statement stmt = null;
        Agency agency = null;

        try {
            DataSource dataSource = new DataSource();
            dbConnection = dataSource.createConnection();
            stmt = dbConnection.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM Agency WHERE name='" + login + "'");

            while (rs.next()) {
                agency = new Agency(rs.getString("name"), rs.getString("password"));

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
        return agency;
    }
}
