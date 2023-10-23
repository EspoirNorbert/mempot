package com.app.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.models.Grade;
import com.app.services.GradeService;

@SpringBootTest
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

	@Test
	void getRecentGrade() {
		List<Grade> grades = gradeService.getLatestGrades();
		 assertEquals(3, grades.size());
		 assertEquals("Doctorat", grades.get(0).getName());
		 assertEquals("Master", grades.get(1).getName());
	}
}
