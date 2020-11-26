package com.company;
import java.sql.*;
import java.lang.*;
import java.util.Collection;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;


//uses property relation
public class inspection {
    //static final data members
    private static final double ph_min = 6.5;
    private static final double ph_max = 8.5;
    private static final double con_max = 3.77;

    public void inspect(String username,String name) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement()
        ) {
            String query1 = "select * from property";
            ResultSet result = statement.executeQuery(query1);

            int count=0;
            while (result.next()) {
                count+=1;
            }
            result.close();

            String source_id[]= new String[0];
            String ph[]= new String[0];
            String con[]= new String[0];
            Date date[]= new Date[0];
            ArrayList<String> mylist = new ArrayList<String>(Arrays.asList(source_id));
            ArrayList<String> mylist2 = new ArrayList<String>(Arrays.asList(ph));
            ArrayList<String> mylist3 = new ArrayList<String>(Arrays.asList(con));
            ArrayList<Date> mylist4 = new ArrayList<Date>(Arrays.asList(date));
            String query2 = "select * from property order by source_id,inspection_date desc";
            ResultSet result1 = statement.executeQuery(query2);

            while(result1.next()){
                String id_element=result1.getString("source_id");
                mylist.add(id_element);
                source_id = mylist.toArray(source_id);

                Date date_element=result1.getDate("inspection_date");
                mylist4.add(date_element);
                date= mylist4.toArray(date);

                String ph_element=result1.getString("ph_level");
                mylist2.add(ph_element);
                ph = mylist2.toArray(ph);

                String con_element=result1.getString("contamination_level");
                mylist3.add(con_element);
                con= mylist4.toArray(con);
            }
            System.out.println("Sources with inappropriate ph_level: ");
            int j=0;
            while(j<count){
                int k=j+1,match=0;
                while(k<count && match==0){
                    if(source_id[j]!=source_id[k]){
                        if(Double.valueOf(ph[j])<ph_min || Double.valueOf(ph[j])>ph_max) {
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
            j=0;
            while(j<count){
                int k=j+1,match=0;
                while(k<count && match==0){
                    if(source_id[j]!=source_id[k]){
                        if(Double.valueOf(con[j])>con_max) {
                            System.out.println(source_id[j] + " " + con[j]);
                            match = 1;
                        }
                    }else{
                        continue;
                    }
                    k+=1;
                }
                j+=1;
            }
            ResourceE resource_obj= new ResourceE(username,name);
            resource_obj.Console();
    } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
