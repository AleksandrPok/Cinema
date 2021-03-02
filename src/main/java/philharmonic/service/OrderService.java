package philharmonic.service;

import java.util.List;
import philharmonic.model.Order;
import philharmonic.model.ShoppingCart;
import philharmonic.model.User;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
