package BeanScope.Prototype;

import CircularDependencyDemo.simple.A;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        User s1 = context.getBean(User.class);
        User s2 = context.getBean(User.class);
        System.out.println("Initial count value : ");
        s1.showCount();
        s2.showCount();

        s1.increaseCount(20);
        s2.increaseCount();

        System.out.println("Count value after operations : ");

        s1.showCount();
        s2.showCount();
    }
}
