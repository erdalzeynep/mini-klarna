package dal.zeynep.miniklarna.service;

import dal.zeynep.miniklarna.model.OrderModel;
import dal.zeynep.miniklarna.model.User;

public class PaymentService {

    private UserService userService = new UserService();
    private OrderService orderService = new OrderService();

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

    public OrderModel pay(String userEmail, int orderId) {
        User user = userService.getUserDetail(userEmail);
        OrderModel order = orderService.getOrderDetail(orderId);
        if (order.isSuccessful()) {
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
