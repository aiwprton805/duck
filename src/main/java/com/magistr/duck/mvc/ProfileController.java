package com.magistr.duck.mvc;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.magistr.duck.service.UserService;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @RequestMapping("/profile")
    public String profile(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("user", userService.getUser(username));
        return "profile";
    }
}
