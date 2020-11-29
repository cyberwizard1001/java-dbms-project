package com.company;

import java.sql.SQLException;
import java.util.Scanner;
import java.sql.*;

public class Finance {
    String name;
    String username;

    public  Finance(String username,String name){
        this.username=username;
        this.name = name;
    }
    public void Console() throws SQLException {
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        System.out.print("\n");
        System.out.print("Welcome back "+(name)+"!");
        System.out.print("\n");
        System.out.println("(1)Allot Approval ");
        System.out.println("(2)Generate Report");
        System.out.println("(3)Sign out ");

        System.out.print("\n\nWhat work do you have? : ");
        int work = input.nextInt();

        switch (work){
            case 1:
                break;

            case 2:
                break;

            case 3:
                login login_obj= new login();
                login_obj.Login();
                break;

            default:
                System.out.println("invalid chose!");
        }

    }
}
