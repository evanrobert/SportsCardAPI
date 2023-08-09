package com.evanrobert.Json.API.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public class NotFoundException {
    public ResponseEntity<String> notFoundException(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body
                (" The card with an ID of " + id + " could not be found");

    }
}
