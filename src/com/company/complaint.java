package com.company;
import java.sql.*;
import java.util.*;

public class complaint {
    public String[] location;

    public complaint(){
        location= new String[]{"Gandhinagar","Siddhapudur","Sungam","Peelamedu","Saibaba Colony"};
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
                int i=0,t=0;
                    if(assigned_to.equals(emp_id) && result.getString("complaint_status").equals("pending")){
                        c=c+1;
                        int random_location= r.nextInt(location.length);
                        System.out.println("("+c+") "+complaint_id+" "+issue+" @ "+location[random_location]);
                        }
            }
            result.close();
            if(c==0){
                System.out.println("No complaints issued to you!");
            }else{
                Scanner input=new Scanner(System.in);
                System.out.println("Press 'S' to solve the above mentioned Complaints");
                System.out.println("Press 'N' to exit complaint portal");
                String a=input.next();
                if (a.compareToIgnoreCase("s")==0){
                     System.out.println("in yes");
                }else if (a.compareToIgnoreCase("n")==0){
                    System.out.println("in no");
                }
            }
            ResourceE resource_obj= new ResourceE(username,name);
            resource_obj.Console();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
