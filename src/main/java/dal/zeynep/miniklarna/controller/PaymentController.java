package dal.zeynep.miniklarna.controller;

import dal.zeynep.miniklarna.model.OrderModel;
import dal.zeynep.miniklarna.service.PaymentService;
import dal.zeynep.miniklarna.dto.OrderDto;
import dal.zeynep.miniklarna.dto.PaymentDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService paymentService = new PaymentService();

    @RequestMapping(value = "/purchase/{price}", method = RequestMethod.POST)
    public OrderDto purchase(@PathVariable("price") Integer price) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        OrderModel order = paymentService.purchase(userEmail, price);
        return new OrderDto(order.getOrderId(), order.isPaid(), order.getIsSuccessful(), order.getPrice());
    }

    @RequestMapping(value = "/pay/{orderId}", method = RequestMethod.POST)
    public PaymentDto purchase(@PathVariable("orderId") int orderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        OrderModel order = paymentService.pay(userEmail, orderId);
        return new PaymentDto(order.isPaid(), order.getPrice());
    }
}
