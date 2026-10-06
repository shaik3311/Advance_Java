package jdbcDemo.jdbcSteps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded and registered");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&&password=SQL@9100");
            System.out.println("Connection established successfully");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException error){
            error.printStackTrace();
        }
    }
}
