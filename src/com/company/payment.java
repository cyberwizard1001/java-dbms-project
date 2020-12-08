package com.company;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class consumer extends database {

    String username;

    public consumer(String username) {
        this.username = username;
    }

    //checking username existence function:
    public bool check_existing_username() {
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        String query = "select username from public";

        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement();

        ) {


            Scanner input = new Scanner(System.in).useDelimiter("\n");

            Statement statement = connection.createStatement()
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String uname = result.getString("username");
                if (uname.equals(this.username)) {
                    return true;
                }
            }
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }
}
