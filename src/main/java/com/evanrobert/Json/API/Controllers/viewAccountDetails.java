package com.evanrobert.Json.API.Controllers;


import com.evanrobert.Json.API.Model.Cards;
import com.evanrobert.Json.API.Model.UserDetailService;
import com.evanrobert.Json.API.Model.UserInfo;
import com.evanrobert.Json.API.Repos.UserInformationRepo;
import com.evanrobert.Json.API.Repos.UserLoginDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class viewAccountDetails {
    @Autowired
    UserInformationRepo userInformationRepo;

    @Controller
    public class ViewAccountDetailsController {

        @Autowired
        UserInformationRepo userInformationRepo;
        @Autowired
        UserLoginDetailsRepo userLoginDetailsRepo;

        @GetMapping("/view/account")
        private String getUserInfo(Principal principal, Model model) {
            String username = principal.getName();
            UserDetailService userDetailService = userLoginDetailsRepo.findByUsername(username);
            UserInfo userInfo = userDetailService.getUserInfo(); // Retrieve UserInfo from UserDetailService
           List<Cards> cards = userInfo.getCards();

            // Fetch the name and email from the userInfo object and add them to the model
            model.addAttribute("cards",cards);
            model.addAttribute("userInfo", userInfo);
            model.addAttribute("name", userInfo.getName());
            model.addAttribute("email", userInfo.getEmail());

            return "Account_info";
        }

    }
    }





