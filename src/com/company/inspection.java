package com.company;
import java.sql.*;
import java.lang.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;

//used property relation from sql
public class inspection {
    //static final data members
    private static final double ph_min = 6.5;
    private static final double ph_max = 8.5;
    private static final double con_max = 3.77;

    public void inspect(String username,String name) throws SQLException {
        /*This method displays the water bodies having inappropriate ph_level and contamination level.*/
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement()
        ) {
            String query1 = "select * from property";
            ResultSet result = statement.executeQuery(query1);

            String[] source_id = new String[0];
            Float[] ph = new Float[0];
            Float[] con = new Float[0];
            Date[] date = new Date[0];
            //Dynamic Arraylists declaration
            ArrayList<String> mylist = new ArrayList<>(Arrays.asList(source_id));
            ArrayList<Float> mylist2 = new ArrayList<>(Arrays.asList(ph));
            ArrayList<Float> mylist3 = new ArrayList<>(Arrays.asList(con));
            ArrayList<Date> mylist4 = new ArrayList<>(Arrays.asList(date));
            String query2 = "select * from property order by source_id,inspection_date desc";
            ResultSet result1 = statement.executeQuery(query2);

            //adding elements to the dynamic Arraylist(add())
            //converting Arraylist to Array(toArray)
            while(result1.next()){
                String id_element=result1.getString("source_id");
                mylist.add(id_element);
                source_id = mylist.toArray(source_id);

                Date date_element=result1.getDate("inspection_date");
                mylist4.add(date_element);
                date= mylist4.toArray(date);

                Float ph_element=result1.getFloat("ph_level");
                mylist2.add(ph_element);
                ph = mylist2.toArray(ph);

                Float con_element=result1.getFloat("contamination_level");
                mylist3.add(con_element);
                con= mylist3.toArray(con);
            }

            //check on the latest(last inspection_date) properties of the inspected water body
            System.out.println("Sources with inappropriate ph_level: ");
            int j=0;
            while(j< source_id.length){
                int k=j+1,match=0;
                while(k< source_id.length && match==0){
                    if(source_id[j]!=source_id[k]){
                        if(ph[j]<ph_min || ph[j]>ph_max) {
                            System.out.println(source_id[j] + " " + ph[j]);
                            match = 1;
                        }
                    }else{
                        continue;
                    }
                    k+=1;
                }
                j+=1;
            }

            System.out.println("Sources with high contamination level: ");
            int l=0;
            while(l< source_id.length){
                int k=l+1,match=0;
                while(k< source_id.length && match==0){
                    if(source_id[l]!=source_id[k]){
                        if(con[l] >con_max) {
                            System.out.println(source_id[l] + " " +con[l]);
                            match = 1;
                        }
                    }else{
                        continue;
                    }
                    k+=1;
                }
                l+=1;
            }
            //back to myaccount(wre) portal
            ResourceE resource_obj= new ResourceE(username,name);
            resource_obj.Console();
    } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
