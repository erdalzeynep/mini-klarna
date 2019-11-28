package dal.zeynep.miniklarna.service;

import dal.zeynep.miniklarna.model.OrderModel;
import dal.zeynep.miniklarna.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    private final UserService userService;

    private final OrderService orderService;

    @Autowired
    public PaymentService(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    public OrderModel purchase(String userEmail, Integer price) {

        User user = userService.getUserDetail(userEmail);
        boolean isSuccessful = price <= User.LIMIT;
        if (isSuccessful) {
            user.setTotalDebt(user.getTotalDebt() + price);
        }
        userService.saveOrUpdateUser(user);
        OrderModel order = new OrderModel(userEmail, price, isSuccessful);
        orderService.saveOrUpdateOrder(order);
        return order;
    }

    public OrderModel pay(String userEmail, long orderId) {
        User user = userService.getUserDetail(userEmail);
        OrderModel order = orderService.getOrderDetail(orderId);
        if (order.getIsSuccessful()) {
            order.setPaid(true);
            Integer updatedDebt = user.getTotalDebt() - order.getPrice();
            user.setTotalDebt(updatedDebt);
            userService.saveOrUpdateUser(user);
            orderService.saveOrUpdateOrder(order);
            return order;
        } else {
            return null;
        }
    }
}
