package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Inventory {


    String purchase_id;
    String serial_no;
    int num_of_prod;
    String spec;
    String prod_name;
    String upgrade;
    int price;

    public void serial_view()
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

            Scanner input = new Scanner(System.in).useDelimiter("\n");
            String serial_required = input.next();
            String query = "select * from inventory";
            System.out.println("Statement: " + query);

            //step 3 - create a result object and perform a query
            ResultSet result = statement.executeQuery(query);

            //step 4 - use the result stored in the object for whatever has to be done

            while (result.next()) {
                serial_no = result.getString("serial_no");

                if (serial_required.equals(serial_no)) {
                    purchase_id = result.getString("purchase_id");
                    num_of_prod = result.getInt("num_prod");
                    spec = result.getString("spec");
                    prod_name = result.getString("prod_name");

                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

}