package jdbcDemo.dataDeletion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver class loaded and registered");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBC?user=root&&password=SQL@9100");
            System.out.println("Connection established");
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM students WHERE std_id=3312 or std_id=3311";
            int res = statement.executeUpdate(sql);
            System.out.println(res+" rows deleted");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
