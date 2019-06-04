import model.OrderModel;
import model.User;
import org.hibernate.criterion.Order;
import org.junit.Test;

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
//
//    @Test
//    public void shouldRetrieveSpecificUserOrders() {
//        PaymentEngine engine = new PaymentEngine();
//        User user = new User();
//        String userId = user.getUserEmail();
//        OrderModel order1 = engine.purchase(userId, 10);
//        OrderModel order2 = engine.purchase(userId, 20);
//        List<OrderModel> orderList = engine.getOrdersOfUser(userId);
//        assertEquals(2, orderList.size());
//    }
//
//    @Test
//    public void shouldRetrieveSpecificOrderDetails() {
//        PaymentEngine engine = new PaymentEngine();
//        User user = new User();
//        String userId = user.getUserEmail();
//        OrderModel order = engine.purchase(userId, 10);
//        OrderModel expectedOrder = engine.getOrderDetail(order.getOrderId());
//        assertEquals(expectedOrder, order);
//    }
//
//    @Test
//    public void shouldRetrieveSpecificUserDetails() {
//        PaymentEngine engine = new PaymentEngine();
//        User user = new User();
//        user.setTotalDebt(100);
//        String userId = user.getUserEmail();
//        User expectedUser = engine.getUserDetail(userId);
//        assertEquals(expectedUser.getTotalDebt(), user.getTotalDebt());
//    }
}
