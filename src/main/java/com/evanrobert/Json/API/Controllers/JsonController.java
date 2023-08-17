package com.evanrobert.Json.API.Controllers;

import com.evanrobert.Json.API.Exceptions.NotFoundException;
import com.evanrobert.Json.API.Model.Cards;
import com.evanrobert.Json.API.Repos.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class JsonController {
    @Autowired
    CardRepo cardRepo;

    @GetMapping("/get/all/users")
    public List<Cards> getAllUsers() {
        return cardRepo.findAll();}
    /**
     * This allows you to get all cards that have been posted
    */

    @PostMapping("/add/user")
    public ResponseEntity<String> addNewUser(@RequestBody Cards cards) {
        cardRepo.save(cards);
        String message = "User added successfully!";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    /**This will allow you to post a card based off  all  fields in the cards Model.
    * Example request in Curl
    * "curl -X POST -H "Content-Type: application/json" -d
     * *'{"sport": "Football", "player": "Bobby Wagner", "numbered": true, "price": 500.0, "yearOfCard":
     *"2014", "rookie": true, "brand": "Topps"}' localhost:8080/add/user"....
     **/
    @PatchMapping("/fix/card/{id}")
    public ResponseEntity<String> fixCard(@PathVariable Long id, @RequestBody Cards cardUpdate) {
        NotFoundException notFoundException = new NotFoundException();
        Optional<Cards> optionalCard = cardRepo.findById(id);

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
        if (cardUpdate.getBrand() != null) {
            existingCard.setBrand(cardUpdate.getBrand());
        }
        if (cardUpdate.getSport() != null) {
            existingCard.setSport(cardUpdate.getSport());
        }
        existingCard.setRookie(cardUpdate.isRookie());
        existingCard.setNumbered(cardUpdate.isNumbered());


        cardRepo.save(existingCard);

        String message = "Card changed successfully!";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    /**
     * This searches for a card based off of an ID, when found it will allow you to modify all fields
      **/

    @DeleteMapping("/delete/card/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        cardRepo.deleteById(id);
        String message = "card deleted successfully!";
        return ResponseEntity.status(HttpStatus.OK).body(message);
        /**
         * This searches for a card based on ID and will delete the card
        **/

    }

    @GetMapping("/find/by/{rookie}")
    public ResponseEntity<List<Cards>> findCardByRookie(@PathVariable("rookie") boolean rookie) {
        List<Cards> rookieCards = cardRepo.findCardByRookie(rookie);
        return ResponseEntity.status(HttpStatus.OK).body(rookieCards);
        /**
          * Searches for cards based off if the card is a rookie or not, this is a boolean value.

         */
    }

    @GetMapping("/numbered/{numbered}")
    public ResponseEntity<List<Cards>> findCardByNumbered(@PathVariable("numbered") boolean numbered) {
        List<Cards> numberedCards = cardRepo.findCardByNumbered(numbered);
        return ResponseEntity.status(HttpStatus.OK).body(numberedCards);
        /**
         * Searches for cards based off if the card is numbered or not This is a boolean value.

         */
    }

    @GetMapping("/card/{brand}")
    public ResponseEntity<?> findCardByBrand(@PathVariable("brand") String brand) {
        List<Cards> brandOfCards = cardRepo.findCardByBrand(brand);
        if(brandOfCards.isEmpty()){
            String errorMessage = "no card found for  brand:" + "'" +brand +"'";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        return ResponseEntity.status(HttpStatus.OK).body(brandOfCards);
        /**
         * Searches for cards based off the brand of the card
         */
    }


    @GetMapping("/card/player/{player}")
    public ResponseEntity<?> findCardByPlayer(@PathVariable("player") String player) {
        List<Cards> findByPlayerName = cardRepo.findByPlayerStartingWith(player);
        if (findByPlayerName.isEmpty()) {
            String errorMessage = "no player found with name :" + player;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            /**
             * Searches for a card Based off of the prefix of the player. If there is none will show not found error message.
             */
        }
        return ResponseEntity.status(HttpStatus.OK).body(findByPlayerName);
    }

    @GetMapping("/card/year/{yearOfCard}")
    public ResponseEntity<?> findCardByYearOfCard(@PathVariable("yearOfCard") String yearOfCard) {
        List<Cards> findCardByPlayersYear = cardRepo.findCardByYearOfCardStartingWith(yearOfCard);

        if (findCardByPlayersYear.isEmpty()) {
            String errorMessage = "No cards found for the year: " + yearOfCard;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }

        return ResponseEntity.status(HttpStatus.OK).body(findCardByPlayersYear);
    }
    @GetMapping("/card/sport/{sport}")
    public ResponseEntity<?> findCardsBySport(@PathVariable("sport")String sport){
        List<Cards> findCardsBySport = cardRepo.findCardBySportStartingWith(sport);
        if(findCardsBySport.isEmpty()){
            String errorMessage = "No cards found for the sport: " + sport;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        return ResponseEntity.status(HttpStatus.OK).body(findCardsBySport);
    }
}
    /**
     * This GetMapping is responsible for finding a card by the year of card the user enters.
     * If there is no card year recorded, we return a not found status code, as well as a cusrtom error message.
     If it is found we return all the cards corresponding to that year.
     */






