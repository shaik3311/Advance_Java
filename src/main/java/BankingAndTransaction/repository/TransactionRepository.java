package BankingAndTransaction.repository;

import java.sql.*;
import java.time.LocalDateTime;

public class TransactionRepository {
    String url = "jdbc:mysql://localhost:3306/banking";
    String username = "root";
    String password = "SQL@9100";

    public void saveTransaction(
            long accountId,
            String transType,
            double amount,
            String reference,
            LocalDateTime createdAt) {

        String sql = """
            INSERT INTO transactions
            (account_id, transaction_type, amount, reference, created_at, destination_acc_id)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {

            preparedStatement.setLong(1, accountId);
            preparedStatement.setString(2, transType);
            preparedStatement.setDouble(3, amount);
            preparedStatement.setString(4, reference);
            preparedStatement.setTimestamp(5, Timestamp.valueOf(createdAt));

            // No destination account for deposit
            preparedStatement.setNull(6, Types.BIGINT);

            int res = preparedStatement.executeUpdate();

            if (res > 0) {
                System.out.println("Transaction saved successfully");
            }

        } catch (SQLException e) {
            System.out.println("Failed at save Transaction " + e.getMessage());
        }
    }
}
