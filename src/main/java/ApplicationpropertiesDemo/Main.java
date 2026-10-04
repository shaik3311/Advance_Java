package ApplicationpropertiesDemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class);

        PaymentService paymentService = context.getBean(PaymentService.class);
        System.out.println(paymentService.getPaymentType());
        System.out.println(paymentService.getCurrency());
        System.out.println(paymentService.isEnabled());

    }
}
