package com.evanrobert.Json.API.Controllers;


import com.evanrobert.Json.API.Model.Cards;
import com.evanrobert.Json.API.Repos.CardRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class HtmlControllerTest {


    @Mock
    CardRepo cardRepo;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    HtmlController htmlController;

    @Test
    void getAllCards(){
        MockitoAnnotations.openMocks(this);

        
            List<Cards> fakeCardsList = new ArrayList<>();
            fakeCardsList.add(new Cards());
            fakeCardsList.add(new Cards());


            when(cardRepo.findAll()).thenReturn(fakeCardsList);


            Model model = Mockito.mock(Model.class);


            String viewName = htmlController.getAllCards(model);


            Assertions.assertEquals("cards", viewName);


            Mockito.verify(cardRepo, Mockito.times(1)).findAll();


            Mockito.verify(model, Mockito.times(1)).addAttribute("cards", fakeCardsList);
        }

    @Test
    void getCardsBySport() {
    }

    @Test
    void getByYearOfCard() {
    }

    @Test
    void getCardByBrand() {
    }

    @Test
    void getCardByIsNumbered() {
    }

    @Test
    void getCardByIsRookie() {
    }

    @Test
    void getCardByPrice() {
    }
}