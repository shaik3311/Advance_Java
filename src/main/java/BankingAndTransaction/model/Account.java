package BankingAndTransaction.model;

import java.time.LocalDateTime;

public class Account {
    private long id;
    private long customer_id;
    private long account_no;
    private String account_type;
    private double balance;
    private String status;
    private LocalDateTime created_at;

    public Account(){}

    public Account(long id, long customer_id, long account_no, String account_type, double balance, String status, LocalDateTime created_at) {
        this.id = id;
        this.customer_id = customer_id;
        this.account_no = account_no;
        this.account_type = account_type;
        this.balance = balance;
        this.status = status;
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public long getAccount_no() {
        return account_no;
    }

    public void setAccount_no(long account_no) {
        this.account_no = account_no;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", customer_id=" + customer_id +
                ", account_no=" + account_no +
                ", account_type='" + account_type + '\'' +
                ", balance=" + balance +
                ", status='" + status + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
