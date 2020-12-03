package com.company;

import java.sql.*;

public class project {

    public void decideapproval(){
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement()
        ) {
            String query1 = "select * from project where ...";
            ResultSet result = statement.executeQuery(query1);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
