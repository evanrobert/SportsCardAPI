package com.evanrobert.Json.API;

import com.evanrobert.Json.API.Model.Users;
import com.evanrobert.Json.API.Repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    UsersRepo usersRepo;
    @GetMapping("/get/all/users")
    public List<Users> getAllUsers() {
        return usersRepo.findAll();
    }


    @PostMapping("/add/user")
    public ResponseEntity<String> addNewUser(@RequestBody Users users){
        usersRepo.save(users);
        String message = "User added successfully!";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
