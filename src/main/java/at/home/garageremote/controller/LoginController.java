package at.home.garageremote.controller;

import at.home.garageremote.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String getLoginForm(){
        return "login";
    }

    @PostMapping
    public String login(@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model){

        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        String expectedUsername = "admin";
        String expectedPassword = "admin";

        if(!expectedUsername.equals(username) || !expectedPassword.equals(password)) {
            model.addAttribute("invalidCredentials", true);
            return "login";
        }

        return "home";
    }
}
