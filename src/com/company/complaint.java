package com.company;
import java.sql.*;
import java.util.*;

public class complaint {
    Scanner input=new Scanner(System.in);
    public String[] location;

    public complaint(){
        location= new String[]{"Gandhipuram","Siddhapudur","Sungam","Peelamedu","Saibaba Colony","Race Course"};
    }

    public void report_complaint(String username,String name) {
    }

    public void assigncomplaints(String username,String name){
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement()
        ) {
            String query1 = "select * from complaints";
            ResultSet result = statement.executeQuery(query1);
            System.out.println("*** Complaints that is needed to be assigned to the engineers ***");

            while(result.next()){
                String complaint_id=result.getString("complaint_id");
                String assigned_to=result.getString("assigned_to");
                String issue=result.getString("issue");
                if(assigned_to.compareTo("empad1")==0){
                    System.out.println(complaint_id+" "+issue);
                   }
                }

            int d=0;
            do{
                System.out.println("Assign to: ");
                System.out.print("Complaint_id: ");
                String c=input.next();
                System.out.println("Assign to(enter emp_id): ");
                String e=input.next();
                String query5="update complaints set assigned_to='"+e+"' where complaint_id='"+c+"'";
                statement.executeUpdate(query5);
                System.out.println("Want to continue? Press '1'");
                d=input.nextInt();
              }while(d==1);
            Admin admin_obj= new Admin(username,name);
            admin_obj.Console();

            } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void assignedtome(String emp_id,String username,String name) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement()
        ) {
            String query1 = "select * from complaints";
            ResultSet result = statement.executeQuery(query1);
            Random r = new Random();


            int c=0;
            while(result.next()){
                String complaint_id=result.getString("complaint_id");
                String assigned_to=result.getString("assigned_to");
                String issue=result.getString("issue");
                    if(assigned_to.equals(emp_id) && result.getString("complaint_status").equals("pending")){
                        c=c+1;
                        int random_location= r.nextInt(location.length);
                        System.out.println("("+c+") Complaint_id: "+complaint_id);
                        System.out.println("    Issue & Location: "+issue+" @ "+location[random_location]);
                        }
            }
            result.close();
            ResourceE resource_obj= new ResourceE(username,name);
            resource_obj.Console();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
