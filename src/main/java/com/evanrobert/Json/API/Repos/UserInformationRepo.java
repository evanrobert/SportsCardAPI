package com.evanrobert.Json.API.Repos;

import com.evanrobert.Json.API.Model.UserDetailService;
import com.evanrobert.Json.API.Model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInformationRepo extends JpaRepository<UserInfo,Long> {
    UserInfo findByName(String name );



}
