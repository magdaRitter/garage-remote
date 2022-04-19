package at.home.garageremote.controller;

import at.home.garageremote.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "/login"})
public class LoginController {

    @GetMapping
    public String getLoginForm(){
        return "login";
    }

    @PostMapping
    public String login(@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model){

        //TODO handle login logic
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        String expectedUsername = "admin";
        String expectedPassword = "admin";

        if(!expectedUsername.equals(username) || !expectedPassword.equals(password)) {
            model.addAttribute("invalidCredentials", true);
            return "redirect:/login";
        }

        return "redirect:/home";
    }
}
