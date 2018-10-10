package com.magistr.duck.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/error/access_denied")
    public String accessDenied() {
        return "error/access_denied";
    }
}
