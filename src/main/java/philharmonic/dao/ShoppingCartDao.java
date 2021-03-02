package philharmonic.dao;

import philharmonic.model.ShoppingCart;
import philharmonic.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
