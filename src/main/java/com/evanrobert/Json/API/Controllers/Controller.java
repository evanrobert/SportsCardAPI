package com.evanrobert.Json.API.Controllers;

import com.evanrobert.Json.API.Exceptions.NotFoundException;
import com.evanrobert.Json.API.Model.Cards;
import com.evanrobert.Json.API.Repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class Controller {
    @Autowired
    UsersRepo usersRepo;
    @GetMapping("/get/all/users")
    public List<Cards> getAllUsers() {
        return usersRepo.findAll();
    }


    @PostMapping("/add/user")
    public ResponseEntity<String> addNewUser(@RequestBody Cards cards){
        usersRepo.save(cards);
        String message = "User added successfully!";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    @PatchMapping("/fix/card/{id}")
    public ResponseEntity<String> fixCard(@PathVariable Long id, @RequestBody Cards cardUpdate) {
        NotFoundException notFoundException = new NotFoundException();
        Optional<Cards> optionalCard = usersRepo.findById(id);

        if (optionalCard.isEmpty()) {
            return notFoundException.notFoundException(id);
        }

        Cards existingCard = optionalCard.get();

        if (cardUpdate.getPlayer() != null) {
            existingCard.setPlayer(cardUpdate.getPlayer());
        }


        if (cardUpdate.getPrice() != 0) {
            existingCard.setPrice(cardUpdate.getPrice());
        }

        if (cardUpdate.getYearOfCard() != null) {
            existingCard.setYearOfCard(cardUpdate.getYearOfCard());
        }
        if(cardUpdate.getBrand() != null){
            existingCard.setBrand(cardUpdate.getBrand());
        }
        existingCard.setRookie(cardUpdate.isRookie());
        existingCard.setNumbered(cardUpdate.isNumbered());




        usersRepo.save(existingCard);

        String message = "Card changed successfully!";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping("/delete/card/{id}")
    public ResponseEntity<String>  deleteById(@PathVariable Long id){
        usersRepo.deleteById(id);
        String message = "card deleted successfully!";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    @GetMapping("/find/by/{rookie}")
    public ResponseEntity<List<Cards>> findCardByRookie(@PathVariable("rookie") boolean rookie) {
        List<Cards> rookieCards = usersRepo.findCardByRookie(rookie);
        return ResponseEntity.status(HttpStatus.OK).body(rookieCards);
    }
    @GetMapping("/numbered/{numbered}")
    public ResponseEntity<List<Cards>> findCardByNumbered(@PathVariable("numbered") boolean numbered) {
        List<Cards> numberedCards = usersRepo.findCardByNumbered(numbered);
        return ResponseEntity.status(HttpStatus.OK).body(numberedCards);
    }
    @GetMapping("/card/{brand}")
    public ResponseEntity<List<Cards>> findCardByBrand(@PathVariable("brand")String brand){
        List<Cards> brandOfCards = usersRepo.findCardByBrand(brand);
        return ResponseEntity.status(HttpStatus.OK).body(brandOfCards);
    }


}
