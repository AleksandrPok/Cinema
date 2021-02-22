package cinema.controller;

import cinema.model.Role;
import cinema.model.Roles;
import cinema.model.User;
import cinema.service.RoleService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InjectData {
    private final RoleService roleService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public InjectData(RoleService roleService,
                      UserService userService,
                      ShoppingCartService shoppingCartService) {
        this.roleService = roleService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostConstruct
    private void inject() {
        Role userRole = new Role();
        userRole.setRoleName(Roles.USER);
        roleService.add(userRole);
        Role adminRole = new Role();
        adminRole.setRoleName(Roles.ADMIN);
        roleService.add(adminRole);
        User admin = new User();
        admin.setRoles(List.of(adminRole));
        admin.setEmail("admin@admin.admin");
        admin.setPassword("admin");
        userService.add(admin);
        shoppingCartService.registerNewShoppingCart(admin);
    }
}
