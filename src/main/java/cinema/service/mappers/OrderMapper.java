package cinema.service.mappers;

import cinema.model.Order;
import cinema.model.Ticket;
import cinema.model.dto.OrderResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setOrderDate(order.getOrderDate().toString());
        orderResponseDto.setEmail(order.getUser().getEmail());
        orderResponseDto.setTickets(order.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return orderResponseDto;
    }
}
