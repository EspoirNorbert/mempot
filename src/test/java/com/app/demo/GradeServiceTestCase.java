package com.app.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.models.Grade;
import com.app.services.GradeService;

class GradeServiceTestCase {

	@Autowired GradeService gradeService;
	
	@Test
	void create() {
		Grade gradeLicence = new Grade("Licence");
		Grade gradeMaster = new Grade("Master");
		gradeService.save(gradeLicence);
		gradeService.save(gradeMaster);
	}
	
	@Test
	void listAllGrades() {
		 List<Grade> grades = gradeService.list();
		 assertEquals(2, grades.size());
		 assertEquals("Licence", grades.get(0).getName());
	     assertEquals("Master", grades.get(1).getName());
	}

}
