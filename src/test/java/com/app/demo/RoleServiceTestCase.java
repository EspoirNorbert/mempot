package com.app.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		// Création des rôles
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");
        // Enregistrement des rôles
        roleService.save(adminRole);
        roleService.save(userRole);
	}
	
	@Test
	void listAllRoles() {
		 List<Role> roles = roleService.list();
		 assertEquals(2, roles.size());
		 assertEquals("ADMIN", roles.get(0).getName());
	     assertEquals("USER", roles.get(1).getName());
	}
}
