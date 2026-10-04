package ApplicationpropertiesDemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix = "payment-gateway")
public class PaymentProperties {
    @Value("${payment-gateway.payment-type}")
    private String paymentType;
    @Value("${payment-gateway.currency}")
    private String currency;
    @Value("${payment-gateway.enabled}")
    private boolean enabled;

//    private String paymentType;
//    private String currency;
//    private boolean enabled;
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
