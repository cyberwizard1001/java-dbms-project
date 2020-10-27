package com.company;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class signup extends database {

    public void SignUp() throws SQLException {

        Scanner input = new Scanner(System.in).useDelimiter("\n");

        String print = "Welcome to the Water Management System Registration Portal";

        StringAlignUtils util = new StringAlignUtils(50, StringAlignUtils.Alignment.CENTER);
        System.out.println( util.format(print) );
        System.out.print("\n\n\n");

        String user_input = "";
        String password = "";

        int ValidUsername = -1;

        while(ValidUsername==-1)
        {
            System.out.println("Enter username: ");
            String query = "select username from login";

            ResultSet result = Query(query);
            System.out.println(result.getString("username"));

            user_input = input.next();

            ValidUsername = 0;

            while((result.next())&&(ValidUsername==0))
            {
                String username = result.getString("username");
                if(user_input.equals(username))
                {
                    System.out.println("Sorry, this username already exists. Try again. ");
                    ValidUsername = -1;
                }

                else ValidUsername = 0;
            }

        }

        System.out.println("The username "+user_input+ " is available");

        int validPassword = -1;

        while(validPassword==-1)
        {
            System.out.println("Choose your password: ");

            password = input.next();

            if(password.length()<8)
            {
                System.out.println("Password should have a minimum of 8 characters. Please try again.");
            }

            else validPassword = 0;

        }

        String query = "insert into login values ('"+user_input+"','"+password+"')";
    System.out.println("Query: "+query);

    InsertInto(query);

    System.out.println("User successfully added.");


    }


}

