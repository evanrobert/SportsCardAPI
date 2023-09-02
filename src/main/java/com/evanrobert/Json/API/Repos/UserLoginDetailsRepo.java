package com.evanrobert.Json.API.Repos;

import com.evanrobert.Json.API.Model.UserDetailService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userDetailServiceRepo extends JpaRepository <UserDetailService,Long> {
}
