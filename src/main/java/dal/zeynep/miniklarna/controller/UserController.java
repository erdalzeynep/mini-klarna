package dal.zeynep.miniklarna.controller;

import dal.zeynep.miniklarna.dto.UserSignUpDto;
import dal.zeynep.miniklarna.model.SignUpRequest;
import dal.zeynep.miniklarna.model.User;
import dal.zeynep.miniklarna.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserDebt", method = RequestMethod.GET)
    public Map<String, Integer> getUserDebt() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Map<String, Integer> result = new HashMap<>();
        result.put("totalDebt", userService.getUserDebt(userEmail));
        return result;
    }

    @PostMapping("/signUp")
    public UserSignUpDto newUser(@RequestBody SignUpRequest signUpRequest) {
        User user = userService.newUser(signUpRequest.getUserEmail(), signUpRequest.getPassword());
        return new UserSignUpDto(user.getEmail());
    }
}
