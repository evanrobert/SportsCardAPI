package com.evanrobert.Json.API.Controllers;

import com.evanrobert.Json.API.Model.Cards;
import com.evanrobert.Json.API.Repos.UsersRepo;
import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControllerTest {
    @Mock
    UsersRepo usersRepo;
    @InjectMocks
    private Controller controller;

    @Test
    void getAllUsers() {

    }

    @Test
    void addNewUser() {
    }

    @Test
    void fixCard() {
    }

    @Test
    void deleteById() {
    }


    @Test
    void findCardByRookie() {
    }

    @Test
    void findCardByNumbered() {
    }

    @Test
    void findCardByBrand() {
    }

    @Test
    void findCardByPlayer() {

    }
}