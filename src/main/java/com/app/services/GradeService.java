package com.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.models.Grade;
import com.app.repositories.GradeRepository;

@Service
public class GradeService {

	@Autowired
	private GradeRepository gradeRepository;
	
	public List<Grade> list() {
		return gradeRepository.findAll();
	}
	
	public List<Grade> getLatestGrades() {
		return gradeRepository.findFirst5ByOrderByIdDesc();
	}
	
	public void save(Grade grade) {
		gradeRepository.save(grade);
	}
	
	@Transactional(readOnly = true)
	public Grade findById(Long id) {
		Grade grade = null;
		Optional<Grade> optionalGrade = gradeRepository.findById(id);
		
		if (optionalGrade.isPresent())
			grade = optionalGrade.get();
		
		return grade;
	}
	
	@Transactional(readOnly = true)
	public Grade findByName(String name) {
		return this.gradeRepository.findByName(name);
	}
	
	@Transactional
	public void delete (Long id) {
		Grade grade = this.findById(id);
		if (grade != null)
			this.gradeRepository.delete(grade);
	}
	
	@Transactional
	public long count() {
		return gradeRepository.count();
	}
}
