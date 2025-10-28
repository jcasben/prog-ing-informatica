package cat.uib.eps.gein.aas.designlab1.ex1;

import cat.uib.eps.gein.aas.designlab1.ex1.channel.ChannelStrategy;
import cat.uib.eps.gein.aas.designlab1.ex1.email.EmailSender;
import cat.uib.eps.gein.aas.designlab1.ex1.repository.OrderRepository;

public class OrderManager {
    private static final double IVA = 0.21;
    private OrderRepository orderRepository;
    private ChannelStrategy channelStrategy;
    private EmailSender emailSender;

    public OrderManager(
            OrderRepository orderRepository,
            ChannelStrategy channelStrategy,
            EmailSender emailSender
    ) {
        this.orderRepository = orderRepository;
        this.channelStrategy = channelStrategy;
        this.emailSender = emailSender;
    }

    public void process(Order order) {
        double total = channelStrategy.calculateTotal(order.getSubtotal(), IVA);
        orderRepository.save(order);
        emailSender.send(order.getCustomerEmail(), "Order " + order.getId() + " total " + total);
    }
}
