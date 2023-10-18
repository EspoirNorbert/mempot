package com.app.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.beans.UpdateStudentRequest;
import com.app.models.Grade;
import com.app.models.Sector;
import com.app.models.Student;
import com.app.models.User;
import com.app.services.GradeService;
import com.app.services.SectorService;
import com.app.services.StudentService;
import com.app.services.UserService;
import com.app.utils.Helper;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/students")
public class StudentController {
	
	private static String MAIN_PATH = "views/admin/students";

	@Autowired private StudentService studentService;
	@Autowired private UserService userService;
	@Autowired private GradeService gradeService;
	@Autowired private SectorService sectorService;
	
	@GetMapping
	public String list(Model model) {
		List<Student> students  = studentService.list();
		model.addAttribute("students", students);
		return MAIN_PATH + "/list";
	}
	
	@GetMapping("/edit/{studentId}")
	public String displayEditStudentForm(@PathVariable("studentId") Long id,Model model) {
		
		Student studentToFound = studentService.findById(id);

		if (studentToFound == null) {
			return "redirect:/admin/students";
		}
		
		UpdateStudentRequest student = Helper.studentToUpdateStudentRequest(studentToFound);
		
		System.out.println(student);
		List<Sector> sectors = sectorService.list();
		List<Grade> grades = gradeService.list();
		
		model.addAttribute("student", student);
		model.addAttribute("grades", grades);
		model.addAttribute("sectors", sectors);
		return MAIN_PATH + "/edit";
	}
	
	@PostMapping("/update")
	public String modifier(@Valid @ModelAttribute("student") 
	UpdateStudentRequest requestStudent,BindingResult result,Model model) {
		
		Student student = Helper.
				updateStudentRequestToStudent(requestStudent);
		System.out.println(student);
		System.out.println(result);
		
		if (result.hasErrors()) {
			List<Sector> sectors = sectorService.list();
			List<Grade> grades = gradeService.list();
			model.addAttribute("grades", grades);
			model.addAttribute("sectors", sectors);
			return MAIN_PATH+ "/edit";
		}
	
		this.studentService.update(student);
		return "redirect:/admin/students";
	}
	
	
	@GetMapping("/{userId}/activate")
	public String activateUser(@PathVariable("userId") Long id) {
		User user = this.userService.findById(id);
		if (user == null) return "redirect:/admin/students";
		this.userService.activateUser(user.getId());
		return "redirect:/admin/students";
	}
	
	@GetMapping("/{userId}/deactivate")
	public String deactivateUser(@PathVariable("userId") Long id) {
		User user = this.userService.findById(id);
		if (user == null) return "redirect:/admin/students";
		this.userService.deactivateUser(id);
		return "redirect:/admin/students";
	}
}
