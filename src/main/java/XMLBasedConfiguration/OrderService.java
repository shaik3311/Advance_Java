package XMLBasedConfiguration;

import java.lang.reflect.Constructor;

public class OrderService {
    private PaymentService paymentService;

//    Constructor for injection
//    public OrderService(PaymentService paymentService){
//        this.paymentService = paymentService;
//    }
    public void setPaymentService(PaymentService paymentService){
        this.paymentService = paymentService;
    }
    public void placeOrder(){
        paymentService.pay();
        System.out.println("Order placed successfully");
    }
}
