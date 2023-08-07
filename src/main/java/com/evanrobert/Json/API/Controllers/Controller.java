package com.evanrobert.Json.API.Controllers;

import com.evanrobert.Json.API.Model.Cards;
import com.evanrobert.Json.API.Repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
}
