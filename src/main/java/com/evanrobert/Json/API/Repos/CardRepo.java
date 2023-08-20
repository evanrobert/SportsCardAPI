package com.evanrobert.Json.API.Repos;

import com.evanrobert.Json.API.Model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepo extends JpaRepository<Cards,Long> {
    List<Cards> findCardByRookie(boolean rookie);
    //Finds Card by if rookie
    List<Cards> findCardByNumbered(boolean numbered);
    //Finds Card by if numbered
    List<Cards> findCardByBrand(String brand);
    // Finds card by Brand

    List<Cards> findByPlayerStartingWith(String player);
    //Finds player starting with
    List<Cards> findCardByYearOfCardStartingWith(String yearOfCard);
    List<Cards> findCardBySportStartingWith(String sport);
    List<Cards> findCardsByPriceLessThan(double price);

    List<Cards> findCardsByPriceGreaterThan(double price);
}






