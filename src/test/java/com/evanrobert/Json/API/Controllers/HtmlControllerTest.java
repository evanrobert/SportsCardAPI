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
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class HtmlControllerTest {


    @Mock
    CardRepo cardRepo;


    @InjectMocks
    HtmlController htmlController;


    @Test
    void getAllCards() {
        MockitoAnnotations.openMocks(this);


        List<Cards> fakeCardsList = new ArrayList<>();
        fakeCardsList.add(new Cards());
        fakeCardsList.add(new Cards());


        when(cardRepo.findAll()).thenReturn(fakeCardsList);


        Model model = mock(Model.class);


        String viewName = htmlController.getAllCards(model);


        assertEquals("cards", viewName);


        verify(cardRepo, Mockito.times(1)).findAll();


        verify(model, Mockito.times(1)).addAttribute("cards", fakeCardsList);
    }

    @Test
    void getCardsBySport() {
        String sport = "Football";
        List<Cards> fakeCardsList = new ArrayList<>();
        fakeCardsList.add(new Cards());
        fakeCardsList.add(new Cards());

        when(cardRepo.findCardBySportStartingWith(sport)).thenReturn(fakeCardsList);

        Model model = mock(Model.class);

        String viewName = htmlController.getCardsBySport(sport, model);

        verify(cardRepo, times(1)).findCardBySportStartingWith(sport);
        verify(model, times(1)).addAttribute("cards", fakeCardsList);

        assertEquals("cards", viewName);

    }


    @Test
    void getByYearOfCard() {
        String yearOfCard = "2022";
        List<Cards> fakeCardsList = new ArrayList<>();
        fakeCardsList.add(new Cards());
        fakeCardsList.add(new Cards());

        when(cardRepo.findCardByYearOfCardStartingWith(yearOfCard)).thenReturn(fakeCardsList);

        Model model = mock(Model.class);

        String viewName = htmlController.getByYearOfCard(yearOfCard, model);

        verify(cardRepo, times(1)).findCardByYearOfCardStartingWith(yearOfCard);
        verify(model, times(1)).addAttribute("cards", fakeCardsList);

        assertEquals("cards", viewName);

    }

    @Test
    void getCardByBrand() {
        String brand = "Optic";
        List<Cards> fakeCardsList = new ArrayList<>();
        fakeCardsList.add(new Cards());
        fakeCardsList.add(new Cards());

        when(cardRepo.findCardByBrand(brand)).thenReturn(fakeCardsList);

        Model model = mock(Model.class);
        RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();

        String viewName = htmlController.getCardByBrand(brand, model, redirectAttributes);

        verify(cardRepo, times(1)).findCardByBrand(brand);
        verify(model, times(1)).addAttribute("cards", fakeCardsList);

        assertEquals("cards", viewName);

    }

    @Test
    void getCardByIsNumbered() {
        boolean numbered = true;
        List<Cards> fakeCardsList = new ArrayList<>();
        fakeCardsList.add(new Cards());
        fakeCardsList.add(new Cards());

        when(cardRepo.findCardByNumbered(numbered)).thenReturn(fakeCardsList);
        Model model = mock(Model.class);
        RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();

        String viewName = htmlController.getCardByIsNumbered(numbered, model, redirectAttributes);
        verify(cardRepo, times(1)).findCardByNumbered(numbered);
        verify(model, times(1)).addAttribute("cards", fakeCardsList);
        assertEquals("cards", viewName);


    }

    @Test
    void getCardByIsRookie() {
        boolean rookie = true;
        List<Cards> fakeCardsList = new ArrayList<>();
        fakeCardsList.add(new Cards());
        fakeCardsList.add(new Cards());
        when(cardRepo.findCardByRookie(rookie)).thenReturn(fakeCardsList);
        Model model = mock(Model.class);
        RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
        String viewName = htmlController.getCardByIsRookie(rookie, model, redirectAttributes);
        verify(cardRepo, times(1)).findCardByRookie(rookie);
        verify(model, times(1)).addAttribute("cards", fakeCardsList);
        assertEquals("cards", viewName);


    }

    @Test
    void testGetCardByPriceGreaterThan() {
        double price = 50.00;

        List<Cards> fakeCardsList = new ArrayList<>();
        fakeCardsList.add(new Cards());
        fakeCardsList.add(new Cards());

        when(cardRepo.findCardsByPriceGreaterThan(price)).thenReturn(fakeCardsList);

        Model model = new ExtendedModelMap();
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        String comparison = "greaterThan";
        String viewName = htmlController.getCardByPrice(price, comparison, model, redirectAttributes);

        
        verify(cardRepo, times(1)).findCardsByPriceGreaterThan(price);
        assertEquals("cards", viewName);
    }

    @Test
    void testGetCardByPriceLessThan() {
        double price = 50.00;

        List<Cards> fakeCardsList = new ArrayList<>();
        fakeCardsList.add(new Cards());
        fakeCardsList.add(new Cards());

        when(cardRepo.findCardsByPriceLessThan(price)).thenReturn(fakeCardsList);

        Model model = new ExtendedModelMap();
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        String comparison = "lessThan";
        String viewName = htmlController.getCardByPrice(price, comparison, model, redirectAttributes);


        verify(cardRepo, times(1)).findCardsByPriceLessThan(price);
        assertEquals("cards", viewName);
    }

}


