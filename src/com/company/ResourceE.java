package com.company;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Scanner;

public class ResourceE {
    String name;
    public ResourceE(String name)
    {
         this.name = name;
        java.sql.Date currentTimestamp = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }

    public void Console()
    {
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        System.out.print("\n");
        System.out.print("Welcome back "+(name)+"!");
        System.out.print("\n");
        System.out.println("(1)Inspect Water Source ");
        System.out.println("(2)View Complaints assigned ");
        System.out.println("(3)Check on Waste-water treatment plants ");
        System.out.println("(4)Back to Home ");

        System.out.print("\n\nWhat work do you have? : ");
        int work = input.nextInt();

        if(work==1){
            inspection inspect_obj = new inspection();
            try {
                inspect_obj.inspect();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
                System.out.println("invalid chose!");
            }
        }
}
