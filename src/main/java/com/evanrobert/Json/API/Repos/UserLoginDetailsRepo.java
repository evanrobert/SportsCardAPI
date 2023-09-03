package com.evanrobert.Json.API.Repos;

import com.evanrobert.Json.API.Model.UserDetailService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginDetailsRepo extends JpaRepository <UserDetailService,Long> {
    UserDetailService findByUsername(String username);

}
