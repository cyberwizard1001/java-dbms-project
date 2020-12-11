package com.company;

import java.sql.*;
import java.util.Scanner;


public class Admin  {
    String name;
    String username;
    String password;
    String type;
    String emp_id = "";
    String dob;
    String doj;
    int salary;


    public String uname = "root";
    public String pw = "n";
    public String url = "jdbc:mysql://localhost:3306/project_trial";

    public void InsertInto(String query) throws SQLException
    {
        int rowsAffected = 0;
        try(
                Connection connection = DriverManager.getConnection(url,uname,pw);

                Statement statement = connection.createStatement();
        )

        {
            rowsAffected = statement.executeUpdate(query);
            System.out.println(rowsAffected+" rows have been inserted");
        }

        catch (SQLException exception)
        {
            exception.printStackTrace();
        }



    }

    public void Update(String query) throws SQLException
    {
        int rowsAffected = 0;
        try(
                Connection connection = DriverManager.getConnection(url,uname,pw);

                Statement statement = connection.createStatement();
        )
        {
            rowsAffected    = statement.executeUpdate(query);
            System.out.println(rowsAffected+ " rows have been updated");

        }

        catch (SQLException exception)
        {
            exception.printStackTrace();
        }

    }

    public Admin(String username, String name) {
        this.username = username;
        this.name = name;
    }

    //checking username existence function:
    public boolean check_username(String uname) {

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
    public void addemployee() throws SQLException {

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
            password = input.next();
            System.out.println("enter type\n1)Water Resource Engineer\n2)System Engineer\n3)Project Engineer");
            user_choice = input.nextInt();
            switch (user_choice) {
                case 1://wre
                    type = "wre";
                    break;

                case 2://sysengg
                    type = "sysadmin";
                    break;

                case 3://prj engg
                    type = "project engineer";
                    break;

                default:
                    System.out.print("Invalid choice");

            }

            k = check_username(username);
//if there is no matching username value  in accounts table,this function assings k with true value
            if (k) {
                String query = "insert into account values('" + username + "','" + name + "','" + password + "','" + type + "')";
                InsertInto(query);

                //now should insert values into employee table
                emp_id = "emp" + username;
                System.out.println("enter date of birth (Format: YYYY-MM-DD ):");
                dob = input.next();
                System.out.println("enter date of join (Format: YYYY-MM-DD ):");
                doj = input.next();
                System.out.println("enter salary");
                salary = input.nextInt();
                String query1 = "insert into employee values('" + username + "','" + emp_id + "','" + name + "','" + salary + "','" + dob + "','" + doj + "')";
                InsertInto(query1);
                System.out.println("Employee details has been successfully added!");
            } else {
                System.out.println("Sorry! Username already exists!");
            }

        }

    }
    //to get no.of connections from public table
 public void updaterelations(String uname,String type ,String lid) {

     String url = "jdbc:mysql://localhost:3306/project_trial";
     String pw = "n";
     String user = "root";

     try (
             Connection connection = DriverManager.getConnection(url, user, pw);
             Statement statement = connection.createStatement();

     ) {


         String query = "select username,no_of_connections from public";
         ResultSet result = statement.executeQuery(query);

         int noc,l,cid=0;
         String un;
         String connection_id;

         int flag=0,flag1=0,flag2=0;
         while (result.next() && flag==0) {
             noc= result.getInt("no_of_connections");
             un=result.getString("username");
             if(un.equals(uname)){

                 noc=noc+1;
                 flag=-1;

                 String query1 = "update public set no_of_connections="+noc+" where username='"+uname+"'";
                 Update(query1);
                 System.out.println("updated in public relation!");

                 String query2 = "select no_of_connections from area where location_id='"+lid+"'";
                 ResultSet result1 = statement.executeQuery(query2);
                 while(result1.next() && flag1==0){
                     l=result.getInt("no_of_connections");
                     l=l+1;
                     flag1=-1;

                     String query3 = "update area set no_of_connections="+l+" where location_id='"+lid+"'";
                     Update(query3);
                     System.out.println("updated in area relation!");
                 }


                 String query4 = "select substring(connection_id,3) AS ExtractString FROM connection order by ExtractString desc;";
                 ResultSet result2 = statement.executeQuery(query4);


                 while(result2.next() && flag2==0){
                     String key=result2.getString("ExtractString");
                     cid=Integer.parseInt(key);
                     flag2=-1;
                 }
                 cid=cid+1;
                 connection_id="CO"+cid;

                 String query5= "insert into public_connection values('" + username + "','" + connection_id + "')";
                 InsertInto(query5);

                 System.out.println("inserted into public connections");

                 String query6= "insert into connection values('"+ connection_id+ "','" + type+ "','"+lid+ "')";
                 InsertInto(query6);

                 System.out.println("inserted into connections");

                 String query7= "update connection_req set status='approved' where username='"+uname+"'";
                 Update(query7);
                 System.out.println("updated in connection_req relation!");


             }
         }
     }

     catch (SQLException e) {
         e.printStackTrace();
     }

 }
//updating public table

    //adding a new connection

    public void addconnection(){
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement();
        ) {
            String query = "select * from connection_req where status='pending'";
            ResultSet result = statement.executeQuery(query);


            while (result.next()) {
                String uname = result.getString("username");
                String type=result.getString("type");
                String lid=result.getString("location_id");
                String sts=result.getString("status");

                updaterelations(uname,type,lid);

            }


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
        System.out.println("(3)Add Connection");
        System.out.println("(4)Sign out");

        System.out.print("\n\nWhat work do you have? : ");
        int work = input.nextInt();

        switch (work) {
            case 1:
                complaint complaint_obj = new complaint();
                complaint_obj.assigncomplaints(username,name);
                break;
            case 2:
                addemployee();
                break;

            case 3:
                addconnection();
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



