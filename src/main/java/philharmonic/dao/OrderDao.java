package philharmonic.dao;

import java.util.List;
import philharmonic.model.Order;
import philharmonic.model.User;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrderHistory(User user);
}
