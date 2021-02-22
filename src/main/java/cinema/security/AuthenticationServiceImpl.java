package cinema.security;

import cinema.model.User;
import cinema.service.RoleService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     RoleService roleService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.roleService = roleService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(List.of(roleService.getRoleByName("USER")));
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
