package DependencyInjectionAndIOC.payment;

import org.springframework.stereotype.Component;

@Component
public class cardPayment implements PaymentService{
    @Override
    public void makePayment() {
        System.out.println("Payment done with card");
    }
}
