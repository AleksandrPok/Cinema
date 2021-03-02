package philharmonic.service.mappers;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import philharmonic.model.Order;
import philharmonic.model.Ticket;
import philharmonic.model.dto.OrderResponseDto;

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
