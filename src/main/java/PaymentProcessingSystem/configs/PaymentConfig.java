package PaymentProcessingSystem.configs;

import PaymentProcessingSystem.Payments.PaymentGateWay;
import PaymentProcessingSystem.Payments.WalletPaymentGateWay;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {

    @Bean
    public PaymentGateWay walletPaymentGateWay(){
        return new WalletPaymentGateWay();
    }
}
