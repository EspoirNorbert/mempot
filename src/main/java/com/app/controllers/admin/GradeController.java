package com.app.controllers.admin;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.models.Grade;
import com.app.services.GradeService;

@Controller
@RequestMapping("/admin/grades")
public class GradeController {

	private static final String MAIN_PATH = "views/admin/grades";
	private static final String SUCCESS_MESSAGE = "Un nouveau niveau a été créé avec succès !";

	@Autowired
	private GradeService gradeService;

	@GetMapping
	public String listGrades(Model model) {
		List<Grade> grades = gradeService.list();
		model.addAttribute("grades", grades);
		model.addAttribute("title", "Liste des niveaux");
		return MAIN_PATH + "/list";
	}

	@GetMapping("/new")
	public String displayNewGradeForm(Model model) {
		model.addAttribute("grade", new Grade());
		model.addAttribute("title", "Ajouter un nouveau niveau");
		return MAIN_PATH + "/new";
	}

	@PostMapping("/new")
	public String createNewGrade(@Valid @ModelAttribute("grade") Grade grade, BindingResult bindingResult, RedirectAttributes rd) {
		if (bindingResult.hasErrors()) {
			return MAIN_PATH + "/new";
		}
		gradeService.save(grade);
		rd.addFlashAttribute("success", SUCCESS_MESSAGE);
		return "redirect:/admin/grades";
	}
	
	@GetMapping("/edit/{gradeId}")
	public String displayEditGradeForm(@PathVariable("gradeId") Long id, Model model) {
		Grade grade = gradeService.findById(id);
		if (grade != null) {
			model.addAttribute("grade", grade);
		}

		model.addAttribute("title", "Modification d'un niveau");
		return MAIN_PATH + "/edit";
	}

	@GetMapping("/detail/{gradeId}")
	public String displayDetailGradeForm(@PathVariable("gradeId") Long id, Model model) {
		Grade grade = gradeService.findById(id);
		if (grade != null) {
			model.addAttribute("grade", grade);
		}
		model.addAttribute("title", "Details d'un niveau");
		return MAIN_PATH + "/detail";
	}

	@PostMapping("/update")
	public String updateGrade(@Valid @ModelAttribute("grade") Grade grade, BindingResult bindingResult, RedirectAttributes rd) {
		if (bindingResult.hasErrors()) {
			return MAIN_PATH + "/edit";
		}
		gradeService.save(grade);
		rd.addFlashAttribute("success", "Le niveau " + grade.getId() + " a été modifié avec succès");
		return "redirect:/admin/grades";
	}

	@GetMapping("/delete/{gradeId}")
	public String deleteGrade(@PathVariable("gradeId") Long id, RedirectAttributes rd) {
		if (gradeService.findById(id).getStudents().isEmpty()) {
			gradeService.delete(id);
			rd.addFlashAttribute("success", "Le niveau " + id + " a été supprimé avec succès !");
		} else {
			rd.addFlashAttribute("danger", "Suppression impossible, ce niveau contient encore des étudiants");
		}
		return "redirect:/admin/grades";
	}
}
