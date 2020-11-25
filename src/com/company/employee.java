package com.company;

import java.sql.*;

public class employee extends account{
    public String emp;

    public String getempid(String person) {
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement()
        ) {
            String query1 = "select * from employee";
            ResultSet result = statement.executeQuery(query1);

            while(result.next()){
                String username=result.getString("username");
                String emp_id=result.getString("emp_id");
                if(person==username){
                    emp= emp_id;
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return emp;
    }

}
