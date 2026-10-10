package jdbcDemo.dataInsertion;

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
            String sql = "INSERT INTO students VALUES(3312, 'Shaik Shashavali', '23AT5A3312', 'JDBC')";
//            Inserting data with execute which is generic
//            statement.execute(sql);
//            System.out.println("Data inserted into students table");

//            using executeUpdate which is specialized for DML operations
            int res = statement.executeUpdate(sql);
            System.out.println(res+" rows effected");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException error){
            error.printStackTrace();
        }
    }
}
