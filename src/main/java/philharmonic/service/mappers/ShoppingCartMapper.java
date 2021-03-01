package philharmonic.service.mappers;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import philharmonic.model.ShoppingCart;
import philharmonic.model.Ticket;
import philharmonic.model.dto.ShoppingCartResponseDto;

@Component
public class ShoppingCartMapper {
    public ShoppingCartResponseDto mapToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setUserId(shoppingCart.getId());
        shoppingCartResponseDto.setTicketIds(shoppingCart.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return shoppingCartResponseDto;
    }
}
