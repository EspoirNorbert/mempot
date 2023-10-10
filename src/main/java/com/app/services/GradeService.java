package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.Grade;
import com.app.repositories.GradeRepository;

@Service
public class GradeService {

	@Autowired
	private GradeRepository gradeRepository;
	
	public List<Grade> getAllGrades() {
		return gradeRepository.findAll();
	}
	
	public void createGrade(Grade grade) {
		// check after
		gradeRepository.save(grade);
	}
	
}
