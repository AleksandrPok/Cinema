package philharmonic.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import philharmonic.dao.OrderDao;
import philharmonic.model.Order;
import philharmonic.model.ShoppingCart;
import philharmonic.model.Ticket;
import philharmonic.model.User;

@Service
public class OrderServiceImpl implements OrderService {
    private final ShoppingCartService shoppingCartService;
    private final OrderDao orderDao;

    public OrderServiceImpl(ShoppingCartService shoppingCartService, OrderDao orderDao) {
        this.shoppingCartService = shoppingCartService;
        this.orderDao = orderDao;
    }

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
