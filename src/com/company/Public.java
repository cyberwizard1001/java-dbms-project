package com.company;

import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;

public class Public {
    String name;
    String username;
    public Public(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public void update_address(String t, String q ,String p) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement pstmt = connection.createStatement())

        {
            String sqlUpdate = "update public set door_num = '"+t+ "' , street = '"+q+ "' ,locality = '"+p+ "' where username = '"+username+"'";
            System.out.println(sqlUpdate);
            pstmt.executeUpdate(sqlUpdate);
            System.out.println("Updated successfully!");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void Console() throws SQLException, ParseException {
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        System.out.print("\n");
        System.out.print("Welcome back!");
        System.out.print("\n");
        System.out.println("(1)Report Complaint ");
        System.out.println("(2)Update Address ");

        System.out.print("\n\nWhat work do you have? : ");
        int work = input.nextInt();

        switch(work){

            case 1:
                complaint complaint_obj = new complaint();
                complaint_obj.report_complaint(username, name);
                break;

            case 2:
                System.out.println("Enter new door no: ");
                String t= input.next();
                System.out.println("Enter new street name: ");
                String q= input.next();
                System.out.println("Enter new locality: ");
                String p= input.next();
                update_address(t,q,p);
                break;

            default:
            System.out.println("invalid chose!");
        }
    }
}



