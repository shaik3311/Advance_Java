package ApplicationpropertiesDemo;

import org.springframework.stereotype.Component;

@Component
public class PaymentService {
    private PaymentProperties paymentProperties;

    public PaymentService(PaymentProperties paymentProperties){
        this.paymentProperties = paymentProperties;
    }

    public String getPaymentType() {
        return paymentProperties.getPaymentType();
    }

    public String getCurrency() {
        return paymentProperties.getCurrency();
    }

    public boolean isEnabled() {
        return paymentProperties.isEnabled();
    }

    public void print(){
        System.out.println(getPaymentType());
        System.out.println(getCurrency());
        System.out.println(isEnabled());
    }

}
