package unittest;

import dal.zeynep.miniklarna.model.OrderModel;
import dal.zeynep.miniklarna.model.User;
import dal.zeynep.miniklarna.service.PaymentService;
import dal.zeynep.miniklarna.service.UserService;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PaymentServiceTest {

    @Test
    public void shouldNotPurchaseMoreThanUserLimit() {
        PaymentService paymentService = new PaymentService();
        User user = new User(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        UserService userService = new UserService();
        userService.saveOrUpdateUser(user);
        assertFalse(paymentService.purchase(user.getEmail(), User.LIMIT + 1).isSuccessful());
    }

    @Test
    public void shouldRetrieveSpecificUserDetails() {
        UserService userService = new UserService();
        String userEmail = UUID.randomUUID().toString();
        User user = new User(userEmail, UUID.randomUUID().toString());
        user.setTotalDebt(10);
        userService.saveOrUpdateUser(user);
        User expectedUser = userService.getUserDetail(userEmail);
        Assert.assertTrue(EqualsBuilder.reflectionEquals(expectedUser, user));
    }

    @Test
    public void shouldNotIncreaseTotalDebtAfterRejectedPurchase() {
        UserService userService = new UserService();
        PaymentService paymentService = new PaymentService();
        String userEmail = UUID.randomUUID().toString();
        User user = new User(userEmail, UUID.randomUUID().toString());
        userService.saveOrUpdateUser(user);
        Integer debtBeforePurchase = user.getTotalDebt();
        paymentService.purchase(userEmail, User.LIMIT + 1);
        userService.saveOrUpdateUser(user);
        Integer debtAfterPurchase = userService.getUserDetail(userEmail).getTotalDebt();
        assertEquals(debtBeforePurchase, debtAfterPurchase);
    }

    @Test
    public void shouldNotDecreaseTotalDebtAfterPaymentForRejectedOrder() {
        UserService userService = new UserService();
        PaymentService paymentService = new PaymentService();
        String userEmail = UUID.randomUUID().toString();
        User user = new User(userEmail, UUID.randomUUID().toString());
        userService.saveOrUpdateUser(user);
        Integer debtBeforePurchase = user.getTotalDebt();
        OrderModel orderModel = paymentService.purchase(userEmail, User.LIMIT + 1);
        paymentService.pay(userEmail, orderModel.getOrderId());
        userService.saveOrUpdateUser(user);
        Integer debtAfterPurchase = userService.getUserDetail(userEmail).getTotalDebt();
        assertEquals(debtBeforePurchase, debtAfterPurchase);
    }
}
