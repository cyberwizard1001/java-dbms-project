package com.company;
import java.util.*;
import  java.sql.*;

public class treatment_plant {
    public String plant_name;
    Scanner input=new Scanner(System.in);
    public treatment_plant(){
        System.out.println("Enter Waste Water Treatment Plant ID to get its details: ");
        plant_name=input.next();
    }
    String url = "jdbc:mysql://localhost:3306/project_trial";
    String pw = "n";
    String user = "root";

    public void get_method(String p_name){
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement()
        ) {
            String query1 = "select * from waste_water_management";
            ResultSet result = statement.executeQuery(query1);

                    String query2 = "select * from purification";
                    ResultSet result1=statement.executeQuery(query2);
                    int match=0;
                    while(result1.next()){
                        String method= result1.getString("purification_method");
                        if(result1.getString("plant_name").compareToIgnoreCase(p_name)==0){
                            if (match==0){
                                System.out.print("Purification methods involved: ");
                                match=1;
                            }
                            System.out.print(method+" ");
                        }
                    }
                    result1.close();
                } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void plant_details(String username,String name){

        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement()
        ) {
            String query1 = "select * from waste_water_management";
            ResultSet result = statement.executeQuery(query1);


            int found=0;
            while ((result.next() && found==0)) {
                String p_name=result.getString("plant_name");
                String location=result.getString("plant_location_id");
                Float volume=result.getFloat("volume_of_water_per_month");
                if(plant_name.compareToIgnoreCase(p_name)==0){
                    System.out.println("*** Details on "+p_name+" ***");
                    System.out.println("Plant name: "+p_name);
                    System.out.println("Location ID: "+location);
                    System.out.println("Volume of water treated per month(in litres): "+volume);
                    found=1;
                    get_method(p_name);
                }
            }
            if(found!=1){
                System.out.println(("Check the Waste Water Treatment Plant Name!"));
            }
            System.out.println("    ");
            ResourceE resource_obj= new ResourceE(username,name);
            resource_obj.Console();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }



    }
}
