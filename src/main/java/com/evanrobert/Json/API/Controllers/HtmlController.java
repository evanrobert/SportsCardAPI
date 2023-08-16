package com.evanrobert.Json.API.Controllers;

import com.evanrobert.Json.API.Model.Cards;
import com.evanrobert.Json.API.Repos.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HtmlController {
    @Autowired
    CardRepo cardRepo;
    @GetMapping("/get/cards")
    public String getAllUsers(Model model) {
        List<Cards> cards = cardRepo.findAll();
        model.addAttribute("cards", cards);
        return "cards";
    }

}
