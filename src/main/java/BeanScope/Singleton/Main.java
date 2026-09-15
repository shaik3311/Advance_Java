package BeanScope.Singleton;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        User1 u1 = context.getBean(User1.class);
        User2 u2 = context.getBean(User2.class);

        u1.getBalance();
        u2.getBalance();

        u1.withDraw(500);

        u1.getBalance();
        u2.getBalance();

    }
}
