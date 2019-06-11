import model.OrderModel;
import model.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class PaymentEngineTest {

    @Test
    public void shouldNotPurchaseMoreThanUserLimit() {
        PaymentService paymentService = new PaymentService();
        User user = new User(UUID.randomUUID().toString());
        assertFalse(paymentService.purchase(user.getEmail(), User.LIMIT + 1).isSuccessful());
    }

    @Test
    public void shouldUpdateOrderAsPaid() {
        PaymentService paymentService = new PaymentService();
        User user = new User(UUID.randomUUID().toString());
        String userEmail = user.getEmail();
        OrderModel order = paymentService.purchase(userEmail, 150);
        paymentService.pay(userEmail, order.getOrderId());
        OrderService orderService = new OrderService();
        OrderModel persistedOrder = orderService.getOrderDetail(order.getOrderId());
        assertTrue(persistedOrder.isPaid());
    }

    @Test
    public void shouldRetrieveSpecificUserOrders() {
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService();
        User user = new User(UUID.randomUUID().toString());
        String userEmail = user.getEmail();
        OrderModel order1 = paymentService.purchase(userEmail, 10);
        OrderModel order2 = paymentService.purchase(userEmail, 20);
        List<OrderModel> orderList = orderService.getUserOrders(userEmail);
        assertEquals(2, orderList.size());
    }

    @Test
    public void shouldRetrieveSpecificOrderDetails() {
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService();
        User user = new User(UUID.randomUUID().toString());
        String userEmail = user.getEmail();
        OrderModel order = paymentService.purchase(userEmail, 10);
        OrderModel expectedOrder = orderService.getOrderDetail(order.getOrderId());
        Assert.assertTrue(EqualsBuilder.reflectionEquals(expectedOrder, order));
    }

    @Test
    public void shouldRetrieveSpecificUserDetails() {
        UserService userService = new UserService();
        String userEmail = UUID.randomUUID().toString();
        User user = userService.getOrCreateUser(userEmail);
        user.setTotalDebt(10);
        userService.saveOrUpdateUser(user);
        User expectedUser = userService.getUserDetail(userEmail);
        Assert.assertTrue(EqualsBuilder.reflectionEquals(expectedUser, user));
    }
}
