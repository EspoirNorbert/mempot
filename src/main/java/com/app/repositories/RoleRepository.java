package com.app.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.app.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	/***
	 * Find role using name
	 * @param roleName Role Name to find
	 * @return Role
	*/
	Role findByName(String roleName);
	
}
