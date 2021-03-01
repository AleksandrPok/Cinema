package philharmonic.service;

import org.springframework.stereotype.Service;
import philharmonic.dao.ShoppingCartDao;
import philharmonic.dao.TicketDao;
import philharmonic.model.ConcertSession;
import philharmonic.model.ShoppingCart;
import philharmonic.model.Ticket;
import philharmonic.model.User;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartDao shoppingCartDao;
    private final TicketDao ticketDao;

    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao, TicketDao ticketDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.ticketDao = ticketDao;
    }

    @Override
    public void addSession(ConcertSession concertSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setConcertSession(concertSession);
        ShoppingCart getByUser = shoppingCartDao.getByUser(user);
        getByUser.getTickets().add(ticket);
        ticketDao.add(ticket);
        shoppingCartDao.update(getByUser);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getTickets().clear();
        shoppingCartDao.update(shoppingCart);
    }
}
