package com.company;

import java.util.Calendar;


public class SystemsE {

    String name;
    java.sql.Date currentTimestamp;

    public SystemsE(String user)
    {
        name = user;
        java.sql.Date currentTimestamp = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }

    public void Console()
    {
        System.out.println("Welcome, "+name);
        System.out.println("Login time: "+currentTimestamp);
    }
}
