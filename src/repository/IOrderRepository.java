package repository;

import model.Order;
import java.util.List;

public interface IOrderRepository {

    List<Order> findAll();

    void saveAll(List<Order> orders);

    Order findById(int id);

    List<Order> findByCustomerId(int customerId);
}
