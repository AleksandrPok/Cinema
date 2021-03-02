package philharmonic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import philharmonic.model.dto.ShoppingCartResponseDto;
import philharmonic.service.ConcertSessionService;
import philharmonic.service.ShoppingCartService;
import philharmonic.service.UserService;
import philharmonic.service.mappers.ShoppingCartMapper;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ConcertSessionService concertSessionService;
    private final UserService userService;
    private final ShoppingCartMapper shoppingCartMapper;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  ConcertSessionService concertSessionService,
                                  UserService userService,
                                  ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartService = shoppingCartService;
        this.concertSessionService = concertSessionService;
        this.userService = userService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @PostMapping("/concert-session")
    public void addSession(Authentication authentication, @RequestParam Long movieSessionId) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        shoppingCartService.addSession(concertSessionService.get(movieSessionId),
                userService.findByEmail(email).get());
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        return shoppingCartMapper.mapToDto(shoppingCartService
                .getByUser(userService.findByEmail(email).get()));
    }
}
