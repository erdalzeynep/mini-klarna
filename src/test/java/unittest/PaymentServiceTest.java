package unittest;

import dal.zeynep.miniklarna.Application;
import dal.zeynep.miniklarna.model.OrderModel;
import dal.zeynep.miniklarna.model.User;
import dal.zeynep.miniklarna.service.PaymentService;
import dal.zeynep.miniklarna.service.UserService;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
public class PaymentServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @Test
    public void shouldNotPurchaseMoreThanUserLimit() {
        User user = new User(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        userService.saveOrUpdateUser(user);
        assertFalse(paymentService.purchase(user.getEmail(), User.LIMIT + 1).getIsSuccessful());
    }

    @Test
    public void shouldRetrieveSpecificUserDetails() {
        String userEmail = UUID.randomUUID().toString();
        User user = new User(userEmail, UUID.randomUUID().toString());
        user.setTotalDebt(10);
        userService.saveOrUpdateUser(user);
        User expectedUser = userService.getUserDetail(userEmail);
        Assert.assertTrue(EqualsBuilder.reflectionEquals(expectedUser, user));
    }

    @Test
    public void shouldNotIncreaseTotalDebtAfterRejectedPurchase() {

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
