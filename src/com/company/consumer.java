package com.company;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class consumer extends database {

    String username;

    public consumer(String username) {
        this.username = username;
    }

    //checking username existence function:
    public bool existing_username(String uname) {
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement();

        ) {
            String query = "select username from public";

            Scanner input = new Scanner(System.in).useDelimiter("\n");
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String username = result.getString("username");
                if (uname.equals(username)) {
                    return true;
                }
            }
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }

    public void update_address(String user) {
        String username = user;
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        String sqlUpdate = "UPDATE public "
                + "SET locality = ? "
                + "WHERE username = uname";

        try (
                Connection connection = DriverManager.getConnection(url, user, pw);

                PreparedStatement pstmt = connection.prepareStatement(sqlUpdate)) {
            boolean m;
            Scanner input = new Scanner(System.in).useDelimiter("\n");
            username = input.next();
            m = existing_username(username);
            if(m) {
                String locality = "T Nagar";
                pstmt.setString(1, locality);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void mainconsole() throws SQLException {

        Scanner input = new Scanner(System.in).useDelimiter("\n");
        System.out.println("press 1 : for updating existing locality");
        int reply = input.nextInt();
        switch (reply) {
            case 1:
                consumer obj = new consumer(username);
                obj.update_address(username);
                break;
            case 2:
                consumer obj1 = new consumer(username);
                obj1.update_address(username);
            default:
                System.out.println("invalid chose!");
        }
    }

}




