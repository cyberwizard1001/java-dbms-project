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


public class SystemsE {

    String name;



    public SystemsE(String user)
    {
        name = user;

    }

    public void Console() throws SQLException, IOException {
        ASCIIArtService.print();
        System.out.println("Welcome, "+name);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.print("Login time: ");
        System.out.println(dtf.format(now));


        //statuscheck();
        DBadmin();

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

        System.out.println("Do you want to open the Database administration login? ");
        System.out.println("Press y to continue");
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


