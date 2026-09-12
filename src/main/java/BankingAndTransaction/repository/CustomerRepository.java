package BankingAndTransaction.repository;

import BankingAndTransaction.model.Customer;
import BankingAndTransaction.service.CustomerService;

import java.sql.*;

public class CustomerRepository {
    String url = "jdbc:mysql://localhost:3306/banking";
    String username = "root";
    String password = "SQL@9100";

    public void createCustomer(Customer customer){
        String sql = """
                INSERT INTO customers(name,email,phone,created_at)
                VALUES(?,?,?,?)
                """;
        try(
                Connection connection = DriverManager.getConnection(url,username,password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2,customer.getEmail());
            preparedStatement.setString(3,customer.getPhone());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(customer.getCreatedAt()));

            int res = preparedStatement.executeUpdate();
            if(res>0){
                System.out.println(res+" Rows effected");
            }else{
                System.out.println("0 rows effected");
            }

        }catch (SQLException e){
            System.out.println("Error at creating Customer "+e.getMessage());
        }
    }

    public void updateCustomerUsername(long id,String newName){
        String sql = """
                UPDATE customers SET name=? WHERE id=?
                """;
        try(
                Connection connection = DriverManager.getConnection(url,username,password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setString(1,newName);
            preparedStatement.setLong(2,id);
            int res = preparedStatement.executeUpdate();
            if(res>0){
                System.out.println(res+" rows effected");
            }else{
                System.out.println("0 rows effected");
            }

        }catch (SQLException e){
            System.out.println("Failed at Customer username update "+e.getMessage());
        }
    }

    public void updateCustomerEmail(long id,String newEmail){
        String sql = """
                UPDATE customers SET email=? WHERE id=?
                """;
        try(
                Connection connection = DriverManager.getConnection(url,username,password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1,newEmail);
            preparedStatement.setLong(2,id);
            int res = preparedStatement.executeUpdate();
            if(res>0){
                System.out.println(res+" rows effected");
            }else{
                System.out.println("0 rows effected");
            }

        }catch (SQLException e){
            System.out.println("Failed at Customer username update "+e.getMessage());
        }
    }

    public void updateCustomerPhone(long id,String newPhone){
        String sql = """
                UPDATE customers SET phone=? WHERE id=?
                """;
        try(
                Connection connection = DriverManager.getConnection(url,username,password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1,newPhone);
            preparedStatement.setLong(2,id);
            int res = preparedStatement.executeUpdate();
            if(res>0){
                System.out.println(res+" rows effected");
            }else{
                System.out.println("0 rows effected");
            }

        }catch (SQLException e){
            System.out.println("Failed at Customer username update "+e.getMessage());
        }
    }

    public void searchCustomerById(long id){
        String sql = """
                SELECT id,name,email,phone,created_at FROM customers
                WHERE id=?
                """;
        try(
                Connection connection  = DriverManager.getConnection(url,username,password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setLong(1,id);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                Customer customer = CustomerService.mapRows(res);
                System.out.println(customer);
            }else{
                System.out.println("Customer not found");
            }


        }catch (SQLException e){
            System.out.println("Failed at search Customer By id "+e.getMessage());
        }
    }

    public void deleteCustomerById(long id){
        String sql = """
                DELETE FROM customers
                WHERE id=?
                """;
        try(
                Connection connection  = DriverManager.getConnection(url,username,password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setLong(1,id);
            int res = preparedStatement.executeUpdate();
            if(res>0){
                System.out.println(res+" rows effected");
            }else{
                System.out.println("Customer not found");
            }


        }catch (SQLException e){
            System.out.println("Failed at search Customer By id "+e.getMessage());
        }
    }

    public boolean checkCustomerExists(long id){
        String sql = """
                SELECT id,name,email,phone,created_at FROM customers
                WHERE id=?
                """;
        try(
                Connection connection  = DriverManager.getConnection(url,username,password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setLong(1,id);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                return true;
            }


        }catch (SQLException e){
            System.out.println("Failed at search Customer By id "+e.getMessage());
        }
        return false;
    }

}
