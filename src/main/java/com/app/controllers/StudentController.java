package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.models.Student;
import com.app.services.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	private static String MAIN_PATH = "views/students";

	@Autowired
	private StudentService studentService;
	
	@GetMapping
	public String list(Model model) {
		List<Student> students  = studentService.list();
		model.addAttribute("students", students);
		return MAIN_PATH + "/list";
	}
	
}
