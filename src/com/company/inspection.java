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
            String ph_source_id[] = new String[4];
            String con_source_id[] = new String[3];
            double invalid_ph[] = new double[4];
            double high_con[] = new double[3];
            Date ph_date[] = new Date[4];
            Date con_date[] = new Date[3];

            int con_count = 0;
            int ph_count = 0;
            String query1 = "select * from property order by source_id and inspection_date";
            ResultSet result = statement.executeQuery(query1);

            int count=0;
            while (result.next()) {
                count+=1;
            }
            result.close();
            String source_id[]= new String[count];
            double ph[]= new double[count];
            double con[]= new double[count];
            Date date[]= new Date[count];

            String query2 = "select * from property order by source_id and inspection_date";
            ResultSet result1 = statement.executeQuery(query2);

            for (int i=0;result1.next();i++){
                source_id[i]=result1.getString("source_id");
                date[i]=result1.getDate("inspection_date");
                ph[i]=result1.getFloat("ph_level");
                con[i]=result1.getFloat("contamination_level");
                System.out.println(source_id[i]+" "+ph[i]+" "+con[i]);
            }


            for (int i=0;ph_count>0;ph_count--) {
                System.out.println(ph_source_id[i]+" "+ph_date[i]+" "+invalid_ph[i]);
                i++;
            }





    } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
