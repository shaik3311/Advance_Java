package DependencyInjectionAndIOC.payment;

import org.springframework.stereotype.Component;

@Component
public class UPIPayment implements PaymentService{
    @Override
    public void makePayment() {
        System.out.println("Payment done with UPI");
    }
}
