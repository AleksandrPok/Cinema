package cinema.service;

import cinema.dao.OrderDao;
import cinema.lib.Inject;
import cinema.lib.ServiceImpl;
import cinema.model.Order;
import cinema.model.ShoppingCart;
import cinema.model.User;
import java.time.LocalDateTime;
import java.util.List;

@ServiceImpl
public class OrderServiceImpl implements OrderService {
    @Inject
    private ShoppingCartService shoppingCartService;
    @Inject
    private OrderDao orderDao;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setTickets(shoppingCart.getTickets());
        order.setUser(shoppingCart.getUser());
        orderDao.add(order);
        shoppingCartService.clear(shoppingCart);
        return order;
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
