package dal.zeynep.miniklarna.dto;

import java.util.List;

public class OrderListDto {
    private List<OrderDto> orders;

    public OrderListDto(List<OrderDto> orders) {
        this.orders = orders;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }
}
