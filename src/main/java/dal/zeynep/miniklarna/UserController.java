package dal.zeynep.miniklarna;

import dal.zeynep.miniklarna.dto.UserDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService = new UserService();

    @RequestMapping(value = "/getUserDebt/{userEmail}", method = RequestMethod.GET)
    public UserDto getUserDebt(@PathVariable("userEmail") String userEmail) {
        return userService.getUserDebt(userEmail);
    }
}
