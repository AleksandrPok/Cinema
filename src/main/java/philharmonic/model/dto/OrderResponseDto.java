package philharmonic.model.dto;

import java.util.List;

public class OrderResponseDto {
    private Long id;
    private List<Long> ticketIds;
    private String orderDate;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getTickets() {
        return ticketIds;
    }

    public void setTickets(List<Long> tickets) {
        this.ticketIds = tickets;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
