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


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Cards cards = new Cards();

		if (usersRepo.count()==0) {
			Cards newUser = cards.builder()
					.player("bill russell").price(5000).rookie(false).numbered("yes")
					.yearOfCard("1965")
					.build();
			Cards newCard2 = Cards.builder().player("Michael Jordan").price(50).yearOfCard("2000").numbered("No").rookie(false).build();

			usersRepo.save(newUser);
			usersRepo.save(newCard2);
			usersRepo.flush();
		}

	}
}
