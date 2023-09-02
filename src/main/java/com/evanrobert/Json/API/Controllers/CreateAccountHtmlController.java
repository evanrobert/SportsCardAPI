package com.evanrobert.Json.API.Controllers;

import com.evanrobert.Json.API.Model.UserDetailService;
import com.evanrobert.Json.API.Repos.UserLoginDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreateAccountHtmlController {
    @Autowired
    UserLoginDetailsRepo userLoginDetailsRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping("/create/account")
    public String createAccount(Model model){
        model.addAttribute("userDetailService", new UserDetailService());
        return "createAccount";
    }
    @PostMapping("/create/new/account")
    public String createNewAccount(@ModelAttribute UserDetailService userDetailService, String username, String password) {
        userDetailService.setUsername(username);
        userDetailService.setPassword(password);
        userLoginDetailsRepo.save(userDetailService);
        return "cards";
    }

}
