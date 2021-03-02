package philharmonic.service;

import philharmonic.model.ConcertSession;
import philharmonic.model.ShoppingCart;
import philharmonic.model.User;

public interface ShoppingCartService {
    void addSession(ConcertSession concertSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
