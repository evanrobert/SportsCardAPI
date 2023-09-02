package com.evanrobert.Json.API.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginHtmlController {
    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

}
