package com.app.services;

import java.time.LocalDate;
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
	
	public void create(Thesis thesis) {
		Student student = (Student) this.userService.getCurrentUser();
		
		if (student !=null) {
			thesis.setSubmissionDate(LocalDate.now());
			// Recuperation de la these de l'etudiant
			List<Thesis> studentThesis = student.getThesis();
			// ajout dans le tableau de la these
			studentThesis.add(thesis);
			// liaison entre la these et l'etudiant
			thesis.setStudent(student);
			thesisRepository.save(thesis);
		}
	}
	
	public List<Thesis> findByCurrentUser() {
		Student student = (Student) this.userService.getCurrentUser();
		return student.getThesis();
	}
}
