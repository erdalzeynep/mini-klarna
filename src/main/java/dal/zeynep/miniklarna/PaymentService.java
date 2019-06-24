package dal.zeynep.miniklarna;

import dal.zeynep.miniklarna.dto.OrderDto;
import dal.zeynep.miniklarna.dto.PaymentDto;
import dal.zeynep.miniklarna.model.OrderModel;
import dal.zeynep.miniklarna.model.User;

public class PaymentService {

    private UserService userService = new UserService();
    private OrderService orderService = new OrderService();

    public OrderDto purchase(String userEmail, Integer price) {

        User user = userService.getOrCreateUser(userEmail);
        boolean isSuccessful = price <= User.LIMIT;
        user.setTotalDebt(user.getTotalDebt() + price);
        userService.saveOrUpdateUser(user);
        OrderModel order = new OrderModel(userEmail, price, isSuccessful);
        orderService.saveOrUpdateOrder(order);
        return new OrderDto(order.getOrderId() , order.isPaid() , isSuccessful, price);
    }

    public PaymentDto pay(String userEmail, int orderId) {
        User user = userService.getUserDetail(userEmail);
        OrderModel order = orderService.getOrderDetail(orderId);
        order.setPaid(true);
        Integer updatedDebt = user.getTotalDebt() - order.getPrice();
        user.setTotalDebt(updatedDebt);
        userService.saveOrUpdateUser(user);
        orderService.saveOrUpdateOrder(order);
        return new PaymentDto(order.isPaid(), order.getPrice());
    }
}
