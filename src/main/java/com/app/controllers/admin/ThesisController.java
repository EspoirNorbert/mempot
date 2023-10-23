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

import com.app.models.Thesis;
import com.app.services.ThesisService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/thesis")
public class ThesisController {
	
	private static String MAIN_PATH = "views/admin/thesis";

	@Autowired
	private ThesisService thesisService;
	
	@GetMapping
	public String list(Model model) {
		List<Thesis> thesis  = thesisService.list();
		model.addAttribute("thesis", thesis);
		return MAIN_PATH + "/list";
	}
	
	@GetMapping("/edit/{thesisId}")
	public String displayEditThesisForm(@PathVariable("thesisId") Long id,Model model) {
		
		Thesis thesis = thesisService.findById(id);

		if (thesis == null) {
			return "redirect:/admin/students";
		}
		
		model.addAttribute("thesis", thesis);
		return MAIN_PATH + "/edit";
	}
	
	@GetMapping("/detail/{thesisId}")
	public String detailThesis(@PathVariable("thesisId") Long id,Model model) {
		
		Thesis thesis = thesisService.findById(id);

		if (thesis == null) {
			return "redirect:/admin/students";
		}
	
		model.addAttribute("thesis", thesis);
		return MAIN_PATH + "/detail";
	}
	
	@PostMapping("/update")
	public String modifier(@Valid @ModelAttribute("thesis") Thesis thesis,BindingResult result,Model model , RedirectAttributes rd) {
	
		if (result.hasErrors()) {
			return MAIN_PATH+ "/edit";
		}
	
		rd.addFlashAttribute("success","La these " + thesis.getId() + 
				" a été modifiée avec succès");	
		this.thesisService.update(thesis);
		return "redirect:/admin/thesis";
	}	
}
