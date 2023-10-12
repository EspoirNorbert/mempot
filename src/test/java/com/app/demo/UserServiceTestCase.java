package com.app.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.models.User;
import com.app.services.UserService;

@SpringBootTest
class UserServiceTestCase {

	@Autowired private UserService userService;
	
	@Test
	void createUser() {
		// creer un utilisateur simple
		User defautUser = new User();
		defautUser.setEmail("admin@deposer.com");
		defautUser.setFirstName("Admin");
		defautUser.setLastname("Super");
		defautUser.setIsEnable(true);
		defautUser.setPassword("admin@123");
		defautUser.setAddress("Hann Mariste Fort B");
		
		userService.createUser(defautUser, "ADMIN");
	}

}
