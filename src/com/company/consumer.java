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
                Statement statement = connection.createStatement()

        ) {


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

    public void update_address() {

        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        String sqlUpdate = "UPDATE public "
                + "SET locality = ? "
                + "WHERE username = this.username";

        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                PreparedStatement pstmt = connection.prepareStatement(sqlUpdate))

        {

            String locality = "T Nagar";
            pstmt.setString(1, locality);




        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void Console() throws SQLException {

        Scanner input = new Scanner(System.in).useDelimiter("\n");
        System.out.println("press 1 : for updating existing locality");
        int reply = input.nextInt();
        String username = input.next();

        consumer obj = new consumer(username);
        switch (reply) {
            case 1:
                if(obj.check_existing_username()){
                    obj.update_address();}
                break;

            default:
                System.out.println("invalid choose!");
        }
    }

}




