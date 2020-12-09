package com.company;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class complaint {
    Scanner input = new Scanner(System.in);
    public String[] location;

    public complaint() {
        location = new String[]{"Gandhipuram", "Siddhapudur", "Sungam", "Peelamedu", "Saibaba Colony", "Race Course"};
    }

    public void report_complaint(String username, String name) throws ParseException, SQLException {
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";

        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement();
        ) {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            String date = (dtf.format(now));
            System.out.println("Issue: ");
            String i = input.nextLine();
            String query3 = "select substring(complaint_id,2,3) AS ExtractString FROM complaints order by ExtractString + 0 desc";
            ResultSet primarykey = statement.executeQuery(query3);

            int complaint_id = 0;
            int match = 0;

            while (primarykey.next() && match == 0) {
                String pk = primarykey.getString("ExtractString");
                complaint_id = Integer.parseInt(pk);
                match = 1;
            }
            complaint_id = complaint_id + 1;
            String query2 = "insert into complaints " + "values ('" + username + "','c" + complaint_id + "','" + i + "','pending','" + date + "','empad1')";
            statement.execute(query2);
            System.out.println("Complaint recorded");
            System.out.println("\n");
            Public public_obj = new Public(username, name);
            public_obj.Console();
        }catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }

    }

    public void assigncomplaints(String username,String name) throws SQLException{
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement()
        ) {
            String query1 = "select * from complaints";
            ResultSet result = statement.executeQuery(query1);
            String[] complaint_id = new String[0];
            String[] issue = new String[0];
            ArrayList<String> mylist = new ArrayList<>(Arrays.asList(complaint_id));
            ArrayList<String> mylist1 = new ArrayList<>(Arrays.asList(issue));
            System.out.println("*** Complaints that is needed to be assigned to the engineers ***");

            while(result.next()){
                String assigned_to=result.getString("assigned_to");

                if(assigned_to.compareTo("empad1")==0){
                    String c_id=result.getString("complaint_id");
                    mylist.add(c_id);
                    complaint_id = mylist.toArray(complaint_id);

                    String prob=result.getString("issue");
                    mylist1.add(prob);
                    issue = mylist1.toArray(issue);
                   }
                }

            for(int y=0;y<complaint_id.length;y++){
                System.out.println("complaint_id: "+complaint_id[y]);
                System.out.println("Issue: "+issue[y]);
                System.out.print("Assign to: ");
                String e_id=input.next();
                String query5="update complaints set assigned_to='"+e_id+"' where complaint_id='"+complaint_id[y]+"'";
                statement.executeUpdate(query5);
            }
            System.out.println("\n");
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
            System.out.println("\n");
            ResourceE resource_obj= new ResourceE(username,name);
            resource_obj.Console();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
