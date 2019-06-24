package dal.zeynep.miniklarna;

import dal.zeynep.miniklarna.dto.OrderDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService = new OrderService();

    @RequestMapping(value = "/getOrders/{userEmail}", method = RequestMethod.GET)
    public List<OrderDto> getUserOrders(@PathVariable("userEmail") String userEmail) {
        return orderService.getUserOrders(userEmail);
    }
}
