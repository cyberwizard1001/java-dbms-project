package com.company;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
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

            ASCIIArtService.print();
            String user_input = "";
            String password = "";
            String name;

            int ValidUsername = -1;

            while (ValidUsername == -1) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.print("Enter username: ");
                String query = "select username from account";

                ResultSet result = statement.executeQuery(query);

                user_input = input.next();

                ValidUsername = 0;

                while ((result.next()) && (ValidUsername == 0)) {
                    String username = result.getString("username");
                    if (user_input.equals(username)) {
                        System.out.println("\nSorry, this username already exists. Try again. ");
                        ValidUsername = -1;
                    } else ValidUsername = 0;
                }

            }

            System.out.println("The username " + user_input + " is available");
            System.out.print("Name: ");
            name = input.next();

            int validPassword = -1;

            while (validPassword == -1) {
                System.out.print("Choose your password: ");

                password = input.next();

                if (password.length() < 8) {
                    System.out.println("Password should have a minimum of 8 characters. Please try again.");
                } else validPassword = 0;

            }

            String query = "insert into account (username,password,name,type) values ('" + user_input + "','" + password +"','"+ name + "','public')";
            System.out.print("Query: " + query);

            int rowsAffected = statement.executeUpdate(query);


            System.out.println(rowsAffected + " user successfully added.");
            System.out.print("Press ENTER to continue: ");

            System.in.read();

            Main.main(null);


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


    }
}

