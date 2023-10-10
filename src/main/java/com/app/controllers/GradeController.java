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
	
	private static String MAIN_PATH = "views/grades";

	@Autowired
	private GradeService gradeService;
	
	@GetMapping
	public String listGrade(Model model) {
		List<Grade> grades  = gradeService.list();
		model.addAttribute("grades", grades);
		return MAIN_PATH + "/list";
	}
	
	@GetMapping("/new")
	public String displayNewGradeForm(Model model) {
		Grade grade = new Grade();
		model.addAttribute("grade", grade);
		return MAIN_PATH + "/new";
	}
	
	@PostMapping("/new")
	public String createNewGrade(@ModelAttribute("grade") Grade grade , 
			BindingResult bindingResult ) {
		
		if (bindingResult.hasErrors()) {
			return MAIN_PATH + "/new";
		}
		
		this.gradeService.save(grade);
		
		return "redirect:/grades";
	}
	
	@GetMapping("/edit/{gradeId}")
	public String displayEditGradeForm(@PathVariable("gradeId") Integer id,Model model) {
		
		Grade grade = gradeService.findById(id);

		if (grade != null) {
			model.addAttribute("grade", grade);
			model.addAttribute("title", "Édition région " + id);
		}
		return MAIN_PATH + "/edit";
	}
	
	@PostMapping("/update")
	public String modifier(@ModelAttribute("grade") Grade grade) {
		System.out.println(grade);
		
		this.gradeService.save(grade);
		return "redirect:/grades";
	}
	
	@GetMapping("/delete/{gradeId}")
	public String supprimeProduit(@PathVariable("gradeId") Integer id ) {
		this.gradeService.delete(id);
		return "redirect:/grades";
	}
}
