package BankingAndTransaction;

import BankingAndTransaction.service.AccountService;
import BankingAndTransaction.service.CustomerService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean turn = true;
        CustomerService customerService = new CustomerService();
        AccountService accountService = new AccountService();
        while(turn){
            System.out.println("================ Welcome to Banking services ===================");
            System.out.println("Service We Provide");
            System.out.println("1.Customer\n2.Accounts");
            System.out.print("Select a service to continue : ");
            int option = sc.nextInt();
            switch(option){
                case 1->{
                    customerService.customerMenu();
                }
                case 2 ->{
                    accountService.accountsMenu();
                }
                case 5 ->{
                    turn = false;
                }
            }
        }
    }
}
