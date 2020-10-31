package com.company;

import java.sql.*;
import java.util.Scanner;

public class login {

    public void Login() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        //step 1 - create a connection object to connect to the db in question - project]
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);

                //step 2 - create a statement object

                Statement statement = connection.createStatement();
                //task for self - learn how to use prepared statements
        ) {
            String query = "select * from login";
            System.out.println("Statement: " + query);

            //step 3 - create a result object and perform a query
            ResultSet result = statement.executeQuery(query);

            //step 4 - use the result stored in the object for whatever has to be done

            String user_input;
            String pass_input;

            Scanner input = new Scanner(System.in).useDelimiter("\n");
            boolean login = false;

            System.out.print("Enter username: ");
            user_input = input.next();
            System.out.print("Enter password: ");
            pass_input = input.next();

            while (result.next()) {
                String username = result.getString("username");
                String password = result.getString("password");

                if (username.equals(user_input)) {
                    if (password.equals(pass_input)) {
                        System.out.println("You've successfully logged in");
                        login = true;
                        Public obj = new Public();

                        obj.Main();
                    }
                }
            }

            if (!login) {
                System.out.println("Please try again, your credentials are wrong");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
