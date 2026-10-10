package jdbcDemo.insertMany;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded and registered");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?user=root&&password=SQL@9100");
            System.out.println("Connection established successfully");
//            Creating statement
            Statement statement = connection.createStatement();
            String sql = """
                    INSERT INTO students(std_id,std_name,std_rollNo,std_subject) VALUES 
                    (3311,'Shaik Shashavali', '23AT5A3311', 'Spring Framework'),
                    (3312, 'Shaik Ishaq', '23At5A3312', 'JDBC'),
                    (3313, 'Shaik Ibrahim', '23At5A3313', 'Core Java')
                    """;
            int res = statement.executeUpdate(sql);
            System.out.println(res+" rows effected");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException error){
            error.printStackTrace();
        }
    }
}
