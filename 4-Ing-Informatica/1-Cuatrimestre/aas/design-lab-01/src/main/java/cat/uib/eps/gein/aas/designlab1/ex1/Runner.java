package cat.uib.eps.gein.aas.designlab1.ex1;

import cat.uib.eps.gein.aas.designlab1.ex1.channel.WebChannel;
import cat.uib.eps.gein.aas.designlab1.ex1.email.EmailSenderImpl;
import cat.uib.eps.gein.aas.designlab1.ex1.repository.MysqlOrderRepository;

public class Runner {

    public static void main(String[] args) {
        // Example usage

        OrderManager manager = new OrderManager(
                new MysqlOrderRepository(),
                new WebChannel(),
                new EmailSenderImpl()
        );
        Order order = new Order();
        order.setId("test-order");
        order.setSubtotal(135);
        order.setCustomerEmail("raimon@test.com");
        order.setType("test-type");
        manager.process(order);
    }

}
