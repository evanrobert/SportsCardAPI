package com.evanrobert.Json.API;

import com.evanrobert.Json.API.Model.Cards;
import com.evanrobert.Json.API.Repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements ApplicationRunner {

	@Autowired
	UsersRepo usersRepo;
	//Need to rename the userepo to cardRepo.
	//Additional resources? possibly a third party api to populate card data?
	// add image url.


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Cards cards = new Cards();

		if (usersRepo.count()==0) {
			Cards newUser = cards.builder()
					.player("bill russell").sport("Basketball").price(5000).rookie(false).numbered(true)
					.yearOfCard("1965").brand("Fleer")
					.build();
			Cards newCard2 = Cards.builder().player("Michael Jordan").sport("Basketball").price(50).yearOfCard("2000").numbered(false).rookie(false).brand("Fleer").build();

			usersRepo.save(newUser);
			usersRepo.save(newCard2);
			usersRepo.flush();
			//Populating two cards if the userRepo is empty.
		}

	}
}
