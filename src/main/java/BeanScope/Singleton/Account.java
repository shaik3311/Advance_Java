package BeanScope.Singleton;

import org.springframework.stereotype.Component;

@Component
public class Account {
    private double amount = 1000;


    public double getAmount() {
        return amount;
    }

    public void deposit(double amount){
        this.amount += amount;
        System.out.println("Deposit success");
    }

    public void withDraw(double amount){
        if(amount<=this.amount){
            this.amount -= amount;
            System.out.println("Withdraw success");
        }else{
            System.out.println("Insufficient balance");
        }
    }
}
