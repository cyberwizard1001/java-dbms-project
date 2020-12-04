package com.company;



import java.awt.*;
import java.net.URI;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class SystemsE implements employee {

    String name;
    String username;
    String emp_id;

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
                    emp_id = result.getString("emp_id");
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public SystemsE(String user, String username)
    {
        name = user;
        this.username = username;

    }

    public void Console() throws SQLException, IOException {
        ASCIIArtService.print();
        find_emp_id(username);
        System.out.println("Welcome, "+name);
        System.out.println("Emp ID: " + emp_id);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.print("Login time: ");
        System.out.println(dtf.format(now));

        Scanner input = new Scanner(System.in).useDelimiter("\n");

        System.out.println("Choose your operation: ");
        System.out.println("1. Check system status");
        System.out.println("2. Open database management (Username: nirmalk Password: nirmal1503)");
        System.out.println("3. Open Inventory Management");
        System.out.println("4. Open Purchase Management");

        int choice;
        System.out.print("Choice: ");
        choice = input.nextInt();

        switch (choice)
        {
            case 1: statuscheck();
            break;

            case 2: DBadmin();
            break;

            case 3: {
                Inventory obj = new Inventory();
                obj.Console();
                break;
            }

            case 4: {
                Purchase obj = new Purchase(username);
                obj.Console();
                break;
            }

            default:{
                System.out.println("Invalid input. Please try again. ");
                Console();
                break;
            }
        }

        Inventory obj = new Inventory();
        obj.Console();

    }

    private void statuscheck() throws IOException, SQLException {

        String result = "";
        try {
            URL urlObj = new URL("http://localhost/login.php");
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            con.setRequestMethod("GET");
            // Set connection timeout
            con.setConnectTimeout(3000);
            con.connect();
            System.out.println("\n\nWeb Server Status Check: ");
            int code = con.getResponseCode();
            System.out.println("HTTP Status code: " + code);
            if (code == 200) {
                result = "up";
            }
        } catch (Exception e) {
            result = "down";
        }
        System.out.println("The web server is " + result);

        boolean dbstatus = DBstatus();

        if(dbstatus)
        {
            System.out.println("The Database is healthy and available.");
        }

        else{
            System.out.println("The Database is not available. Please check configuration.");
        }


        System.out.println("Press any key to go back to Console. ");
        System.in.read();

        Console();
    }

    private boolean DBstatus() throws SQLException
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


            return true;


        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

    }

    public void DBadmin() throws IOException
    {

        System.out.println("Do you want to open the Database Administration login? ");
        System.out.print("Press y to continue: ");
        Scanner input = new Scanner(System.in).useDelimiter("\n");

        char option = input.next().charAt(0);

        if(option=='y'||option=='Y'){
            try {
                Desktop desktop = java.awt.Desktop.getDesktop();
                URI oURL = new URI("http://localhost/login.php");
                desktop.browse(oURL);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        else{
            System.out.println("Press any key to return to Console. ");
            System.in.read();
        }
    }
}


