package dal.zeynep.miniklarna.controller;

import dal.zeynep.miniklarna.dto.OrderListDto;
import dal.zeynep.miniklarna.service.OrderService;
import dal.zeynep.miniklarna.dto.OrderDto;
import dal.zeynep.miniklarna.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/getOrders", method = RequestMethod.GET)
    public OrderListDto getUserOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        List<OrderDto> orderDtos = new ArrayList<>();

        List<OrderModel> orderModels = orderService.getUserOrders(userEmail);
        for (OrderModel orderModel : orderModels) {
            orderDtos.add(new OrderDto(orderModel.getOrderId(), orderModel.isPaid(),
                    orderModel.getIsSuccessful(), orderModel.getPrice()));
        }
        return new OrderListDto(orderDtos);
    }

    @RequestMapping(value = "/getOrderDetail/{orderId}", method = RequestMethod.GET)
    public OrderModel getOrderDetail(@PathVariable("orderId") int orderId) {
        return orderService.getOrderDetail(orderId);
    }
}
