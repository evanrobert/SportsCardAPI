package com.evanrobert.Json.API.Controllers;

import com.evanrobert.Json.API.Model.UserDetailService;
import com.evanrobert.Json.API.Model.UserInfo;
import com.evanrobert.Json.API.Repos.UserInformationRepo;
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
    UserInformationRepo userInformationRepo;
    @Autowired
    UserLoginDetailsRepo userLoginDetailsRepo;

    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping("/create/account")
    public String createAccount(Model model){
        model.addAttribute("userDetailService", new UserDetailService());
        model.addAttribute("userInfo",new UserInfo());

        return "createAccount";
    }

    /**
     * Handles the creation of a new user account based on form input.
     * @param userDetailService The UserDetailService object containing user login details.
     * @param username The username obtained from the registration form.
     * @param password The password obtained from the registration form.
     * @return The view name to navigate to after account creation (e.g., "cards").
     */
    @PostMapping("/create/new/account")
    public String createNewAccount(
            @ModelAttribute UserDetailService userDetailService, String username, String password) {

        String encodedPassword = passwordEncoder.encode(password);
        UserInfo userInfo = userDetailService.getUserInfo();
        userInfo.setName(userDetailService.getUserInfo().getName());
        userInfo.setEmail(userDetailService.getUserInfo().getEmail());

        userDetailService.setUsername(username);
        userDetailService.setPassword(encodedPassword);

        userInfo.setUserDetailService(userDetailService);
        userDetailService.setUserInfo(userInfo);

        userInformationRepo.save(userInfo);
        userLoginDetailsRepo.save(userDetailService);

        return "cards";
    }

}

