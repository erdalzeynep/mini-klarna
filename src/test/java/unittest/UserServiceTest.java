package unittest;

import dal.zeynep.miniklarna.model.User;
import dal.zeynep.miniklarna.service.UserService;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class UserServiceTest {
    @Test
    public void shouldPersistUserAfterSuccessfulSignUp(){
        UserService userService = new UserService();
        String userEmail = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();
        userService.newUser(userEmail, password);

        User persistedUser = userService.getUserDetail(userEmail);
        assertNotNull(persistedUser);
        assertEquals(userEmail, persistedUser.getEmail());
    }
}
