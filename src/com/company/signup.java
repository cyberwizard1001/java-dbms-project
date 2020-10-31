package com.company;

import java.sql.*;
import java.util.Scanner;

public class signup {

    public void SignUp() throws SQLException {

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

            String print = "Welcome to the Water Management System Registration Portal";

            StringAlignUtils util = new StringAlignUtils(50, StringAlignUtils.Alignment.CENTER);
            System.out.println(util.format(print));
            System.out.print("\n\n\n");

            String user_input = "";
            String password = "";

            int ValidUsername = -1;

            while (ValidUsername == -1) {
                System.out.println("Enter username: ");
                String query = "select username from login";

                ResultSet result = statement.executeQuery(query);

                user_input = input.next();

                ValidUsername = 0;

                while ((result.next()) && (ValidUsername == 0)) {
                    String username = result.getString("username");
                    if (user_input.equals(username)) {
                        System.out.println("Sorry, this username already exists. Try again. ");
                        ValidUsername = -1;
                    } else ValidUsername = 0;
                }

            }

            System.out.println("The username " + user_input + " is available");

            int validPassword = -1;

            while (validPassword == -1) {
                System.out.println("Choose your password: ");

                password = input.next();

                if (password.length() < 8) {
                    System.out.println("Password should have a minimum of 8 characters. Please try again.");
                } else validPassword = 0;

            }

            String query = "insert into login values ('" + user_input + "','" + password + "')";
            System.out.println("Query: " + query);

            int rowsAffected = statement.executeUpdate(query);


            System.out.println(rowsAffected + " user successfully added.");


        }


    }
}

