import dal.zeynep.miniklarna.controller.OrderController;
import org.junit.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public class OrderControllerTest {
    @Test(expected = ResponseStatusException.class)
    public void shouldGetExceptionWhenGettingOrderListForInvalidUser(){
        OrderController orderService = new OrderController();
        orderService.getUserOrders(UUID.randomUUID().toString());
    }
}
