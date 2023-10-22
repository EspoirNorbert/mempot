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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.models.Grade;
import com.app.services.GradeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/grades")
public class GradeController {

	private static String MAIN_PATH = "views/admin/grades";

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
	public String createNewGrade(@Valid @ModelAttribute("grade") Grade grade 
			,BindingResult bindingResult,RedirectAttributes rd) {

		if (bindingResult.hasErrors()) {
			return MAIN_PATH + "/new";
		}
		this.gradeService.save(grade);
		rd.addFlashAttribute("success", "Un nouveau niveau a été crée avec success !");
		return "redirect:/admin/grades";
	}

	@GetMapping("/edit/{gradeId}")
	public String displayEditGradeForm(@PathVariable("gradeId") Long id,Model model) {

		Grade grade = gradeService.findById(id);

		if (grade != null) {
			model.addAttribute("grade", grade);
		}
		return MAIN_PATH + "/edit";
	}

	@GetMapping("/detail/{gradeId}")
	public String displayDetailGradeForm(@PathVariable("gradeId") Long id,Model model) {

		Grade grade = gradeService.findById(id);

		if (grade != null) {
			model.addAttribute("grade", grade);
		}
		
		return MAIN_PATH + "/detail";
	}

	@PostMapping("/update")
	public String modifier(@Valid @ModelAttribute("grade") Grade grade, 
			BindingResult results,
			RedirectAttributes rd) {

		if (results.hasErrors()) return MAIN_PATH+ "/edit";

		this.gradeService.save(grade);
		rd.addFlashAttribute("success", "Le niveau " + grade.getId() + " a été modifie avec success");
		return "redirect:/admin/grades";
	}

	@GetMapping("/delete/{gradeId}")
	public String supprimeProduit(@PathVariable("gradeId") Long id, RedirectAttributes rd) {
		// search grade and check il student are affilated
		if (this.gradeService.findById(id).getStudents().size() == 0) {
			this.gradeService.delete(id);
			rd.addFlashAttribute("success", "Le niveau " + id+ " a été supprimé avec success !");
		} else {
			rd.addFlashAttribute("danger","Suppression impossible, Ce niveau contient encore des etudiants");
		}
		return "redirect:/admin/grades";
	}	
}
