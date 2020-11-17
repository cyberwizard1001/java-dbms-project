package com.company;
import java.sql.*;
import java.util.Date;

//uses property relation
public class inspection {
    //static block
    static final double ph_min = 6.5;
    static final double ph_max = 8.5;
    static final int ppm_min = 30;
    static final int ppm_max = 400;
    static final double con_min = 0.0;
    static final double con_max = 4.23;
    String source_id;
    Date inspection_date;
    float ph_level, ppm, contamination_level;

    public void inspect() throws SQLException {
        System.out.println("hi");
        String url = "jdbc:mysql://localhost:3306/project_trial";
        String pw = "n";
        String user = "root";
        try (
                Connection connection = DriverManager.getConnection(url, user, pw);
                Statement statement = connection.createStatement()
        ) {
            String query1 = "select * from property";
            ResultSet result = statement.executeQuery(query1);

            while (result.next()) {
                double ph_level = result.getInt("ph_level");
                double ppm = result.getInt("ppm");
                int contamination = result.getInt("contamination_level");

                if ((ph_level >= ph_min) && (ph_max <= ph_level)) {
                    if ((ppm >= ppm_min) && (ppm <= ppm_max)) {
                        if ((contamination <= con_max) && (contamination >= con_min)) {
                            continue;
                        } else {
                            System.out.println("Source ID with invalid pH value: ");
                        }
                    } else {
                        System.out.println("Source ID with invalid PPM: ");
                    }
                } else {
                    System.out.println("Source ID with high Contamination: ");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
