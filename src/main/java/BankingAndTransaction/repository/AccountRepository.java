package BankingAndTransaction.repository;

import BankingAndTransaction.model.Account;
import BankingAndTransaction.service.AccountService;

import java.sql.*;

public class AccountRepository {
    String url = "jdbc:mysql://localhost:3306/banking";
    String username = "root";
    String password = "SQL@9100";
    public boolean existsByAccountNumber(long accountNumber){
        String sql = """
                SELECT * FROM customers
                WHERE id=?
                """;
        try(
                Connection connection = DriverManager.getConnection(url,username,password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setLong(1,accountNumber);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()) {
                return true;
            }

        }catch (SQLException e){
            System.out.println("Failed At checking whether the AccountNumber exists "+e.getMessage());
        }
        return false;
    }

    public void createAccount(Account account){
        String sql = """
                INSERT INTO accounts(customer_id,account_number,account_type,balance,status,created_at)
                VALUES(?,?,?,?,?,?)
                """;
        try(
                Connection connection = DriverManager.getConnection(url,username,password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setLong(1,account.getCustomer_id());
            preparedStatement.setLong(2,account.getAccount_no());
            preparedStatement.setString(3,account.getAccount_type());
            preparedStatement.setDouble(4,account.getBalance());
            preparedStatement.setString(5,account.getStatus());
            preparedStatement.setTimestamp(6,Timestamp.valueOf(account.getCreated_at()));

            int res = preparedStatement.executeUpdate();
            if(res>0){
                System.out.println(res+" rows effected");
            }else{
                System.out.println("0 rows effected");
            }

        }catch (SQLException e){
            System.out.println("Failed at creating account "+e.getMessage());
        }
    }

    public void viewAccount(long id){
        String sql = """
                SELECT id,customer_id,account_number,account_type,balance,status,created_at FROM accounts
                WHERE id=?
                """;
        try(
                Connection connection = DriverManager.getConnection(url,username,password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setLong(1,id);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                Account account = AccountService.mapRows(res);
                System.out.println(account);
            }else{
                System.out.println("No account found");
            }
        }catch (SQLException e){
            System.out.println("Failed at viewAccount "+e.getMessage());
        }
    }
}
