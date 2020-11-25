package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Purchase implements Employee{


    String emp_id;

    public void find_emp_id(String username)
    {
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        //step 1 - create a connection object to connect to the db in question - project]
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);

                //step 2 - create a statement object

                Statement statement = connection.createStatement();

        ) {

            String query = "select username, emp_id from employee";
            System.out.println("Statement: " + query);

            //step 3 - create a result object and perform a query
            ResultSet result = statement.executeQuery(query);

            //step 4 - use the result stored in the object for whatever has to be done

            while(result.next())
            {
                String uname = result.getString(emp_id);

                if(uname.equals(username))
                {
                    emp_id = result.getString(emp_id);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public void make_request(){



    }
}
