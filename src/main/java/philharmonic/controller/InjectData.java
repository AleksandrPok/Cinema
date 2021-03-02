package philharmonic.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import philharmonic.model.Role;
import philharmonic.model.RoleName;
import philharmonic.model.User;
import philharmonic.service.RoleService;
import philharmonic.service.ShoppingCartService;
import philharmonic.service.UserService;

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
        userRole.setRoleName(RoleName.USER);
        roleService.add(userRole);
        Role adminRole = new Role();
        adminRole.setRoleName(RoleName.ADMIN);
        roleService.add(adminRole);
        User admin = new User();
        admin.setRoles(List.of(adminRole));
        admin.setEmail("admin@admin.admin");
        admin.setPassword("admin");
        userService.add(admin);
        shoppingCartService.registerNewShoppingCart(admin);
    }
}
