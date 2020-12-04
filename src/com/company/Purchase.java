package com.company;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Purchase extends database implements employee {

    String username;
    String emp_id;
    String purchase_id;
    String procurement_date;
    String issue_date;
    String status;

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void Console() throws IOException, SQLException {
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        System.out.println("\n\nPurchase Management System\n");
        System.out.println("Choose your operation: ");
        System.out.println("1. Make purchase");
        System.out.println("2. Check purchase status ");
        System.out.println("3. Back");

        int choice;
        System.out.print("Choice: ");
        choice = input.nextInt();
        System.out.print("\n\n");

        if(choice==1)
        {
            make_request();
        }

        else if(choice==2)
        {
            view_status();
        }

        else{
            SystemsE obj = new SystemsE(emp_id,username);
            obj.Console();
        }
    }

    public Purchase(String username) {
        this.username = username;
    }

    public void find_emp_id(String username) {
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        //step 1 - create a connection object to connect to the db in question - project]
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);

                //step 2 - create a statement object

                Statement statement = connection.createStatement();

        ) {

            String query = "select username,emp_id from employee";
            System.out.println("Statement: " + query);

            //step 3 - create a result object and perform a query
            ResultSet result = statement.executeQuery(query);

            //step 4 - use the result stored in the object for whatever has to be done

            while (result.next()) {
                String uname = result.getString("emp_id");

                if (uname.equals(username)) {
                    emp_id = result.getString("emp_id");
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public void make_request() throws SQLException, IOException {

        ASCIIArtService.print();
        String print = "Purchase request";
        StringAlignUtils util = new StringAlignUtils(59, StringAlignUtils.Alignment.CENTER);
        System.out.println("\n\t\t" + util.format(print));
        System.out.print("\n");

        Scanner input = new Scanner(System.in).useDelimiter("\n");

        find_emp_id(username);
        System.out.println("Welcome, " + username + "(" + emp_id + ")");

        System.out.print("Enter purchase ID: ");
        purchase_id = input.next();

        System.out.println("Do you want to continue requesting for this purchase? (y/n) ");
        char choice;
        choice = input.next().charAt(0);

        if (choice == 'y') {
            status = "pending";
            String query = "insert into purchase values ('" + purchase_id + "','" + emp_id + "',NULL,NULL,'" + status + "')";
            database obj = new database();
            obj.InsertInto(query);
        } else {
            System.out.println("Request cancelled.");
        }


        System.out.println("Press any key to continue.");
        System.in.read();
    }


    public void view_status() {

        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        //step 1 - create a connection object to connect to the db in question - project]
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);

                //step 2 - create a statement object

                Statement statement = connection.createStatement();

        ) {

            String query = "select * from purchase";
            System.out.println("Statement: " + query);

            //step 3 - create a result object and perform a query
            ResultSet result = statement.executeQuery(query);

            //step 4 - use the result stored in the object for whatever has to be done

            while (result.next()) {

                String emp_id = result.getString("emp_id");
                String purchase_id = result.getString("purchase_id");
                String issue_date = result.getString("issue_date");
                String status = result.getString("status");

                System.out.println("Purchase ID: " + purchase_id);
                System.out.println("Employee ID: " + emp_id);


                if(status.equals("approved"))
                {
                    System.out.println("Status: " + ANSI_GREEN + status + ANSI_RESET);
                    System.out.println("Procurement Date: " + procurement_date);
                    System.out.println("Issue Date: " + issue_date);
                }

                else if (status.equals("pending"))
                {
                    System.out.println("Status: " + ANSI_YELLOW + status + ANSI_RESET);
                }

                else{
                    System.out.println("Status: " + ANSI_RED + status + ANSI_RESET);
                }

                System.out.println("\n");

                System.out.print("Press any key to continue");
                System.in.read();

                Console();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}