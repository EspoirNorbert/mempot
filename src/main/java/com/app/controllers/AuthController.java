package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.models.Grade;
import com.app.models.Sector;
import com.app.models.Student;
import com.app.services.GradeService;
import com.app.services.SectorService;

@Controller
public class AuthController {
	
	@Autowired private GradeService gradeService;
	@Autowired private SectorService sectorService;

	@GetMapping("/register")
	public String register(Model model) {
		Student student = new Student();
		List<Sector> sectors = sectorService.list();
		List<Grade> grades = gradeService.list();
		
		model.addAttribute("student", student);
		model.addAttribute("grades", grades);
		model.addAttribute("sectors", sectors);
		return "views/register";
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "views/login";
	}

}
