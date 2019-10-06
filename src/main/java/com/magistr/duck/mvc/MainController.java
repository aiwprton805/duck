package com.magistr.duck.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.magistr.duck.entity.User;
import com.magistr.duck.security.SecurityService;
import com.magistr.duck.service.UserService;

@Controller
public class MainController {

    private final UserService userService;

    private final SecurityService securityService;

    @Autowired
    public MainController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping({"", "/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping(path = "/registration/signup")
    public String registration(@RequestParam String name, @RequestParam CharSequence password,
            @RequestParam CharSequence retypePassword, HttpServletRequest request) {

        var user = new User();
        user.setName(name);

        userService.saveUser(user, password);
        securityService.autoLogin(request, name, password);

        return "redirect:/profile";
    }

}
