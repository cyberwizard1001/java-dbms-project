package com.company;
import java.sql.*;
import java.util.Date;


//uses property relation
public class inspection {
    //static data members
        private static final double ph_min = 6.5;
        private static final double ph_max = 8.5;
        private static final double con_max = 4.23;

    public void inspect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement()
        ) {
            String ph_source_id[]= new String[3];
            String con_source_id[]= new String[3];
            double invalid_ph[]= new double[3];
            double high_con[]= new double[3];
            Date ph_date[]= new Date[3];
            Date con_date[]= new Date[3];
            int con_count=0;
            int ph_count=0;
            String query1 = "select * from property";
            ResultSet result = statement.executeQuery(query1);

            while (result.next()){
                String source_id= result.getString("source_id");
                double ph_level = result.getInt("ph_level");
                int contamination = result.getInt("contamination_level");
                Date date = result.getDate("inspection_date");

                if ((ph_level <= ph_min) || (ph_level >= ph_max)){
                    ph_source_id[ph_count]= source_id;
                    invalid_ph[ph_count]= ph_level;
                    ph_date[ph_count]= date;
                    ph_count=ph_count+1;
                }
                if (contamination > con_max){
                    con_source_id[con_count]= source_id;
                    high_con[con_count]= contamination;
                    con_date[con_count]=  date;
                    con_count=con_count+1;
                }
            }
            System.out.println("Source ID with invalid pH value: ");
            System.out.println(ph_count);
            for (int i=0;ph_count<i;i--){
                System.out.println(ph_source_id[i]+" "+ invalid_ph[i]);
            }
            /*for (int i=0;ph_count!=0;ph_count--){
                for (int j=i+1;ph_count!=0;ph_count--){
                    if (ph_source_id[i].equals(ph_source_id[j])){
                       if (ph_date[i].after(ph_date[j])) {
                           System.out.println(ph_source_id[i] + "   " + invalid_ph[i]);
                       }
                       else {
                           System.out.println(ph_source_id[j] + " " + invalid_ph[j]);
                       }
                    }
                    j=j+1;
                }
                i=i+1;
            }
           /* System.out.println("Source ID high contamination: " );
            for (int i=0;con_count!=0;con_count--){
                for (int j=i+1;con_count!=0;con_count--){
                    if (con_source_id[i].equals(con_source_id[j])){
                        if (con_date[i].after(con_date[j])) {
                            System.out.println(con_source_id[i] + "   " + high_con[i]);
                        }
                        else {
                            System.out.println(con_source_id[j] + " " + high_con[j]);
                        }
                    }
                    j=j+1;
                }
                i=i+1;
            }*/
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
