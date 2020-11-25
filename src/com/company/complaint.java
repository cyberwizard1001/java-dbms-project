package com.company;
import java.sql.*;
import java.util.Date;

public class complaint {

    public void assignedtome(String emp_id) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement()
        ) {
            String query1 = "select * from complaints";
            ResultSet result = statement.executeQuery(query1);

            int c=0;
            while(result.next()){
                String assigned_to=result.getString("assigned_to");
                if(assigned_to.equals(emp_id)){
                    c=c+1;
                    System.out.println("("+c+") "+assigned_to);
                }
            }
            result.close();
            if(c==0){
                System.out.println("No complaints issued to you!");
            }else{
                ;
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
