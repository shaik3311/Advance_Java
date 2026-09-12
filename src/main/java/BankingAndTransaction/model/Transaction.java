package BankingAndTransaction.model;

import java.time.LocalDateTime;

public class Transaction {
    private long id;
    private long account_id;
    private String transaction_type;
    private double amount;
    private String reference;
    private LocalDateTime created_at;

    Transaction(){}

    public Transaction(long id, long account_id, String transaction_type, double amount, String reference, LocalDateTime created_at) {
        this.id = id;
        this.account_id = account_id;
        this.transaction_type = transaction_type;
        this.amount = amount;
        this.reference = reference;
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
