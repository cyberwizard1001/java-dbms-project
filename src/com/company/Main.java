package com.company;


import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        String print = "Welcome to the Water Management System Console. Choose the function you want to perform";

        Scanner input = new Scanner(System.in).useDelimiter("\n");

        StringAlignUtils util = new StringAlignUtils(50, StringAlignUtils.Alignment.CENTER);
        System.out.println( util.format(print) );
        System.out.print("\n\n\n");

        System.out.println("Existing user? Press 1 to login to your account.");
        System.out.println("New user? Press 2 to sign up for an account");
        System.out.println("Here for data? Press 3 to view public data");

        int choice = input.nextInt();

        switch (choice)
        {
            case 1 : {
                login obj = new login();
                obj.Login();
                break;
            }

            case 2:{
                signup obj = new signup();
                obj.SignUp();
                break;
            }
        }

    }
}
