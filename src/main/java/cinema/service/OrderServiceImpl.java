package cinema.service;

import cinema.dao.OrderDao;
import cinema.lib.Inject;
import cinema.lib.ServiceImpl;
import cinema.model.Order;
import cinema.model.ShoppingCart;
import cinema.model.Ticket;
import cinema.model.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        List<Ticket> tickets = shoppingCart.getTickets();
        order.setTickets(new ArrayList<>(tickets));
        order.setUser(shoppingCart.getUser());
        order.setOrderDate(LocalDateTime.now());
        orderDao.add(order);
        shoppingCartService.clear(shoppingCart);
        return order;
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
