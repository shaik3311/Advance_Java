package XMLBasedConfiguration;

public class UpiPaymentService implements PaymentService{
    @Override
    public void pay() {
        System.out.println("Payment done with UPI");
    }
}
