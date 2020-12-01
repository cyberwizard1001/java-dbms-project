package com.company;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Public {
    String name;
    String username;

    public Public(String username, String name)
    {
        this.username=username;
        this.name = name;
    }

    public void Console() throws ParseException, SQLException {
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        System.out.print("\n");
        System.out.print("Welcome back "+(name)+"!");
        System.out.print("\n");
        System.out.println("(1)Report Complaint ");
        System.out.println("(2)Sign out ");

        System.out.print("\n\nWhat work do you have? : ");
        int work = input.nextInt();

        switch (work){
            case 1:
                complaint complaint_obj = new complaint();
                complaint_obj.insertcomplaint(username,name);
                break;

            case 2:
                login login_obj= new login();
                login_obj.Login();
                break;

            default:
                System.out.println("invalid chose!");
        }
    }
}



