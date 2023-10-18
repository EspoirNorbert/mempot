package com.app.services;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.models.Grade;
import com.app.models.Role;
import com.app.models.Sector;
import com.app.models.Student;
import com.app.models.User;
import com.app.repositories.RoleRepository;
import com.app.repositories.UserRepository;

@Service
public class UserService {

	@Autowired private UserRepository userRepository;
	@Autowired private PasswordEncoder passwordEncoder;
	@Autowired private RoleRepository roleRepository;
	@Autowired private GradeService gradeService;
	@Autowired private SectorService sectorService;
	
	@Transactional
	public void createUser(User user, String roleName) {
		Role role = roleRepository.findByName(roleName);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList((role)));
		user.setIsEnable(true);
		this.save(user);
	}
	
	public void createStudent(Student student) {
		Grade grade = gradeService.findById(student.getGrade().getId());
		Sector sector = sectorService.findById(student.getSector().getId());	
		student.setGrade(grade);
		student.setSector(sector);
		this.createUser(student, "USER");
	}
	
	@Transactional
	public void save(User user) {
		userRepository.save(user);
	}
	
	
	@Transactional
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
	
	@Transactional
	public User findById(Long id) {
		Optional<User> user =  this.userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}
	
	@Transactional
	public void activateUser(Long userId) {
		Optional<User> user = this.userRepository.findById(userId); 
		if (user.isPresent()) {
			user.get().setIsEnable(true);
			this.save(user.get());
		}
	}
	
	@Transactional
	public void deactivateUser(Long userId) {
		Optional<User> user = this.userRepository.findById(userId); 
		if (user.isPresent()) {
			user.get().setIsEnable(false);
			this.save(user.get());
		}
	}
}
