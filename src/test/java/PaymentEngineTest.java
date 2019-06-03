import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PaymentEngineTest {

    @Test
    public void shouldNotPurchaseMoreThanUserLimit() {
        PaymentEngine engine = new PaymentEngine();
        User user = new User();
        assertFalse(engine.purchase(user.getId(), User.LIMIT + 1).isSuccessful());
    }

    @Test
    public void shouldBeAbleToPaySpecificOrder() {
        PaymentEngine engine = new PaymentEngine();
        User user = new User();
        String userId = user.getId();
        Order order = engine.purchase(userId, 150);
        engine.pay(userId, order.getOrderId());
    }

    @Test
    public void shouldRetrieveSpecificUserOrders() {
        PaymentEngine engine = new PaymentEngine();
        User user = new User();
        String userId = user.getId();
        Order order1 = engine.purchase(userId, 10);
        Order order2 = engine.purchase(userId, 20);
        List<Order> orderList = engine.getOrderListOfUser(userId);
        assertEquals(2, orderList.size());
    }

    @Test
    public void shouldRetrieveSpecificOrderDetails() {
        PaymentEngine engine = new PaymentEngine();
        User user = new User();
        String userId = user.getId();
        Order order = engine.purchase(userId, 10);
        Order expectedOrder = engine.getOrderDetail(order.getOrderId());
        assertEquals(expectedOrder, order);
    }

    @Test
    public void shouldRetrieveSpecificUserDetails() {
        PaymentEngine engine = new PaymentEngine();
        User user = new User();
        user.setTotalDebt(100);
        String userId = user.getId();
        User expectedUser = engine.getUserDetail(userId);
        assertEquals(expectedUser.getTotalDebt(), user.getTotalDebt());
    }
}
