package cinema.model.dto;

import java.util.List;

public class ShoppingCartResponseDto {
    private Long userId;
    private List<Long> tickets;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getTickets() {
        return tickets;
    }

    public void setTickets(List<Long> tickets) {
        this.tickets = tickets;
    }
}
