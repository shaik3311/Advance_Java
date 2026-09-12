package PaymentProcessingSystem.Payments;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("payment")
public class PaymentService {

    PaymentGateWay paymentGateWay;

    public PaymentService(@Qualifier("walletPaymentGateWay") PaymentGateWay paymentGateWay){
        this.paymentGateWay = paymentGateWay;
    }

    public void makePayment(double amount){
        System.out.println("Payment Initiated");
        paymentGateWay.pay(amount);
    }
}
