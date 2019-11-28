package unittest;

import dal.zeynep.miniklarna.Application;
import dal.zeynep.miniklarna.model.OrderModel;
import dal.zeynep.miniklarna.model.User;
import dal.zeynep.miniklarna.service.OrderService;
import dal.zeynep.miniklarna.service.PaymentService;
import dal.zeynep.miniklarna.service.UserService;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
public class OrderServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    @Test
    public void shouldUpdateOrderAsPaid() {

        User user = new User(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        userService.saveOrUpdateUser(user);
        String userEmail = user.getEmail();
        OrderModel order = paymentService.purchase(userEmail, 150);
        paymentService.pay(userEmail, order.getOrderId());

        OrderModel persistedOrder = orderService.getOrderDetail(order.getOrderId());
        assertTrue(persistedOrder.isPaid());
    }

    @Test
    public void shouldRetrieveSpecificUserOrders() {

        User user = new User(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        userService.saveOrUpdateUser(user);
        String userEmail = user.getEmail();
        paymentService.purchase(userEmail, 10);
        paymentService.purchase(userEmail, 20);
        List<OrderModel> orderList = orderService.getUserOrders(userEmail);
        assertEquals(2, orderList.size());
    }

    @Test
    public void shouldRetrieveSpecificOrderDetails() {

        User user = new User(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        userService.saveOrUpdateUser(user);
        String userEmail = user.getEmail();
        OrderModel actualOrder = paymentService.purchase(userEmail, 10);
        OrderModel expectedOrder = orderService.getOrderDetail(actualOrder.getOrderId());
        Assert.assertTrue(EqualsBuilder.reflectionEquals(expectedOrder, actualOrder));
    }
}
