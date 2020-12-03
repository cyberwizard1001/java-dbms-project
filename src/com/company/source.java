package com.company;
import java.sql.*;

public class source implements river,dam {
    public String s_id;
    String url = "jdbc:mysql://localhost:3306/project_trial";
    String pw = "n";
    String user = "root";

    public source(String s_id) {
        this.s_id = s_id;
    }

    public void source_details(String username,String name) {
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement()
        ) {
            String query1 = "select * from source";
            ResultSet result1 = statement.executeQuery(query1);

            if (!("R".equalsIgnoreCase(String.valueOf(s_id.charAt(0))) || "D".equalsIgnoreCase(String.valueOf(s_id.charAt(0))))){
                System.out.println("Invalid Source ID!");
                ResourceE resource_obj= new ResourceE(username,name);
                resource_obj.Console();
            }

            while (result1.next()) {
                String river_id = result1.getString("source_id");
                if (s_id.compareToIgnoreCase(river_id) == 0) {
                    System.out.println("*** Details on the Water Source ***");
                    System.out.println("Source ID: " + river_id);
                    System.out.println("Source name: " + result1.getString(2));
                }
            }
            if ("R".equalsIgnoreCase(String.valueOf(s_id.charAt(0)))) {
                river_details(username,name);
            } else if ("D".equalsIgnoreCase(String.valueOf(s_id.charAt(0)))) {
                dam_details(username, name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void dam_details(String username,String name) {
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement()
        ) {
            String query2 = "select * from dam";
            ResultSet result2 = statement.executeQuery(query2);
            while (result2.next()) {
                String r_id = result2.getString(1);
                if (s_id.compareToIgnoreCase(r_id) == 0) {
                    System.out.println("Number of Dams: " + result2.getString(2));
                    System.out.println(("River length: " + result2.getString(3)) + "m");
                }
            }
            ResourceE resource_obj= new ResourceE(username,name);
            resource_obj.Console();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void river_details(String username,String name) {
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement()
        ) {
            String query2 = "select * from river";
            ResultSet result2 = statement.executeQuery(query2);
            while (result2.next()) {
                String r_id = result2.getString(1);
                if (s_id.compareToIgnoreCase(r_id) == 0) {
                    System.out.println("Number of Dams: " + result2.getString(2));
                    System.out.println(("River length: " + result2.getString(3)) + "m");
                }
            }
            ResourceE resource_obj= new ResourceE(username,name);
            resource_obj.Console();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}