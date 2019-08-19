package dal.zeynep.miniklarna.controller;

import dal.zeynep.miniklarna.model.User;
import dal.zeynep.miniklarna.service.OrderService;
import dal.zeynep.miniklarna.dto.OrderDto;
import dal.zeynep.miniklarna.model.OrderModel;
import dal.zeynep.miniklarna.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService = new OrderService();

    @RequestMapping(value = "/getOrders/{userEmail}", method = RequestMethod.GET)
    public List<OrderDto> getUserOrders(@PathVariable("userEmail") String userEmail) {
        UserService userService = new UserService();
        List<OrderDto> orderDtos = new ArrayList<>();
        User user = userService.getUserDetail(userEmail);
        if (user == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Users Found with Given Email");
        } else {
            List<OrderModel> orderModels = orderService.getUserOrders(userEmail);
            for (OrderModel orderModel : orderModels) {
                orderDtos.add(new OrderDto(orderModel.getOrderId(), orderModel.isPaid(),
                        orderModel.isSuccessful(), orderModel.getPrice()));
            }
            return orderDtos;
        }
    }

    @RequestMapping(value = "/getOrderDetail/{orderId}", method = RequestMethod.GET)
    public OrderModel getOrderDetail(@PathVariable("orderId") int orderId) {
        return orderService.getOrderDetail(orderId);
    }
}
