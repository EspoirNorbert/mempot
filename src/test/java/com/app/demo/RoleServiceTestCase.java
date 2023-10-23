package com.app.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.models.Role;
import com.app.services.RoleService;

@SpringBootTest
class RoleServiceTestCase {

	@Autowired
	private RoleService roleService;

	@Test
	void createRole() {

		if (roleService.findByName("ADMIN") == null) {
			Role adminRole = new Role("ADMIN");
			roleService.save(adminRole);
		}

		if (roleService.findByName("USER") == null) {
			Role userRole = new Role("USER");
			roleService.save(userRole);
		}
	}

	@Test
	void listAllRoles() {
		List<Role> roles = roleService.list();
		if (roles.size() == 0) {
			System.out.println("Aucun role recement pour le moment !");
		} else {
			for (Role role : roles) {
				System.out.println(role.getName());
			}
		}
	}
		
}
