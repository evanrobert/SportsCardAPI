package com.evanrobert.Json.API.Controllers;

import com.evanrobert.Json.API.Model.Cards;
import com.evanrobert.Json.API.Repos.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
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
    public String getByYearOfCard(@RequestParam("yearOfCard") String yearOfCard, Model model) {
        List<Cards> cards = cardRepo.findCardByYearOfCardStartingWith(yearOfCard);
        model.addAttribute("cards", cards);
        return "cards";
    }

    @GetMapping("/get/card/by/brand")
    public String getCardByBrand(@RequestParam("brand") String brand, Model model, RedirectAttributes redirectAttributes) {
        List<Cards> cards = cardRepo.findCardByBrand(brand);
        if (cards.isEmpty()) {
            redirectAttributes.addAttribute("error", "no card by this brand");

        }
        model.addAttribute("cards", cards);
        return "cards";

    }

    @GetMapping("/get/card/by/numbered")
    public String getCardByIsNumbered(@RequestParam("numbered") boolean numbered, Model model, RedirectAttributes redirectAttributes) {
        List<Cards> cards = cardRepo.findCardByNumbered(numbered);
        if (cards.isEmpty()) {
            redirectAttributes.addAttribute("error", "no numbered cards found");
        }
        model.addAttribute("cards", cards);
        return "cards";

    }

    @GetMapping("/get/card/by/rookie")
    public String getCardByIsRookie(@RequestParam("rookie") boolean rookie, Model model) {
        List<Cards> cards = cardRepo.findCardByRookie(rookie);
        if (cards.isEmpty()) {
      model.addAttribute("error", "No rookies found");

        }
        model.addAttribute("cards", cards);
        return "cards";
    }

    @GetMapping("/get/card/by/price")
    public String getCardByPrice(@RequestParam("price") double price, @RequestParam("comparison") String comparison,
                                 Model model, RedirectAttributes redirectAttributes) {
        List<Cards> cards = new ArrayList<>();

        if (comparison.equals("greaterThan")) {
            cards = cardRepo.findCardsByPriceGreaterThan(price);
        } else if (comparison.equals("lessThan")) {
            cards = cardRepo.findCardsByPriceLessThan(price);
        }

        if (cards.isEmpty()) {
            redirectAttributes.addAttribute("error", "No cards found for the given price.");
        }

        model.addAttribute("cards", cards);
        return "cards";
    }

}





