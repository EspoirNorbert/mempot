package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.config.CustomUserDetailService;
import com.app.models.Student;
import com.app.models.Thesis;
import com.app.repositories.ThesisRepository;

@Service
public class ThesisService {

	@Autowired private ThesisRepository thesisRepository;
	@Autowired private CustomUserDetailService userService;
	
	
	public List<Thesis> list() {
		return thesisRepository.findAll();
	}
	
	public void save(Thesis thesis) {
		thesisRepository.save(thesis);
	}
	
	public List<Thesis> findByCurrentUser() {
		Student student = (Student) this.userService.getCurrentUser();
		return student.getThesis();
	}
}
