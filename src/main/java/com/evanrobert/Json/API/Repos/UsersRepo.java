package com.evanrobert.Json.API.Repos;

import com.evanrobert.Json.API.Model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Cards,Long> {
}
