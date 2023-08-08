package com.evanrobert.Json.API.Repos;

import com.evanrobert.Json.API.Model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepo extends JpaRepository<Cards,Long> {
    List<Cards> findCardByRookie(boolean rookie);
}
