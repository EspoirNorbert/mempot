package com.app.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.models.Role;
import com.app.models.User;
import com.app.repositories.RoleRepository;
import com.app.repositories.UserRepository;

@Service
public class UserService {

	@Autowired private UserRepository userRepository;
	@Autowired private PasswordEncoder passwordEncoder;
	@Autowired private RoleRepository roleRepository;
	
	@Transactional
	public void createUser(User user, String roleName) {
		Role role = roleRepository.findByName(roleName);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList((role)));
		this.save(user);
	}
		
	public void save(User user) {
		userRepository.save(user);
	}
}
