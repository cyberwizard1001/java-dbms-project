package com.company;

import java.util.Scanner;

public class Admin{
    public void Console() {
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        System.out.print("\n");
        System.out.print("Welcome back!");
        System.out.print("\n");
        System.out.println("(1)Assign complaints to Engineers");

        System.out.print("\n\nWhat work do you have? : ");
        int work = input.nextInt();

        switch (work){
            case 1:
                complaint complaint_obj = new complaint();
                complaint_obj.assigncomplaints();
                break;

            default:
                System.out.println("invalid chose!");
        }
    }
}


