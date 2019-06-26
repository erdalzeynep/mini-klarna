import dal.zeynep.miniklarna.dto.OrderDto;
import dal.zeynep.miniklarna.model.OrderModel;
import dal.zeynep.miniklarna.model.User;
import dal.zeynep.miniklarna.service.OrderService;
import dal.zeynep.miniklarna.service.PaymentService;
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
        User user = new User(UUID.randomUUID().toString());
        String userEmail = user.getEmail();
        OrderDto order = paymentService.purchase(userEmail, 150);
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
        paymentService.purchase(userEmail, 10);
        paymentService.purchase(userEmail, 20);
        List<OrderDto> orderList = orderService.getUserOrders(userEmail);
        assertEquals(2, orderList.size());
    }

    @Test
    public void shouldRetrieveSpecificOrderDetails() {
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService();
        User user = new User(UUID.randomUUID().toString());
        String userEmail = user.getEmail();
        OrderDto orderDto = paymentService.purchase(userEmail, 10);
        OrderModel expectedOrder = orderService.getOrderDetail(orderDto.getOrderId());
        OrderDto expectedOrderDto  = new OrderDto(expectedOrder.getOrderId() , expectedOrder. isPaid(),
                expectedOrder.isSuccessful(), expectedOrder.getPrice());
        Assert.assertTrue(EqualsBuilder.reflectionEquals(expectedOrderDto, orderDto));
    }
}
