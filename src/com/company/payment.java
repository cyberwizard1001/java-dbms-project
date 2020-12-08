package com.company;


import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class payment extends database,consumer {

}
    String username;
    String transaction_id;

    public payment(String username,String transaction_id) {
        this.username=username;
        this.transaction_id=transaction_id;
    }

    public void getdetails() {

        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        String sql = "SELECT no_of_connections FROM public" + "WHERE username = this.username";

        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement();) {


            Scanner input = new Scanner(System.in).useDelimiter("\n");
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                int noc = result.getInt("no_of_connections");
                System.out.println("no of connections : " + noc);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void get_other_details() {

        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        String sql1 = "SELECT no_of_connections FROM payment"
                + "WHERE transaction_id = this.transaction_id";

        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement();) {

            ResultSet result1 = statement.executeQuery(sql1);

            while (result.next()) {

                String mode = result1.getString("payment_mode");
                double amt = result1.getDouble("amt_paid");
                int units = result1.getInt("units_consumed");
                System.out.println("payment mode : " + mode);
                System.out.println("amount paid: " + amt);
                System.out.println("number of units of water consumed (in litres) : " + units);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void Console() throws SQLException {

        Scanner input = new Scanner(System.in).useDelimiter("\n");
        System.out.println("press 2 to know your payment details ");
        int r = input.nextInt();
        String username = input.next();
        String transaction_id=input.next();
        payment p = new payment(username,transaction_id);
        if(r==2){
       p.getdetails();
       p.get_other_details();}
        else{
            System.out.println("invalid option selected");}

}
}





//enter your consumer id , transaction id to know your payment details