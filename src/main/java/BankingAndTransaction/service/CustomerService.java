package BankingAndTransaction.service;

import BankingAndTransaction.model.Customer;
import BankingAndTransaction.repository.CustomerRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

public class CustomerService {
    Scanner sc = new Scanner(System.in);
    public static Customer mapRows(ResultSet res)throws SQLException {
//        Mapping SQL Row into object
        Customer customer = new Customer();
        customer.setId(res.getLong(1));
        customer.setName(res.getString(2));
        customer.setEmail(res.getString(3));
        customer.setPhone(res.getString(4));
        Timestamp timestamp = res.getTimestamp(5);
        customer.setCreatedAt(timestamp.toLocalDateTime());
        return customer;
    }


    public void customerMenu(){
        boolean turn = true;
        while(turn){
            System.out.println("======== Welcome to Customer page ========");
            System.out.println("1.Create Customer\n2.Update Customer\n3.Search Customer\n4.Delete Customer\n5.Exit");
            System.out.print("Select an operation : ");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option){
                case 1->{
                    System.out.println("Enter details to Create Customer");
                    System.out.print("Name : ");
                    String name = sc.nextLine();
                    System.out.print("Email : ");
                    String email = sc.nextLine();
                    System.out.print("Phone number : ");
                    String phone_no = sc.nextLine();
//                    Object of CutomerRepo for accessing methods
                    CustomerRepository customerRepository = new CustomerRepository();
//                    Creating Customer object
                    Customer customer = new Customer(1,name,email,phone_no, LocalDateTime.now());
//                    passing Customer object to createCustomer mthods present in customer repo
                    customerRepository.createCustomer(customer);
                }
                case 2 ->{
                    System.out.println("Fields to update a customer ");
                    System.out.println("1.Name\n2.Email\n3.Phone Number");
                    System.out.print("Select a field to update : ");
                    int field = sc.nextInt();
                    switch (field){
                        case 1->{
                            System.out.println("Update Name of customer");
                            System.out.print("Enter customer id : ");
                            long id = sc.nextLong();
                            sc.nextLine();
                            System.out.print("Enter new name : ");
                            String newName = sc.nextLine();

                            CustomerRepository customerRepository = new CustomerRepository();
                            customerRepository.updateCustomerUsername(id,newName);
                        }
                        case 2->{
                            System.out.println("Update Email of customer");
                            System.out.print("Enter customer id : ");
                            long id = sc.nextLong();
                            sc.nextLine();
                            System.out.print("Enter new Email : ");
                            String newEmail = sc.nextLine();

                            CustomerRepository customerRepository = new CustomerRepository();
                            customerRepository.updateCustomerEmail(id,newEmail);
                        }
                        case 3 ->{
                            System.out.println("Update Phone of customer");
                            System.out.print("Enter customer id : ");
                            long id = sc.nextLong();
                            sc.nextLine();
                            System.out.print("Enter new Phone : ");
                            String newPhone = sc.nextLine();

                            CustomerRepository customerRepository = new CustomerRepository();
                            customerRepository.updateCustomerPhone(id,newPhone);
                        }
                    }
                }
                case 3 ->{
                    System.out.println("Enter Details to search the customer");
                    System.out.print("Enter customer id : ");
                    long id = sc.nextLong();
                    CustomerRepository customerRepository = new CustomerRepository();
                    customerRepository.searchCustomerById(id);
                }
                case 4 ->{
                    System.out.println("Enter Details to delete the customer");
                    System.out.print("Enter customer id : ");
                    long id = sc.nextLong();
                    CustomerRepository customerRepository = new CustomerRepository();
                    customerRepository.deleteCustomerById(id);
                }
                case 5 ->{
                    turn=false;
                }
            }
        }
    }
}
