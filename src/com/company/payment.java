package com.company;
import java.sql.*;

public class payment extends database {
    String username;
    String transaction_id;

    public payment(String username, String transaction_id) {
        this.username=username;
        this.transaction_id=transaction_id;
    }

    public void getdetails() {

        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement();
            )
        {
            String sql = "select no_of_connections from public " + "where username = '"+username+"'";
            ResultSet result = statement.executeQuery(sql);
            System.out.println("*** Detail 0n number of Connection ***");
            while (result.next()) {
                int noc = result.getInt("no_of_connections");
                System.out.println("Number of Connections : " + noc);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void get_other_details() {
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement();
           )
        {
            String sql1 = "SELECT payment_mode,amt_paid,units_consumed FROM payment " + "WHERE transaction_id = '"+transaction_id+"'";
            ResultSet result1 = statement.executeQuery(sql1);

            while (result1.next()) {

                String mode = result1.getString("payment_mode");
                double amt = result1.getDouble("amt_paid");
                int units = result1.getInt("units_consumed");
                System.out.println("payment mode : " + mode);
                System.out.println("amount paid: " + amt);
                System.out.println("number of units of water consumed (in litres) : " + units);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



}