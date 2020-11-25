package com.company;

import java.sql.*;
import java.util.Date;

public class complaint {

    public void assignedtome(){
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement()
        ) {
            String query1 = "select * from complaints";
            ResultSet result = statement.executeQuery(query1);

            assigned_to=result.getString("assigned_to");
            for (int i=0;result.next();i++){
                if(assigned_to=="")
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
