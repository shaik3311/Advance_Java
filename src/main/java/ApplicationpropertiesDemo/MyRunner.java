package ApplicationpropertiesDemo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class MyRunner implements ApplicationRunner {
    private PaymentService paymentService;
    public MyRunner(PaymentService paymentService){
        this.paymentService = paymentService;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        paymentService.print();
    }
}
