import dal.zeynep.miniklarna.model.OrderModel;
import dal.zeynep.miniklarna.model.User;
import dal.zeynep.miniklarna.service.OrderService;
import dal.zeynep.miniklarna.service.PaymentService;
import dal.zeynep.miniklarna.service.UserService;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderServiceTest {
    @Test
    public void shouldUpdateOrderAsPaid() {
        PaymentService paymentService = new PaymentService();
        User user = new User(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        UserService userService = new UserService();
        userService.saveOrUpdateUser(user);
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
        User user = new User(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        UserService userService = new UserService();
        userService.saveOrUpdateUser(user);
        String userEmail = user.getEmail();
        paymentService.purchase(userEmail, 10);
        paymentService.purchase(userEmail, 20);
        List<OrderModel> orderList = orderService.getUserOrders(userEmail);
        assertEquals(2, orderList.size());
    }

    @Test
    public void shouldRetrieveSpecificOrderDetails() {
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService();
        User user = new User(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        UserService userService = new UserService();
        userService.saveOrUpdateUser(user);
        String userEmail = user.getEmail();
        OrderModel actualOrder = paymentService.purchase(userEmail, 10);
        OrderModel expectedOrder = orderService.getOrderDetail(actualOrder.getOrderId());
        Assert.assertTrue(EqualsBuilder.reflectionEquals(expectedOrder, actualOrder));
    }
}
