package com.company;

import java.sql.*;

public class Purchase implements employee {

    String username;
    String emp_id;

    public Purchase(String username)
    {
        this.username = username;
    }

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

    public void make_request() throws SQLException {

        ASCIIArtService.print();
        String print = "Purchase request";
        StringAlignUtils util = new StringAlignUtils(59, StringAlignUtils.Alignment.CENTER);
        System.out.println( "\n\t\t" + util.format(print) );
        System.out.print("\n");

        find_emp_id(username);
        System.out.println("Welcome, " + username + "(" + emp_id + ")");


    }
}
