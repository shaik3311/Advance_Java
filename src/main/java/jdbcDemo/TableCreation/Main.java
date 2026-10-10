package jdbcDemo.TableCreation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded and registered");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBC?user=root&&password=SQL@9100");
            System.out.println("Connection established successfully");
//            Creating statement
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE students (std_id INT, std_name VARCHAR(20), std_rollNo VARCHAR(20), std_subject VARCHAR(50))";
            statement.execute(sql);
            System.out.println("Table Creation completed");

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException error){
            error.printStackTrace();
        }
    }
}
