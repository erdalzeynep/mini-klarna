package dal.zeynep.miniklarna.controller;

import dal.zeynep.miniklarna.service.OrderService;
import dal.zeynep.miniklarna.dto.OrderDto;
import dal.zeynep.miniklarna.model.OrderModel;
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
        List<OrderDto> orderDtos = new ArrayList<>();
        List<OrderModel> orderModels = orderService.getUserOrders(userEmail);
        if (orderModels == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Actor Not Found");
        } else {
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
