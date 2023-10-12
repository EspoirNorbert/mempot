package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.Role;
import com.app.repositories.RoleRepository;


@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> list() {
		return roleRepository.findAll();
	}
	
	public void save(Role role) {
		roleRepository.save(role);
	}
	
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
	
}
