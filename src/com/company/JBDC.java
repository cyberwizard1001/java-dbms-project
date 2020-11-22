package com.company;

import java.sql.*;

public class JBDC{
      // Save as "JdbcSelectTest.java" (JDK 7 and above)
        public static void main(String[] args) {
            try (
                    // Step 1: Allocate a database 'Connection' object
                    Connection conn = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/project_trial?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                            "root", "n");   // For MySQL only
                    // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

                    // Step 2: Allocate a 'Statement' object in the Connection
                    Statement stmt = conn.createStatement();
            ) {
                // Step 3: Execute a SQL SELECT query. The query result is returned in a 'ResultSet' object.
                String strSelect = "select * from login";
                System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging

                ResultSet result = stmt.executeQuery(strSelect);

                // Step 4: Process the ResultSet by scrolling the cursor forward via next().
                //  For each row, retrieve the contents of the cells with getXxx(columnName).
                System.out.println("The records selected are:");
                int rowCount = 0;
                while(result.next()) {   // Move the cursor to the next row, return false if no more row
                    String username = result.getString("username");
                    String password = result.getString("password");
                    System.out.println(username + ", " + password);
                    ++rowCount;
                }
                System.out.println("Total number of records = " + rowCount);

            } catch(SQLException ex) {
                ex.printStackTrace();
            }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
        }
    }

