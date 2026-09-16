package BeanScope.Prototype;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class User {
    private Counter counter;

    public User(Counter counter){
        this.counter = counter;
    }

    public void increaseCount(){
        counter.setCounter(counter.getCounter()+1);
    }
    public void increaseCount(int count){
       counter.setCounter(counter.getCounter()+count);
    }
    public void showCount(){
        System.out.println(counter.getCounter());
    }
}
