package com.magistr.duck.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.magistr.duck.entity.User;
import com.magistr.duck.security.SecurityService;
import com.magistr.duck.service.UserService;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping("/index.html")
    public String index() {
        return "index";
    }

    @RequestMapping("/login.html")
    public String login() {
        return "login";
    }

    @RequestMapping("/registration.html")
    public String registration() {
        return "registration";
    }

    @RequestMapping(path = "/registration/sign_up", method = RequestMethod.POST)
    public String registration(@RequestParam String name, @RequestParam CharSequence password,
            @RequestParam CharSequence retypePassword, HttpServletRequest request) {

        var user = new User();
        user.setName(name);

        userService.saveUser(user, password);
        securityService.autoLogin(request, name, password);

        return "redirect:/profile.html";
    }

}
