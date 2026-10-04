package BankingAndTransaction;

import BankingAndTransaction.service.AccountService;
import BankingAndTransaction.service.CustomerService;
import BankingAndTransaction.service.TransactionService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean turn = true;
        CustomerService customerService = new CustomerService();
        AccountService accountService = new AccountService();
        TransactionService transactionService = new TransactionService();
        while(turn){
            System.out.println("================ Welcome to Banking services ===================");
            System.out.println("Service We Provide");
            System.out.println("1.Customer\n2.Accounts\n3.Transactions\n4.Exit");
            System.out.print("Select a service to continue : ");
            int option = sc.nextInt();
            switch(option){
                case 1->{
                    customerService.customerMenu();
                }
                case 2 ->{
                    accountService.accountsMenu();
                }
                case 3 ->{
                    transactionService.transactionMenu();
                }
                case 4 ->{
                    turn = false;
                }
            }
        }
    }
}
