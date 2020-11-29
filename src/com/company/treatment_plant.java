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

    public void get_details(String emp_id,String username,String name){
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement()
        ) {
            String query1 = "select * from waste_water_management";
            ResultSet result = statement.executeQuery(query1);

            int found=0;
            while ((result.next() && found==0)) {
                String p_name=result.getString("plant_name");
                if(plant_name.compareToIgnoreCase(p_name)==0){
                    System.out.println(p_name);
                    found=1;
                }
            }
            if(found!=1){
                System.out.println(("Check the Waste Water Treatment Plant Name!"));
            } 
            result.close();
            ResourceE resource_obj= new ResourceE(username,name);
            resource_obj.Console();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }



    }
}
