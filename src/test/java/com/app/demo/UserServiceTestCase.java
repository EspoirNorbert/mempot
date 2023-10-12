package com.app.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.models.Grade;
import com.app.models.Sector;
import com.app.models.Student;
import com.app.models.User;
import com.app.services.GradeService;
import com.app.services.SectorService;
import com.app.services.StudentService;
import com.app.services.UserService;

@SpringBootTest
class UserServiceTestCase {

	@Autowired private UserService userService;
	@Autowired private GradeService gradeService;
	@Autowired private SectorService sectorService;
	@Autowired private StudentService studentService;

	//@Test
	void createUser() {
		User defautUser = new User();
		defautUser.setEmail("admin@deposer.com");
		defautUser.setFirstName("Admin");
		defautUser.setLastname("Super");
		defautUser.setIsEnable(true);
		defautUser.setPassword("admin@123");
		defautUser.setAddress("Hann Mariste Fort B");
		userService.createUser(defautUser, "ADMIN");
	}

	//@Test
	void createStudent() {
		// Retrouve le niveau et les filieres
		Grade master = this.gradeService.findByName("Master");
		Sector informatique = this.sectorService.findByName("Informatique");

		// creer un etudiant
		Student ghislain = new Student();
		ghislain.setEmail("ghislain.akinocho@gmail.com");
		ghislain.setFirstName("Ghislain");
		ghislain.setLastname("Akinocho");
		ghislain.setIsEnable(true);
		ghislain.setPassword("user@123");
		ghislain.setMatricule("123589637");
		ghislain.setGrade(master);
		ghislain.setSector(informatique);

		Student jmp = new Student();
		jmp.setEmail("jmp@gmail.com");
		jmp.setFirstName("Jean-Marie");
		jmp.setLastname("Preira");
		jmp.setIsEnable(true);
		jmp.setPassword("user@123");
		jmp.setMatricule("123589638");
		jmp.setGrade(master);
		jmp.setSector(informatique);

		userService.createUser(ghislain, "USER");
		userService.createUser(jmp, "USER");
	}

	@Test void listStudent() {
		List<Student> students = this.studentService.list();
		for (Student student : students) {
			System.out.println(student.getFirstname());
		}
	}
}
