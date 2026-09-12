package DependencyInjectionAndIOC;

import DependencyInjectionAndIOC.payment.PaymentService;
import org.springframework.stereotype.Component;

@Component("order")
public class OrderService {
    PaymentService paymentService;
    public OrderService(PaymentService paymentService){
        this.paymentService = paymentService;
    }
    public void placeOrder(){
        paymentService.makePayment();
        System.out.println("Order placed");
    }
}
