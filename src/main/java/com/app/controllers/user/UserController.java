package com.app.controllers.user;

import com.app.models.Thesis;
import com.app.services.FilesStorageService;
import com.app.services.ThesisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private ThesisService thesisService;

	@Autowired
	private FilesStorageService filesStorageService;

	@GetMapping({"/dashboard", ""})
	public String home(Model model) {
		model.addAttribute("thesis", thesisService.getLatestThesis());
		return "views/user/dashboard";
	}

	@GetMapping("/thesis")
	public String thesis(Model model) {
		List<Thesis> thesis = thesisService.findByCurrentUser();
		model.addAttribute("thesis", thesis);
		return "views/user/thesis/list";
	}

	@GetMapping("/thesis/deposit")
	public String formDeposit(Model model) {
		model.addAttribute("thesis", new Thesis());
		return "views/user/thesis/deposit";
	}

	@PostMapping("/thesis/deposit")
	public String deposit(@Valid @ModelAttribute("thesis") Thesis thesis,
			BindingResult bindingResult,
			@RequestParam("file") MultipartFile file,
			RedirectAttributes rd) {

		System.out.println(bindingResult);
		if (bindingResult.hasErrors()) {
			return "views/user/thesis/deposit";
		}

		filesStorageService.save(file);
		String pathFile = getFilePath(file);
		thesis.setFilePath(pathFile);
		thesisService.create(thesis);
		rd.addFlashAttribute("success", "Un nouveau dépôt a été créé avec succès!");
		return "redirect:/user/thesis";
	}

	@GetMapping("/thesis/detail/{thesisId}")
	public String detailsThesis(@PathVariable("thesisId") Long id, Model model) {
		Thesis thesis = thesisService.findById(id);
		model.addAttribute("thesis", thesis);
		return "views/user/thesis/detail";
	}

	@GetMapping("/thesis/edit/{thesisId}")
	public String displayEditThesisForm(@PathVariable("thesisId") Long id, Model model) {
		Thesis thesis = thesisService.findById(id);
		if (thesis == null) {
			return "redirect:/user/thesis";
		}
		model.addAttribute("thesis", thesis);
		return "views/user/thesis/edit";
	}


	@PostMapping("/thesis/update")
	public String updateThesis(@Valid @ModelAttribute("thesis") Thesis thesis,
			BindingResult result, RedirectAttributes rd) {
		if (result.hasErrors()) {
			return "views/user/thesis/edit";
		}
		rd.addFlashAttribute("success", "Le dépôt N°" + thesis.getId() + " a été mis à jour avec succès!");
		thesisService.update(thesis);
		return "redirect:/user/thesis";
	}

	@GetMapping("/library")
	public String library(Model model) {
		List<Thesis> thesis = thesisService.list();
		model.addAttribute("thesis", thesis);
		return "views/user/libraries/list";
	}

	@GetMapping("/library/thesis/{thesisId}")
	public String getLibraryOneThesis(@PathVariable("thesisId") Long id, Model model) {
		Thesis thesis = thesisService.findById(id);
		model.addAttribute("thesis", thesis);
		return "views/user/libraries/detail";
	}

	@GetMapping("/profile")
	public String profile() {
		return "views/user/profile";
	}

	private String getFilePath(MultipartFile file) {
		return "/files/" + com.app.utils.Helper.replaceSpaceByDash(file.getOriginalFilename());
	}
}
