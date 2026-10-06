package BankingAndTransaction.service;

import BankingAndTransaction.exception.InsufficientBalanceException;
import BankingAndTransaction.repository.AccountRepository;
import BankingAndTransaction.repository.TransactionRepository;
import CircularDependencyDemo.simple.A;

import java.time.LocalDateTime;
import java.util.Scanner;

public class TransactionService {
    Scanner sc = new Scanner(System.in);
    TransactionRepository transactionRepository = new TransactionRepository();
    AccountRepository accountRepository = new AccountRepository();

    public void transactionMenu(){
        boolean turn = true;
        while(turn){
            System.out.println("======= Welcome to Transactions Menu =======");
            System.out.println("Transaction Services we provide : ");
            System.out.println("1.Deposit\n2.Withdraw\n3.Transfer to other Account\n4.Exit");
            System.out.print("Select an option : ");
            int option = sc.nextInt();

            switch (option){
                case 1 ->{
                    System.out.println("Enter Details to Deposit ");
                    System.out.print("Enter Account Number : ");
                    long account_no = sc.nextLong();
                    System.out.print("Enter amount : ");
                    double amount = sc.nextDouble();

//                    check is account active ?
                    if(accountRepository.checkActive(account_no)) {
//                        Perform Deposite Operation
                        accountRepository.deposite(amount, account_no);

//                        Create data for saving transaction
                        String transactionType = "Deposit";
                        String reference = "TXN" + System.currentTimeMillis();
                        LocalDateTime created_at = LocalDateTime.now();
//                        Saving Transaction
                        transactionRepository.saveTransaction(accountRepository.getId(account_no),transactionType,amount,reference,created_at,accountRepository.getId(account_no));
                    }else{
                        System.out.println("Account is closed");
                    }

                }
                case 2 ->{
                    System.out.println("Enter Details to WithDraw ");
                    System.out.print("Enter Account Number : ");
                    long account_no = sc.nextLong();
                    System.out.print("Enter amount : ");
                    double amount = sc.nextDouble();

//                    check is account active ?
                    if(accountRepository.checkActive(account_no)) {
//                        Perform WithDraw Operation
                        try {
                            accountRepository.withDraw(account_no, amount);
                        } catch (InsufficientBalanceException e) {
                            throw new RuntimeException(e);
                        }

//                        Create data for saving transaction
                        String transactionType = "WithDraw";
                        String reference = "TXN" + System.currentTimeMillis();
                        LocalDateTime created_at = LocalDateTime.now();
//                        Saving Transaction
                        transactionRepository.saveTransaction(accountRepository.getId(account_no),transactionType,amount,reference,created_at, accountRepository.getId(account_no));
                    }else{
                        System.out.println("Account is closed");
                    }
                }
                case 3 ->{
                    System.out.println("Enter Details to Transfer ");
                    System.out.print("Enter Source Account Number : ");
                    long source_acc_no = sc.nextLong();
                    System.out.print("Enter Destination Account Number : ");
                    long destination_acc_no = sc.nextLong();
                    System.out.print("Enter amount : ");
                    double amount = sc.nextDouble();

//                    check is account active ?
                    if(accountRepository.checkActive(source_acc_no) && accountRepository.checkActive(destination_acc_no)) {
//                        Perform WithDraw Operation
                        accountRepository.transfer(source_acc_no, destination_acc_no, amount);

//                        Create data for saving transaction
                        String transactionType = "Transfer";
                        String reference = "TXN" + System.currentTimeMillis();
                        LocalDateTime created_at = LocalDateTime.now();
//                        Saving Transaction
                        transactionRepository.saveTransaction(accountRepository.getId(source_acc_no),transactionType,amount,reference,created_at,accountRepository.getId(destination_acc_no));
                    }else{
                        System.out.println("Account is closed");
                    }
                }
                case 4 ->{
                    turn = false;
                }
            }
        }
    }
}
