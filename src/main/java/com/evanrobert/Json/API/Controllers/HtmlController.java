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
    /**
     * Retrieves a list of cards based on the provided brand and prepares the view for display.
     *
     * This method takes in a String parameter indicating the brand of cards to retrieve.
     * It queries the card repository to retrieve cards based on the provided brand and prepares
     * the model with the list of cards matching the brand. If no cards are found for the given brand,
     * an error attribute is added for redirection to notify the user.
     *
     * @param brand The brand of cards to retrieve.
     * @param model The model to add attributes for the view.
     * @param redirectAttributes Used for adding attributes to a redirect.
     * @return The name of the view template ("cards" in this case) for displaying the filtered cards.
     */

    @GetMapping("/get/card/by/brand")
    public String getCardByBrand(@RequestParam("brand") String brand, Model model, RedirectAttributes redirectAttributes) {
        List<Cards> cards = cardRepo.findCardByBrand(brand);
        if (cards.isEmpty()) {
            redirectAttributes.addAttribute("error", "no card by this brand");

        }
        model.addAttribute("cards", cards);
        return "cards";

    }
    /**
     * Retrieves a list of cards based on the numbered status provided and prepares the view for display.
     *
     * This method takes in a boolean parameter indicating whether the cards should be numbered or not.
     * It queries the card repository to retrieve cards based on the provided numbered status and prepares
     * the model with the list of filtered cards. If no numbered cards are found, an error attribute is added
     * for redirection to notify the user.
     *
     * @param numbered A boolean indicating whether the cards should be numbered or not.
     * @param model The model to add attributes for the view.
     * @param redirectAttributes Used for adding attributes to a redirect.
     * @return The name of the view template ("cards" in this case) for displaying the filtered cards.
     */

    @GetMapping("/get/card/by/numbered")
    public String getCardByIsNumbered(@RequestParam("numbered") boolean numbered, Model model, RedirectAttributes redirectAttributes) {
        List<Cards> cards = cardRepo.findCardByNumbered(numbered);
        if (cards.isEmpty()) {
            redirectAttributes.addAttribute("error", "no numbered cards found");
        }
        model.addAttribute("cards", cards);
        return "cards";

    }
    /**
     * Retrieves a list of cards based on the rookie status provided and redirects to the appropriate view.
     *
     * This method takes in a boolean parameter indicating whether the cards should be rookies or not.
     * It queries the card repository to retrieve cards based on the provided rookie status and redirects
     * to a view displaying the filtered cards. If the "rookie" parameter is not provided, a flash error
     * message is added and the user is redirected to the root URL for appropriate selection.
     *
     * @param rookie A boolean indicating whether the cards should be rookies or not (optional).
     * @param redirectAttributes Used for adding attributes to a redirect.
     * @return A redirection to the relevant URL either for displaying cards or prompting for selection.
     */

    @GetMapping("/get/card/by/rookie")
    public String getCardByIsRookie(@RequestParam(value = "rookie", required = false) Boolean rookie,RedirectAttributes redirectAttributes) {
        if (rookie == null) {
            redirectAttributes.addFlashAttribute("error", "Please select whether the card is a rookie.");
            return "redirect:/"; // Redirect to a relevant URL
        }

        List<Cards> cards = cardRepo.findCardByRookie(rookie);
        redirectAttributes.addFlashAttribute("cards", cards);
        return "redirect:/cards"; // Redirect to the URL displaying cards
    }


    /**
     * Retrieves a list of cards based on the given price and comparison criteria.
     *
     * @param price The price to compare against.
     * @param comparison The comparison criteria ("greaterThan" or "lessThan").
     * @param model The model to add attributes for the view.
     * @param redirectAttributes Used for adding attributes to a redirect.
     * @return The name of the view template ("cards" in this case).
     */

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





