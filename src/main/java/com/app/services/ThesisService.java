package com.app.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public void update(Thesis thesis) {
		
		Thesis thesisToUpdated = this.findById(thesis.getId());
	
		if (thesisToUpdated !=null) {
			thesisToUpdated.setTopic(thesis.getTopic());
			thesisToUpdated.setAcademicYear(thesis.getAcademicYear());
			thesisRepository.save(thesisToUpdated);
			System.out.println("Updated");
		}
		
	}

	public List<Thesis> findByCurrentUser() {
		Student student = (Student) this.userService.getCurrentUser();
		return student.getThesis();
	}

	@Transactional(readOnly = true)
	public Thesis findById(Long id) {
		Thesis thesis = null;
		Optional<Thesis> optionalGrade = thesisRepository.findById(id);
		if (optionalGrade.isPresent()) thesis = optionalGrade.get();
		return thesis;
	}

	@Transactional
	public long count() {
		return thesisRepository.count();
	}
}
