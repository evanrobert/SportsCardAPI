package com.evanrobert.Json.API.Controllers;

import com.evanrobert.Json.API.Model.Cards;
import com.evanrobert.Json.API.Repos.UsersRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ControllerTest {
    @Mock
    UsersRepo usersRepo;
    @InjectMocks
    private Controller controller;

    @Test
    void getAllUsers() {
        Cards fakeCard = Cards.builder()
                    .sport("Football")
                    .player("John Doe")
                    .numbered(true)
                    .price(50.0)
                    .yearOfCard("2022")
                    .rookie(false)
                    .brand("Optic")
                    .build();
        Cards fakeCard2 = Cards.builder()
                .sport("Football")
                .player("John Doe")
                .numbered(true)
                .price(50.0)
                .yearOfCard("2022")
                .rookie(false)
                .brand("Optic")
                .build();

            List<Cards> fakeCardList = Arrays.asList(fakeCard,fakeCard2);
            when(usersRepo.findAll()).thenReturn(fakeCardList);


            List<Cards> returnedCards = controller.getAllUsers();


            assertNotNull(returnedCards);
            assertEquals(2, returnedCards.size());
            assertEquals(fakeCard, returnedCards.get(0));
        assertEquals(fakeCard2, returnedCards.get(1));


            verify(usersRepo, times(1)).findAll();
        }





    @Test
    void addNewUser() {
    }

    @Test
    void fixCard() {
    }

    @Test
    void deleteById() {
        Cards fakeCard = Cards.builder()
                .id(1l)
                .sport("Football")
                .player("John Doe")
                .numbered(true)
                .price(50.0)
                .yearOfCard("2022")
                .rookie(false)
                .brand("Optic")
                .build();
        controller.deleteById(fakeCard.getId());
    }


    @Test
    void findCardByRookie() {
        Cards fakeCard = Cards.builder()
                .sport("Football")
                .player("John Doe")
                .numbered(true)
                .price(50.0)
                .yearOfCard("2022")
                .rookie(false)
                .brand("Optic")
                .build();
      controller.findCardByRookie(fakeCard.isRookie());
        Cards fakeCard2 = Cards.builder()
                .sport("Football")
                .player("John Doe")
                .numbered(true)
                .price(50.0)
                .yearOfCard("2022")
                .rookie(true)
                .brand("Optic")
                .build();
        controller.findCardByRookie(fakeCard.isRookie());
      Assert.isTrue(!fakeCard.isRookie());
      Assert.isTrue(fakeCard2.isRookie());
    }

    @Test
    void findCardByNumbered() {
        Cards fakeCard = Cards.builder()
                .sport("Football")
                .player("John Doe")
                .numbered(true)
                .price(50.0)
                .yearOfCard("2022")
                .rookie(false)
                .brand("Optic")
                .build();
        controller.findCardByRookie(fakeCard.isRookie());
        Cards fakeCard2 = Cards.builder()
                .sport("Football")
                .player("John Doe")
                .numbered(false)
                .price(50.0)
                .yearOfCard("2022")
                .rookie(true)
                .brand("Optic")
                .build();
        Assert.isTrue(fakeCard.isNumbered());
        Assert.isTrue(!fakeCard2.isNumbered());
    }

    @Test
    void findCardByBrand() {
        Cards fakeCard = Cards.builder()
                .sport("Football")
                .player("John Doe")
                .numbered(true)
                .price(50.0)
                .yearOfCard("2022")
                .rookie(false)
                .brand("Optic")
                .build();
        Cards fakeCard2 = Cards.builder()
                .sport("Football")
                .player("John Doe")
                .numbered(false)
                .price(50.0)
                .yearOfCard("2022")
                .rookie(true)
                .brand("optic")
                .build();
        Assert.isTrue(!Objects.equals(fakeCard.getBrand(), "prism"));
        Assert.isTrue(Objects.equals(fakeCard2.getBrand(), "optic"));

    }

    @Test
    void findCardByPlayer() {
        Cards fakeCard = Cards.builder()
                .sport("Football")
                .player("John Doe")
                .numbered(true)
                .price(50.0)
                .yearOfCard("2022")
                .rookie(false)
                .brand("optic")
                .build();
        Cards fakeCard2 = Cards.builder()
                .sport("Football")
                .player("John Doe")
                .numbered(false)
                .price(50.0)
                .yearOfCard("2022")
                .rookie(true)
                .brand("optic")
                .build();
        Assert.isTrue(fakeCard.getPlayer().equals("John Doe"));
        Assert.isTrue(!fakeCard2.getPlayer().equals("Sosa"));


    }
}