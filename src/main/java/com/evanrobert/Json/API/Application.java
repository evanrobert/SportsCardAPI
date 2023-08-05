package com.evanrobert.Json.API;

import com.evanrobert.Json.API.Model.Users;
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
		Users users = new Users();

		if (users.isEmpty()) {
			Users newUser = Users.builder()
					.firstName("bill")
					.lastName("Nye")
					.build();

			usersRepo.save(newUser);
			usersRepo.flush();
		}

	}
}
