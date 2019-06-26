import dal.zeynep.miniklarna.service.OrderService;
import dal.zeynep.miniklarna.service.PaymentService;
import dal.zeynep.miniklarna.service.UserService;
import dal.zeynep.miniklarna.dto.OrderDto;
import dal.zeynep.miniklarna.model.OrderModel;
import dal.zeynep.miniklarna.model.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class PaymentServiceTest {

    @Test
    public void shouldNotPurchaseMoreThanUserLimit() {
        PaymentService paymentService = new PaymentService();
        User user = new User(UUID.randomUUID().toString());
        assertFalse(paymentService.purchase(user.getEmail(), User.LIMIT + 1).isSuccessful());
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

    @Test
    public void shouldNotIncreaseTotalDebtAfterRejectedPurchase(){
        UserService userService = new UserService();
        PaymentService paymentService = new PaymentService();
        String userEmail = UUID.randomUUID().toString();
        User user = userService.getOrCreateUser(userEmail);
        Integer debtBeforePurchase = user.getTotalDebt();
        paymentService.purchase(userEmail, User.LIMIT + 1);
        Integer debtAfterPurchase = userService.getOrCreateUser(userEmail).getTotalDebt();
        assertEquals(debtBeforePurchase, debtAfterPurchase);
    }

    @Test
    public void shouldNotDecreaseTotalDebtAfterPaymentForRejectedOrder(){
        UserService userService = new UserService();
        PaymentService paymentService = new PaymentService();
        String userEmail = UUID.randomUUID().toString();
        User user = userService.getOrCreateUser(userEmail);
        Integer debtBeforePurchase = user.getTotalDebt();
        OrderDto orderDto = paymentService.purchase(userEmail, User.LIMIT + 1);
        paymentService.pay(userEmail, orderDto.getOrderId());
        Integer debtAfterPurchase = userService.getOrCreateUser(userEmail).getTotalDebt();
        assertEquals(debtBeforePurchase, debtAfterPurchase);
    }
}
