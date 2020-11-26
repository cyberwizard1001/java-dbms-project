package com.company;

import java.sql.SQLException;
import java.util.Scanner;
import java.sql.*;

public class ResourceE implements employee {
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
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement()
        ) {
            String query1 = "select * from employee";
            ResultSet result = statement.executeQuery(query1);
            while(result.next()){
                String username=result.getString("username");
                String emp_id=result.getString("emp_id");
                if(person.equals(username)){
                    this.emp_id= emp_id;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void Console() throws SQLException {
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        System.out.print("\n");
        System.out.print("Welcome back "+(name)+"!");
        System.out.print("\n");
        System.out.println("(1)Inspect Water Source ");
        System.out.println("(2)View Complaints assigned to me ");
        System.out.println("(3)Get details on Waste-water treatment plants ");
        System.out.println("(4)Sign out ");

        System.out.print("\n\nWhat work do you have? : ");
        int work = input.nextInt();

        switch (work){
            case 1:
                inspection inspect_obj = new inspection();
                try {
                    inspect_obj.inspect(username,name);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

            case 2:
                find_emp_id(username);
                complaint complaint_obj = new complaint();
                try {
                    complaint_obj.assignedtome(emp_id,username,name);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

            case 3:
                treatment_plant plant_obj= new treatment_plant();
                plant_obj.get_details();
                break;

            case 4:
                login login_obj= new login();
                login_obj.Login();
                break;

            default:
                System.out.println("invalid chose!");
            }
        }
}

