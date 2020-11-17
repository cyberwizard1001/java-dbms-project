package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class ResourceE {

    public void Console()
    {
          System.out.println("inside wre class");
        String print = "Welcome back!";
        Scanner input = new Scanner(System.in).useDelimiter("\n");



        StringAlignUtils util = new StringAlignUtils(59, StringAlignUtils.Alignment.CENTER);
        System.out.println( "\n\t\t" + util.format(print) );
        System.out.print("\n");

        System.out.println("(1)Inspect Water Source ");
        System.out.println("(2) ");
        System.out.println("(3) ");

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




    }
}
