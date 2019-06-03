import java.util.ArrayList;
import java.util.List;

public class PaymentEngine {

    public Order purchase(String userId, Integer price) {
        User user = getUserDetail(userId);
        boolean isSuccessful = price <= User.LIMIT;
        user.setTotalDebt(user.getTotalDebt() + price);
        return new Order(userId, price, isSuccessful);
    }

    public void pay(String userID, String orderId) {
        User user = getUserDetail(userID);
        Order order = getOrderDetail(orderId);
        order.setPaid(true);
        Integer updatedDebt = user.getTotalDebt() - order.getPrice();
        user.setTotalDebt(updatedDebt);
    }

    public List<Order> getOrderListOfUser(String userId) {
        List<Order> orders = new ArrayList<>();

        return orders;
    }

    public Order getOrderDetail(String orderId) {
        User user = new User();
        return new Order(user.getId(), 100, true);
    }

    public User getUserDetail(String userId) {
        return new User();

    }

}
