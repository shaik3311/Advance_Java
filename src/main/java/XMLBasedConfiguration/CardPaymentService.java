package XMLBasedConfiguration;

public class CardPaymentService implements PaymentService{
    @Override
    public void pay() {
        System.out.println("Payment done with Card");
    }
}
