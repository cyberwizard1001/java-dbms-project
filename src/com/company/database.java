package com.company;

import java.sql.*;

public class database {

    public String uname = "root";
    public String pw = "n";
    public String url = "jdbc:mysql://localhost:3306/project_trial";

    public ResultSet Query(String query) throws SQLException
    {

        ResultSet result = null;
        try(
                Connection connection = DriverManager.getConnection(url,uname,pw);

                Statement statement = connection.createStatement();
                ) {

            result = statement.executeQuery(query);
            System.out.println("Returning query");

        }
        finally {
            return result;
        }

    }

    public void Update(String query) throws SQLException
    {
        int rowsAffected = 0;
        try(
                Connection connection = DriverManager.getConnection(url,uname,pw);

                Statement statement = connection.createStatement();
                )
        {
           rowsAffected    = statement.executeUpdate(query);
            System.out.println(rowsAffected+ " rows have been updated");

        }

        catch (SQLException exception)
        {
            exception.printStackTrace();
        }

    }

    public void InsertInto(String query) throws SQLException
    {
        int rowsAffected = 0;
        try(
                Connection connection = DriverManager.getConnection(url,uname,pw);

                Statement statement = connection.createStatement();
                )

        {
            rowsAffected = statement.executeUpdate(query);
            System.out.println(rowsAffected+" rows have been inserted");
        }

        catch (SQLException exception)
        {
            exception.printStackTrace();
        }



    }

    public void Delete(String query) throws SQLException
    {
        int rowsAffected = 0;
        try(
                Connection connection = DriverManager.getConnection(url,uname,pw);

                Statement statement = connection.createStatement();
        )
        {
            rowsAffected = statement.executeUpdate("query");
            System.out.println(rowsAffected+" rows have been deleted");
        }

        catch (SQLException exception)
        {
            exception.printStackTrace();
        }


    }
}
