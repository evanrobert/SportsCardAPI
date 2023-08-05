package com.evanrobert.Json.API.Repos;

import com.evanrobert.Json.API.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users,Long> {
}
