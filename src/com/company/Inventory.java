package com.company;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Inventory extends database{


    String purchase_id;
    String serial_no;
    int num_of_prod;
    String spec;
    String prod_name;
    String upgrade;
    int price;

    public void Console() throws SQLException, IOException {
        ASCIIArtService.print();
        Scanner input = new Scanner(System.in).useDelimiter("\n");

        System.out.println("Inventory Management System\n\n\n");

        System.out.println("Please choose what it is that you wish to do. \n");
        System.out.println("1. Create a new record\n");
        System.out.println("2. Search by product name\n");
        System.out.println("3. Search by serial number\n");
        System.out.print("4. Go back\n");

        System.out.print("Choice: ");
        int choice;
        choice = input.nextInt();

        System.out.print("\n\n");

        if(choice==1)
            create_record();

        else if(choice==2)
            product_view();

        else if(choice==3)
            serial_view();

        else
        {
            SystemsE obj = new SystemsE("Nirmal K","empsys01");
        }

    }

    private boolean check_approved(String check) throws SQLException
    {
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";
        boolean approved_purchase = false;

        //step 1 - create a connection object to connect to the db in question - project]
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);

                //step 2 - create a statement object

                Statement statement = connection.createStatement();

        ) {

            String query = "select purchase_id from purchase";
            System.out.println("Statement: " + query);

            //step 3 - create a result object and perform a query
            ResultSet result = statement.executeQuery(query);

            //step 4 - use the result stored in the object for whatever has to be done
            System.out.println("Checking approved purchases: ");
            while (result.next()&&(!approved_purchase)) {
                purchase_id = result.getString("purchase_id");
                System.out.print(purchase_id);
                if(purchase_id.equals(check))
                {
                    approved_purchase=true;
                    System.out.println("Approved purchase number - verified\n");
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return approved_purchase;
    }


    private void create_record() throws SQLException, IOException {
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Enter approved purchase ID: ");
        String check = input.next();
        boolean verification = check_approved(check);

        if(verification)
        {
            char cont = 'y';
            while(cont=='y'||cont=='Y')
            {
                System.out.println("Enter product details");
                System.out.print("Serial number: ");
                serial_no = input.next();
                System.out.print("Specifications: ");
                spec = input.next();
                System.out.print("Product name: ");
                prod_name = input.next();
                System.out.println("Number of products: 1");
                num_of_prod = 1;

                String query = "insert into inventory (purchase_id,serial_no,spec,prod_name,num_prod,price) values ('" + purchase_id + "','" + serial_no + "','" + spec + "','" + prod_name + "','" + num_of_prod + "','" + price + "')";

                InsertInto(query);

                System.out.print("Do you want to continue? (y/n)");
                cont = input.next().charAt(0);

            }

        }

        else
        {
            System.out.print("Record not found.");
        }

        System.out.print("Press any key to go back");
        System.in.read();

        Console();

    }


    private void serial_view()
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
            System.out.print("Enter serial number: ");
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
                    price = result.getInt("price");

                    System.out.println("Serial number: " + serial_no);
                    System.out.println("Purchase ID: " + purchase_id);
                    System.out.println("Number of products: " + num_of_prod);
                    System.out.println("Specifications: " + spec);
                    System.out.println("Product name: " + prod_name);
                    System.out.println("Price: " + price);
                    System.out.println();


                }


            }

            {
                System.out.println("\nPress any key to go back");

                System.in.read();
                Console();
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    private void product_view()
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
            System.out.print("Enter product name: ");
            String prod_required = input.next();
            String query = "select * from inventory";
            System.out.println("Statement: " + query);

            //step 3 - create a result object and perform a query
            ResultSet result = statement.executeQuery(query);

            //step 4 - use the result stored in the object for whatever has to be done

            while (result.next()) {
                prod_name = result.getString("prod_name");

                if (prod_required.equals(prod_name)) {
                    purchase_id = result.getString("purchase_id");
                    num_of_prod = result.getInt("num_prod");
                    spec = result.getString("spec");
                    serial_no = result.getString("serial_no");
                    price = result.getInt("price");

                    System.out.println("Serial number: " + serial_no);
                    System.out.println("Purchase ID: " + purchase_id);
                    System.out.println("Number of products: " + num_of_prod);
                    System.out.println("Specifications: " + spec);
                    System.out.println("Product name: " + prod_name);
                    System.out.println("Price: " + price);
                    System.out.println();
                }
            }

            {
                System.out.println("\nPress any key to go back");
                System.in.read();

                Console();
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}