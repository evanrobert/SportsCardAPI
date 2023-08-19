package com.evanrobert.Json.API.Controllers;

import com.evanrobert.Json.API.Model.Cards;
import com.evanrobert.Json.API.Repos.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Collections;
import java.util.List;

@Controller
public class HtmlController {
    @Autowired
    CardRepo cardRepo;

    @GetMapping("/")
    public String getAllCards(Model model) {
        List<Cards> cards = cardRepo.findAll();
        model.addAttribute("cards", cards);
        return "cards"; // Returning the name of the HTML template
    }

    @GetMapping("/get/cards/sport")
    public String getCardsBySport(@RequestParam("sport") String sport, Model model) {
        List<Cards> cards = cardRepo.findCardBySportStartingWith(sport);
        model.addAttribute("cards", cards);
        return "cards"; // Returning the name of the HTML template
    }
    @GetMapping("/get/card/yearOfCard")
    public String getByYearOfCard(@RequestParam("yearOfCard")String yearOfCard,Model model){
        List<Cards> cards = cardRepo.findCardByYearOfCardStartingWith(yearOfCard);
        model.addAttribute("cards", cards);
        return "cards";
    }
    @GetMapping("/get/card/by/brand")
    public String getCardByBrand(@RequestParam("brand")String brand, Model model, RedirectAttributes redirectAttributes){
        List<Cards> cards = cardRepo.findCardByBrand(brand);
        if(cards.isEmpty()){
            redirectAttributes.addAttribute("error","no card by this brand");

        }
        model.addAttribute("cards",cards);
        return "cards";

    }

}

