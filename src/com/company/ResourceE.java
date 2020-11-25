package com.company;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Scanner;

public class ResourceE extends employee{
    String name;
    String username;
    public ResourceE(String username, String name)
    {
        this.username=username;
         this.name = name;
    }

    public void Console()
    {
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        System.out.print("\n");
        System.out.print("Welcome back "+(name)+"!");
        System.out.print("\n");
        System.out.println("(1)Inspect Water Source ");
        System.out.println("(2)View Complaints assigned ");
        System.out.println("(3)Get details on Waste-water treatment plants ");
        System.out.println("(4)Back to Home ");

        System.out.print("\n\nWhat work do you have? : ");
        int work = input.nextInt();

        switch (work){
            case 1:
                inspection inspect_obj = new inspection();
                try {
                    inspect_obj.inspect();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

            case 2:
                String emp_id=getempid(username);
                complaint complaint_obj = new complaint(emp_id);
                complaint_obj.assignedtome();
                break;

            default:
                System.out.println("invalid chose!");
            }
        }
}
