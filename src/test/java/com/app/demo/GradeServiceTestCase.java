package com.app.demo;


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

		if (gradeService.findByName("Licence") == null) {
			Grade gradeLicence = new Grade("Licence");
			gradeService.save(gradeLicence);
			System.out.println("Le niveau Licence a été crée avec success !");
		}

		if (gradeService.findByName("Master") == null) {
			Grade gradeMaster = new Grade("Master");
			gradeService.save(gradeMaster);
			System.out.println("Le niveau Master a été crée avec success !");
		}
	}

	@Test
	void listAllGrades() {
		List<Grade> grades = gradeService.list();
		if (grades.size() == 0) {
			System.out.println("Aucun niveau crée pour le moment!");
		} else {
			for (Grade grade : grades) {
				System.out.println(grade.getName());
			}
		}
	}

	@Test
	void getRecentGrade() {
		List<Grade> grades = gradeService.getLatestGrades();
		if (grades.size() == 0) {
			System.out.println("Aucun niveau recement pour le moment !");
		} else {
			for (Grade grade : grades) {
				System.out.println(grade.getName());
			}
		}
	}
}
