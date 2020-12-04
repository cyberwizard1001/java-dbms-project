package com.company;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.*;

public class Finance implements employee{
    String name;
    String username;
    String fin_emp_id;

    public void find_emp_id(String username)
    {
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        //step 1 - create a connection object to connect to the db in question - project]
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);

                //step 2 - create a statement object

                Statement statement = connection.createStatement();

        ) {

            String query = "select username, emp_id from employee";
            System.out.println("Statement: " + query);

            //step 3 - create a result object and perform a query
            ResultSet result = statement.executeQuery(query);

            //step 4 - use the result stored in the object for whatever has to be done

            while(result.next())
            {
                String uname = result.getString("username");

                if(uname.equals(username))
                {
                    fin_emp_id = result.getString("emp_id");
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }


    public  Finance(String username,String name){
        this.username=username;
        this.name = name;
    }


    public void Console() throws SQLException, IOException {
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        System.out.print("\n");
        System.out.println("Finance Module");
        System.out.println("Welcome back "+(name)+"!\n");

        System.out.println("(1)Approve purchases ");
        System.out.println("(2)Generate Report");
        System.out.println("(3)Sign out ");

        System.out.print("\n\nChoose an option: ");
        int work = input.nextInt();

        switch (work){
          case 1:
                approve_purchase();
                break;

          case 2:
                generate_report();
                break;

            case 3:
                login login_obj= new login();
                login_obj.Login();
                break;

            default:
                System.out.println("Invalid choice. Please try again.\nPress any key to continue.");
                System.in.read();
                Console();
                break;
        }

    }

    private void generate_report() {
    }

    private void approve_purchase() {

        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        //step 1 - create a connection object to connect to the db in question - project]
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);

                //step 2 - create a statement object

                Statement statement = connection.createStatement();

        ) {

            String query = "select * from purchase where status = 'pending'";
            System.out.println("Statement: " + query);

            //step 3 - create a result object and perform a query
            ResultSet result = statement.executeQuery(query);

            //step 4 - use the result stored in the object for whatever has to be done

            while(result.next())
            {
                String uname = result.getString("username");

                if(uname.equals(username))
                {
                    fin_emp_id = result.getString("emp_id");
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
