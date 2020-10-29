package pl.coderslab.charity.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

}
