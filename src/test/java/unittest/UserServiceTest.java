package unittest;

import dal.zeynep.miniklarna.Application;
import dal.zeynep.miniklarna.model.User;
import dal.zeynep.miniklarna.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void shouldPersistUserAfterSuccessfulSignUp(){
        String userEmail = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();
        userService.newUser(userEmail, password);

        User persistedUser = userService.getUserDetail(userEmail);
        assertNotNull(persistedUser);
        assertEquals(userEmail, persistedUser.getEmail());
    }
}
