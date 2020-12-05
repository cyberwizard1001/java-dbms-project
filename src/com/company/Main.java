package com.company;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Main  extends ASCIIArtService {

    public static void main(String[] args) throws SQLException, IOException, ParseException {

        ASCIIArtService.print();
        String print = "Welcome to the Water Management System Console. Choose the         option relating to what you want to do";

        Scanner input = new Scanner(System.in).useDelimiter("\n");



        StringAlignUtils util = new StringAlignUtils(59, StringAlignUtils.Alignment.CENTER);
        System.out.println( "\n\t\t" + util.format(print) );
        System.out.print("\n");

        System.out.println("* Existing user? Press 1 to login to your account.");
        System.out.println("\n* New user? Press 2 to sign up for an account");

        System.out.print("\n\nMake your choice: ");
        int choice = input.nextInt();

        switch (choice)
        {
            case 1 : {
                login obj = new login();
                obj.login_main();
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
     /*
     Admin a=new Admin("empad1","Sitha");
        a.Console();

        //SystemsE Obj = new SystemsE("Nirmal K");
     //   ResourceE engineer = new ResourceE("wre1", "Ram");
        //Obj.Console();
        engineer.Console();
        Public obj = new Public("public1", "Arya");
        obj.Console();

    }}

    */