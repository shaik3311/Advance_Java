package PaymentProcessingSystem.Payments;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cardPaymentGateWay")
public class CardPaymentGateWay implements PaymentGateWay{
    @Override
    public void pay(double amount) {
        System.out.println("Amount : "+amount+" is paid with Card");
    }
}
