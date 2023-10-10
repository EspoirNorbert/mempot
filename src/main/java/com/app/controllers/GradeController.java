package com.app.controllers;

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

import com.app.models.Grade;
import com.app.services.GradeService;

@Controller
@RequestMapping("/grades")
public class GradeController {

	@Autowired
	private GradeService gradeService;
	
	@GetMapping
	public String listGrade(Model model) {
		List<Grade> grades  = gradeService.getAllGrades();
		model.addAttribute("grades", grades);
		return "grades/list";
	}
	
	@GetMapping("/new")
	public String displayNewGradeForm(Model model) {
		Grade grade = new Grade();
		model.addAttribute("grade", grade);
		return "grades/ajout";
	}
	
	@PostMapping("/new")
	public String createNewGrade(@ModelAttribute("grade") Grade grade , 
			BindingResult bindingResult ) {
		
		if (bindingResult.hasErrors()) {
			return "grades/ajout";
		}
		
		this.gradeService.createGrade(grade);
		
		return "redirect:/grades";
	}
	
	@GetMapping("/edit/{gradeId}")
	public String displayEditGradeForm(Model model,@PathVariable("gradeId") Integer id) {
		return null;
		//Grade grade = this.gradeService.
	}
}
