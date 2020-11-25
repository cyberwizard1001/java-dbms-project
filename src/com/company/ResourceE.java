package com.company;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Scanner;
import java.sql.*;

public class ResourceE implements Employee{
    String name;
    String username;
    String emp_id;
    public ResourceE(String username, String name)
    {
        this.username=username;
        this.name = name;
        emp_id=" ";
    }
    public void find_emp_id(String person){
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";
        System.out.println("hi!");
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement()
        ) {
            String query1 = "select * from employee";
            ResultSet result = statement.executeQuery(query1);
            while(result.next()){
                System.out.println("hi!");
                String username=result.getString("username");
                String emp_id=result.getString("emp_id");
                if(person.equals(username)){
                    this.emp_id= emp_id;
                    System.out.println("hi!");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void Console()
    {
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        System.out.print("\n");
        System.out.print("Welcome back "+(name)+"!");
        System.out.print("\n");
        System.out.println("(1)Inspect Water Source ");
        System.out.println("(2)View Complaints assigned to me ");
        System.out.println("(3)Get details on Waste-water treatment plants ");
        System.out.println("(4)Back to Home ");

        System.out.print("\n\nWhat work do you have? : ");
        int work = input.nextInt();

        switch (work){
            case 1:
                inspection inspect_obj = new inspection();
                try {
                    inspect_obj.inspect();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

            case 2:
                find_emp_id(username);
                System.out.println("hi22!");
                complaint complaint_obj = new complaint();
                try {
                    complaint_obj.assignedtome(emp_id);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

            default:
                System.out.println("invalid chose!");
            }
        }
}

