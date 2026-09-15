package CircularDependencyDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {
//    @Autowired
    public OrderService orderService;

//    public PaymentService(OrderService orderService){
//        this.orderService = orderService;
//    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void pay(){
        orderService.getOrderDetails();
        System.out.println("Payment Done");
    }
}
