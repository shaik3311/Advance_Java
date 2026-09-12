package PaymentProcessingSystem;

import PaymentProcessingSystem.Payments.PaymentService;
import PaymentProcessingSystem.configs.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        PaymentService paymentService = (PaymentService) context.getBean("payment");
        paymentService.makePayment(200);
    }
}
