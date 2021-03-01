package philharmonic.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import philharmonic.model.dto.OrderResponseDto;
import philharmonic.service.OrderService;
import philharmonic.service.ShoppingCartService;
import philharmonic.service.UserService;
import philharmonic.service.mappers.OrderMapper;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(ShoppingCartService shoppingCartService,
                           UserService userService, OrderService orderService,
                           OrderMapper orderMapper) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public OrderResponseDto completeOrder(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        return orderMapper.mapToDto(orderService.completeOrder(shoppingCartService
                .getByUser(userService.findByEmail(email).get())));
    }

    @GetMapping
    public List<OrderResponseDto> getByUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        return orderService.getOrdersHistory(userService.findByEmail(email).get()).stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
