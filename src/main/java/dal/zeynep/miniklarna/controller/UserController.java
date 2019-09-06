package dal.zeynep.miniklarna.controller;

import dal.zeynep.miniklarna.dto.UserSignUpDto;
import dal.zeynep.miniklarna.model.SignUpRequest;
import dal.zeynep.miniklarna.model.User;
import dal.zeynep.miniklarna.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService = new UserService();

    @RequestMapping(value = "/getUserDebt", method = RequestMethod.GET)
    public int getUserDebt() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return userService.getUserDebt(userEmail);
    }

    @PostMapping("/signUp")
    public UserSignUpDto newUser(@RequestBody SignUpRequest signUpRequest)
    {
        User user = userService.newUser(signUpRequest.getUserEmail(), signUpRequest.getPassword());
        return new UserSignUpDto(user.getEmail());
    }
}
