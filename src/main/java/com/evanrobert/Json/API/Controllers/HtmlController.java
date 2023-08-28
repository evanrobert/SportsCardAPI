package com.evanrobert.Json.API.Controllers;

import com.evanrobert.Json.API.Model.Cards;
import com.evanrobert.Json.API.Repos.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HtmlController {
    @Autowired
    CardRepo cardRepo;
    @GetMapping("/create")
    public String getCreateACard( Model model){
        model.addAttribute("cards", new Cards());
        return "createCard";
    }


    @PostMapping("/create/card")
    public String createACard(@ModelAttribute Cards cards,Model model) {
        try {
            cardRepo.save(cards);
            return "redirect:/";  // Redirect to the root path
        } catch (Exception e) {
            String errorMessage = "Card could not be saved: " + e.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            // Log the error or handle it appropriately
            return "redirect:/createCard";  // Redirect back to the createCard path
        }
    }


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
    public String getCardByIsRookie(@RequestParam(value = "rookie", required = false) Boolean rookie, RedirectAttributes redirectAttributes) {
        if (rookie == null) {
            redirectAttributes.addFlashAttribute("error", "Please select whether the card is a rookie.");
            return "redirect:/"; // Redirect to a relevant URL
        }

        List<Cards> cards = cardRepo.findCardByRookie(rookie);
        redirectAttributes.addFlashAttribute("cards", cards);
        return "redirect:/cards"; // Redirect to the URL displaying cards
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





