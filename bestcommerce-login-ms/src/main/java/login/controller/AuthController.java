package login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthController {

    @GetMapping("/check/loginStatus")
    public String loginSuccess() {
        return "login is successful!";
    }

}
