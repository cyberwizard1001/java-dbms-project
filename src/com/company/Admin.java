package com.company;

import java.sql.*;
import java.util.Scanner;


public class Admin extends database {
    String name;
    String username;
    String password;
    String type;
    String emp_id="";
    String dob;
    String doj;
    int salary;


    public Admin(String username, String name) {
        this.username = username;
        this.name = name;
    }

    //checking username existence function:
    public boolean check_username(String uname)
    {

        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        try (
                Connection connection = DriverManager.getConnection(url, user, pw);


                Statement statement = connection.createStatement();

        ) {
            String query = "select username from account";

            Scanner input = new Scanner(System.in).useDelimiter("\n");
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String username = result.getString("username");
                if (uname.equals(username)) {
                    return false;
                }
            }
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }



    //adding employee function:
    public void addemp() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement();

        ) {

            int user_choice;
            boolean k;
            Scanner input = new Scanner(System.in).useDelimiter("\n");
            System.out.println("enter username");
            username = input.next();
            System.out.println("enter employee name");
            name = input.next();
            System.out.println("enter password");
            password=input.next();
            System.out.println("enter type");
            user_choice=input.nextInt();
            switch(user_choice){
                case 1://wre
                    type="wre";
                    break;

                case 2://sysengg
                    type="sysadmin";
                    break;

                case 3://prj engg
                    type="project engineer";
                    break;

                default:
                    System.out.print("Invalid choice");

            }

            k=check_username(username);
//if there is no matching username value  in accounts table,this function assings k with true value
            if(k){
                String query = "insert into account values('" + username + "','" + name + "','"+ password + "','"+type+"')";
                InsertInto(query);

                //now should insert values into employee table
                emp_id="emp"+username;
                System.out.println("enter date of birth");
                dob=input.next();
                System.out.println("enter date of join");
                doj=input.next();
                System.out.println("enter salary");
                salary=input.nextInt();
                String query1="insert into employee values('" + username + "','" + emp_id + "','" + name + "','"+ salary + "','"+ dob +"','"+ doj +"')";
                InsertInto(query1);

            }
            else{
                System.out.println("Sorry! Username already exis1ts!");
            }

        }

    }

    //adding a new connection

    public void addconnec(){
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement();
        ) {
            String query = "select * from Connection_req where status='pending'";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String resultset = result.getString("username");
                if (uname.equals(username)) {
                    return ;
                }
            }
            return ;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    public void Console() throws SQLException {

        Scanner input = new Scanner(System.in).useDelimiter("\n");
        System.out.print("\n");
        System.out.print("Welcome back " + (name) + "!");
        System.out.print("\n");
        System.out.println("(1)Assign complaints to Engineers");
        System.out.println("(2)Add Employee");
        //System.out.println("(3)Remove Employee");
        System.out.println("(4)Add Connection");
        System.out.println("(5)Remove Connection");
        System.out.println("(6)Sign out");

        System.out.print("\n\nWhat work do you have? : ");
        int work = input.nextInt();

        switch (work) {
            case 1:
                complaint complaint_obj = new complaint();
                complaint_obj.assigncomplaints(username,name);
                break;
            case 2:
                addemp();

                break;
            case 3:
                break;
            case 4:
                //addconnec();
                break;

            default:
                System.out.println("invalid chose!");
        }
    }
}



