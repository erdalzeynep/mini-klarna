import model.OrderModel;
import model.User;

public class PaymentService {

    private UserService userService = new UserService();
    private OrderService orderService = new OrderService();

    public OrderModel purchase(String userEmail, Integer price) {

        User user = userService.getOrCreateUser(userEmail);
        boolean isSuccessful = price <= User.LIMIT;
        user.setTotalDebt(user.getTotalDebt() + price);
        OrderModel order = new OrderModel(userEmail, price, isSuccessful);
        orderService.saveOrUpdateOrder(order);
        return order;
    }

    public void pay(String userEmail, int orderId) {
        User user = userService.getOrCreateUser(userEmail);
        OrderModel order = orderService.getOrderDetail(orderId);
        order.setPaid(true);
        Integer updatedDebt = user.getTotalDebt() - order.getPrice();
        user.setTotalDebt(updatedDebt);
        userService.saveOrUpdateUser(user);
        orderService.saveOrUpdateOrder(order);
    }
}
