package jdbcDemo.dataUpdation;

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
            String sql = "UPDATE students set std_name='Shaik Ishaq' , std_subject='core java' WHERE std_id=3312";
            statement.executeUpdate(sql);
            System.out.println("Data Updated");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException error){
            error.printStackTrace();
        }
    }
}
