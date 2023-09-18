package com.evanrobert.Json.API.Controllers;


import com.evanrobert.Json.API.Model.UserDetailService;
import com.evanrobert.Json.API.Model.UserInfo;
import com.evanrobert.Json.API.Repos.UserInformationRepo;
import com.evanrobert.Json.API.Repos.UserLoginDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
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
        private String getUserInfo() {
            return "Account_info";

        }
    }
}




