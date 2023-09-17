package com.evanrobert.Json.API.Controllers;


import com.evanrobert.Json.API.Model.UserInfo;
import com.evanrobert.Json.API.Repos.UserInformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class viewAccountDetails {
    @Autowired
    UserInformationRepo userInformationRepo;

    @Controller
    public class ViewAccountDetailsController {

        @Autowired
        UserInformationRepo userInformationRepo;

        @GetMapping("/view/account")
        private String getUserInfo(@RequestParam Long id, Model model){
            Optional<UserInfo> userInfo = userInformationRepo.findById(id);

            if (userInfo.isPresent()) {
                model.addAttribute("userInfo", userInfo.get());
                return "Account_info";
            } else {
                // Handle the case where the user is not found
                return "error"; // You can create an error.html template
            }
        }
    }



}
