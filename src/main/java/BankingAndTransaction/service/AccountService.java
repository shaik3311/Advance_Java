package BankingAndTransaction.service;

import BankingAndTransaction.model.Account;
import BankingAndTransaction.repository.AccountRepository;
import BankingAndTransaction.repository.CustomerRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class AccountService {
    Scanner sc = new Scanner(System.in);

    public long generateAccountNumber(){
        long number = 100000000000L +
                (long) (Math.random() * 900000000000L);

        return Long.valueOf(number);
    }

    public static Account mapRows(ResultSet res)throws SQLException {
        Account account = new Account();
        account.setId(res.getLong(1));
        account.setCustomer_id(res.getLong(2));
        account.setAccount_no(res.getLong(3));
        account.setAccount_type(res.getString(4));
        account.setBalance(res.getDouble(5));
        account.setStatus(res.getString(6));
        account.setCreated_at(res.getTimestamp(7).toLocalDateTime());

        return account;
    }

    public void accountsMenu(){
        boolean turn = true;
        while(turn){
            System.out.println("======= Welcome to Accounts Menu =======");
            System.out.println("Account Services we provide : ");
            System.out.println("1.Create Account\n2.View Account\n3.Check Balance\n4.Close Account\n5.Exit");
            System.out.print("Select an option : ");
            int option = sc.nextInt();
            switch (option){
                case 1 -> {
                    System.out.println("Enter Details to create Account ");
                    System.out.print("Enter Customer id : ");
                    long customer_id = sc.nextLong();
                    sc.nextLine();
                    System.out.print("Enter Type of Account (Savings/Current) : ");
                    String account_type = sc.nextLine();
                    System.out.print("Enter Initial Balance : ");
                    double balance = sc.nextDouble();
                    String status = "Active";

//                    Generating account number and checking unique
                    AccountRepository accountRepository = new AccountRepository();
                    CustomerRepository customerRepository = new CustomerRepository();
                    long account_number = 0;
                    do{
                        account_number = generateAccountNumber();
                    }while (accountRepository.existsByAccountNumber(account_number));
                    if(customerRepository.checkCustomerExists(customer_id)){
                        Account account = new Account(1,customer_id,account_number,account_type,balance,status, LocalDateTime.now());
                        accountRepository.createAccount(account);
                    }else{
                        System.out.println("Customer with customer_id "+customer_id+" not exists");
                    }
                }

                case 2 -> {
                    System.out.println("Enter Details to view account : ");
                    System.out.print("Enter account id : ");
                    long id = sc.nextLong();
                    AccountRepository accountRepository = new AccountRepository();
                    accountRepository.viewAccount(id);
                }
                case 3 -> {

                }
                case 5 -> {
                    turn = false;
                }
            }
        }
    }
}
