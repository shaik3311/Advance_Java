package PaymentProcessingSystem.Payments;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("upiPaymentGateWay")
//@Primary
public class UPIPaymentGateWay implements PaymentGateWay{
    @Override
    public void pay(double amount) {
        System.out.println("Amount : "+amount+" Paid with UPI");
    }
}
