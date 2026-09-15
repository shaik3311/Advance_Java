package BeanScope.Singleton;

import org.springframework.stereotype.Component;

@Component
public class User1 {
    private Account account;

    public User1(Account account){
        this.account = account;
    }

    public void getBalance(){
        System.out.println(account.getAmount());
    }
    public void withDraw(double amount){
        account.withDraw(amount);
    }
    public void deposit(double amount){
        account.deposit(amount);
    }
}
