package cat.uib.eps.gein.aas.designlab1.ex1.repository;

import cat.uib.eps.gein.aas.designlab1.ex1.Order;

public class MysqlOrderRepository implements OrderRepository {

    @Override
    public void save(Order order) {
        try {
            System.out.println("Saving order: " + order.getId());
            // MySQL-dependant code
            System.out.println("Order saved!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
