package BankingAndTransaction.repository;

import BankingAndTransaction.exception.InsufficientBalanceException;
import BankingAndTransaction.model.Account;
import BankingAndTransaction.service.AccountService;

import java.net.ConnectException;
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

    public double getBalance(long acc_no){
        String sql = """
                SELECT balance FROM accounts WHERE account_number=?
                """;
        double balance = 0;
        try(
                Connection connection = DriverManager.getConnection(url,username,password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setDouble(1,acc_no);
            if(checkActive(acc_no)){
                ResultSet set = preparedStatement.executeQuery();
                set.next();
                balance = set.getDouble(1);
            }else{
                System.out.println("Account is Closed");
            }

        }catch (SQLException e){
            System.out.println("Failed at getBalance : "+e.getMessage());
        }
        return balance;
    }

    public boolean checkActive(long acc_no){
        String sql = """
                SELECT status FROM accounts WHERE account_number=?
                """;
        try(
                Connection connection = DriverManager.getConnection(url,username,password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setLong(1,acc_no);
            ResultSet set = preparedStatement.executeQuery();
            set.next();
            if("Closed".equals(set.getString(1))){
                return false;
            }


        }catch (SQLException e){
            System.out.println("Failed at checkActive : "+e.getMessage());
        }
        return true;
    }

    public void closeAccount(long acc_no){
        String sql = """
                Update accounts SET status=? WHERE account_number=?
                """;
        try(
                Connection connection = DriverManager.getConnection(url,username,password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setString(1,"Closed");
            preparedStatement.setLong(2,acc_no);
            int res = preparedStatement.executeUpdate();
            if(res>0){
                System.out.println(res+" Accounts closed");
            }else{
                System.out.println("No account with the given account number found");
            }

        }catch (SQLException e){
            System.out.println("Failed in closeAccount : "+e.getMessage());
        }
    }

    public void deposite(double amount,long acc_no){
        String sql = """
                UPDATE accounts
                SET balance = balance + ?
                WHERE account_number = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(url,username,password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setDouble(1, amount);
            preparedStatement.setLong(2,acc_no);

            int res = preparedStatement.executeUpdate();
            if(res>0){
                System.out.println(res+" rows effected");
            }else{
                System.out.println("0 roes effected");
            }

        }catch (SQLException e){
            System.out.println("Failed at deposit "+e.getMessage());
        }
    }

    public void withDraw(long acc_no, double amount) throws InsufficientBalanceException{
        String sql = """
                UPDATE accounts
                SET balance = balance - ?
                WHERE account_number = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(url,username,password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            if(amount>getBalance(acc_no)){
                throw new InsufficientBalanceException("Insufficient Balance");
            }
            preparedStatement.setDouble(1,amount);
            preparedStatement.setLong(2,acc_no);
            int res = preparedStatement.executeUpdate();
            if(res>0){
                System.out.println(res+" rows effected");
            }else{
                System.out.println("0 rows effected");
            }

        }catch (SQLException e){
            System.out.println("Failed at withDraw : "+e.getMessage());
        }
    }

    public void transfer(long source_acc_no, long destination_acc_no, double amount){
        if(getBalance(source_acc_no)>=amount){
            try {
                withDraw(source_acc_no, amount);
                deposite(amount, destination_acc_no);
            } catch (InsufficientBalanceException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public long getId(long acc_no){
        String sql = """
                SELECT id FROM accounts WHERE account_number = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(url,username,password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setLong(1,acc_no);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("id");

        }catch (SQLException e){
            System.out.println("Failed at getId : "+e.getMessage());
        }
        return -1;
    }
}
