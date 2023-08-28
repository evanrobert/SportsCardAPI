package com.evanrobert.Json.API;

import com.evanrobert.Json.API.Model.Cards;
import com.evanrobert.Json.API.Repos.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements ApplicationRunner {

	@Autowired
	CardRepo cardRepo;




	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 *
	 * @param args incoming application arguments
	 * @throws Exception
	 * Adding two cards if card list is empty
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Cards cards = new Cards();

		if (cardRepo.count()==0) {
			Cards newUser = cards.builder()
					.player("bill russell").sport("Basketball").price(500.00).rookie(false).numbered(true)
					.yearOfCard("1965").brand("Fleer")
					.build();
			Cards newCard2 = Cards.builder().player("Michael Jordan").sport("Basketball").price(50.00).yearOfCard("2000").numbered(false).rookie(false).brand("Fleer").build();

			cardRepo.save(newUser);
			cardRepo.save(newCard2);
			cardRepo.flush();

		}

	}
}
