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
            float ph[]= new float[count];
            float con[]= new float[count];
            Date date[]= new Date[count];

            String query2 = "select * from property order by source_id and inspection_date";
            ResultSet result1 = statement.executeQuery(query2);

            for (int i=0;result1.next();i++){
                source_id[i]=result1.getString("source_id");
                date[i]=result1.getDate("inspection_date");
                ph[i]=result1.getFloat("ph_level");
                con[i]=result1.getFloat("contamination_level");
                System.out.println(source_id[i]+" "+ph[i]+" "+con[i]+" "+date[i]);
            }

           /* for(int j=0;j<count;j++){

                ;
            }*/






    } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
