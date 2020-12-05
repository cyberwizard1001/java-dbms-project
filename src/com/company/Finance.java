package com.company;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.sql.*;

import static com.company.Purchase.*;

public class Finance extends database implements employee{
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
        ASCIIArtService.print();
        find_emp_id(username);
        System.out.println("Welcome, "+name);
        System.out.println("Emp ID: " + fin_emp_id);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.print("Login time: ");
        System.out.println(dtf.format(now));

        System.out.println("(1)Approve purchases ");
        System.out.println("(2)Generate Report");
        System.out.println("(3)View past purchases");
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
                view_status();
                break;

            case 4:
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

            Scanner input = new Scanner(System.in).useDelimiter("\n");
            String query = "select * from purchase where status = 'pending'";
            System.out.println("Statement: " + query + "\n");

            //step 3 - create a result object and perform a query
            ResultSet result = statement.executeQuery(query);

            //step 4 - use the result stored in the object for whatever has to be done

            while(result.next()) {

                String purchase_id = result.getString("purchase_id");
                String application_date = result.getString("application_date");
                String emp_id = result.getString("emp_id");

                int count = 1;

                System.out.println("Purchase " + count + ": ");
                System.out.println("Purchase ID: " + purchase_id);
                System.out.println("Application date: " + application_date);
                System.out.println("Employee ID: " + emp_id);

                System.out.print("Options:\n\t(1)Approve purchase\n\t(2)Decline purchase\n\t(3)Keep pending\nPlease choose action: ");

                int choice = input.nextInt();

                if(choice==1)
                {
                    String Query = "update purchase set status = 'approved' where purchase_id = '" + purchase_id + "'";
                    Update(Query);
                    System.out.println("This purchase has been approved. \n");
                }

                else if(choice==2)
                {
                    String Query = "update purchase set status = 'rejected' where purchase_id = '" + purchase_id + "'";
                    Update(Query);
                    System.out.println("This purchase has been rejected. \n");
                }

                else
                {
                    System.out.println("This purchase is still pending.\n");
                }

                count++;
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

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
                String application_date = result.getString("application_date");
                String status = result.getString("status");

                if (status.equals("pending"))
                {
                    continue;
                }

                System.out.println("Purchase ID: " + purchase_id);
                System.out.println("Employee ID: " + emp_id);


                if(status.equals("approved"))
                {
                    System.out.println("Status: " + ANSI_GREEN + status + ANSI_RESET);
                    System.out.println("Application Date: " + application_date);
                    System.out.println("Issue Date: " + issue_date);
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
