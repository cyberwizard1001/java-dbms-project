package com.company;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;

public class    login {

    boolean login = false;
    int err_count = 0;

    public void login_main() throws SQLException {

        ASCIIArtService.print();

        while((!login)&&(err_count<3))
        {
            System.out.println("Login");
            Login();
            if(!login)
            {
                System.out.println("You have "+ (3-err_count) + " tries left.");
            }

        }
    }

    public void Login() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        //step 1 - create a connection object to connect to the db in question - project]
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);

                //step 2 - create a statement object

                Statement statement = connection.createStatement()
                //task for self - learn how to use prepared statements
        ) {
            String query = "select * from account";

            //step 3 - create a result object and perform a query
            ResultSet result = statement.executeQuery(query);

            //step 4 - use the result stored in the object for whatever has to be done

            String user_input;
            String pass_input;

            Scanner input = new Scanner(System.in).useDelimiter("\n");

            System.out.print("Enter username: ");
            user_input = input.next();
            System.out.print("Enter password: ");
            pass_input = input.next();



                while (result.next()) {
                    String username = result.getString("username");
                    String password = result.getString("password");
                    String type = result.getString("type");
                    String name = result.getString("name");

                    if (username.equals(user_input)) {
                        if (password.equals(pass_input)) {
                            System.out.println("You've successfully logged in");
                            login = true;


                            if(type.equals("admin"))
                            {
                                Admin obj = new Admin(username,name);
                                obj.Console();
                            }

                            else if(type.equals("finance"))
                            {
                                Finance obj = new Finance(username,name);
                                obj.Console();
                            }

                            else if(type.equals("wre"))
                            {
                                ResourceE obj = new ResourceE(username,name);
                                obj.Console();
                            }

                            else if(type.equals("project"))
                            {
                                ProjectE obj = new ProjectE();
                                obj.Console();
                            }

                            else if(type.equals("sysadmin"))
                            {
                                SystemsE obj = new SystemsE(name,username);
                                obj.Console();
                            }

                            else
                            {
                                Public obj = new Public(username,name);
                                obj.Console();
                            }



                        }
                    }
                }

                if (!login) {
                    System.out.println("Please try again, your credentials are wrong");
                    err_count++;

                }



        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }

    }

}
