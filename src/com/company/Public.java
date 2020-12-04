package com.company;

import java.util.Scanner;

public class Public {
    String name;
    String username;

    public Public(String username, String name)
    {
        this.username=username;
        this.name = name;
    }

    public void Console()  {
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        System.out.print("\n");
        System.out.print("Welcome back "+(name)+"!");
        System.out.print("\n");
        System.out.println("(1)Report Complaint ");

        System.out.print("\n\nWhat work do you have? : ");
        int work = input.nextInt();

        switch (work){
            case 1:
                complaint complaint_obj = new complaint();
                complaint_obj.report_complaint(username,name);
                break;


            default:
                System.out.println("invalid chose!");
        }
    }
}



