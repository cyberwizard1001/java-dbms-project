package com.company;
import java.sql.*;
import java.util.Date;


//uses property relation
public class inspection {
    //static final data members
    private static final double ph_min = 6.5;
    private static final double ph_max = 8.5;
    private static final double con_max = 3.77;

    public void inspect() throws SQLException {
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
            String source_id[]= new String[count];
            float ph[]= new float[count];
            float con[]= new float[count];
            Date date[]= new Date[count];

            String query2 = "select * from property order by source_id,inspection_date desc";
            ResultSet result1 = statement.executeQuery(query2);

            for (int i=0;result1.next();i++){
                source_id[i]=result1.getString("source_id");
                date[i]=result1.getDate("inspection_date");
                ph[i]=result1.getFloat("ph_level");
                con[i]=result1.getFloat("contamination_level");
            }
            System.out.println("Sources with inappropriate ph_level: ");
            int j=0;
            while(j<count){
                int k=j+1,match=0;
                while(k<count && match==0){
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
            j=0;
            while(j<count){
                int k=j+1,match=0;
                while(k<count && match==0){
                    if(source_id[j]!=source_id[k]){
                        if(con[j]>con_max) {
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
    } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
