package dal.zeynep.miniklarna.controller;

import dal.zeynep.miniklarna.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService = new UserService();

    @RequestMapping(value = "/getUserDebt", method = RequestMethod.GET)
    public int getUserDebt() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return userService.getUserDebt(userEmail);
    }
}
