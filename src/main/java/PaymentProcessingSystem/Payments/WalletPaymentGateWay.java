package PaymentProcessingSystem.Payments;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("walletPaymentGateWay")
public class WalletPaymentGateWay implements PaymentGateWay{
    @Override
    public void pay(double amount) {
        System.out.println("Amount : "+amount+" is paid with Wallet");
    }
}
