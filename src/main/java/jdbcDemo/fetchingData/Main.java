package jdbcDemo.fetchingData;

import javax.xml.transform.Result;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded and registered");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBC?user=root&&password=SQL@9100");
            System.out.println("Connection established successfully");

            Statement statement = connection.createStatement();
            String sql = """
                    SELECT * FROM students
                    """;

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                System.out.println("{" +
                        "id : "+resultSet.getInt("std_id")+
                        " Name : "+resultSet.getString("std_name")+
                        " RollNo : "+resultSet.getString("std_rollNo")+
                        " Subject : "+resultSet.getString("std_subject")+" }");
            }


        }catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException error){
            error.printStackTrace();
        }
    }
}
