package dal.zeynep.miniklarna.controller;

import dal.zeynep.miniklarna.model.OrderModel;
import dal.zeynep.miniklarna.service.PaymentService;
import dal.zeynep.miniklarna.dto.OrderDto;
import dal.zeynep.miniklarna.dto.PaymentDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService paymentService = new PaymentService();

    @RequestMapping(value = "/purchase/{userEmail}/{price}", method = RequestMethod.POST)
    public OrderDto purchase(@PathVariable("userEmail") String userEmail,
                             @PathVariable("price") Integer price) {
        OrderModel order = paymentService.purchase(userEmail, price);
        return new OrderDto(order.getOrderId(), order.isPaid(), order.isSuccessful(), order.getPrice());
    }

    @RequestMapping(value = "/pay/{userEmail}/{orderId}", method = RequestMethod.POST)
    public PaymentDto purchase(@PathVariable("userEmail") String userEmail,
                               @PathVariable("orderId") int orderId) {
        OrderModel order = paymentService.pay(userEmail, orderId);
        return new PaymentDto(order.isPaid(), order.getPrice());
    }
}
