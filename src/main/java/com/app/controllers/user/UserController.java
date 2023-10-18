package com.app.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.app.models.Thesis;
import com.app.services.FilesStorageService;
import com.app.services.ThesisService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired private ThesisService thesisService;
	@Autowired private FilesStorageService filesStorageService;
	
	@GetMapping({"/dashboard" , ""})
	public String home() {
		return "views/user/dashboard";
	}
	
	@GetMapping("/thesis")
	public String thesis(Model model) {
		List<Thesis> thesis = this.thesisService.findByCurrentUser();
		model.addAttribute("thesis", thesis);
		return "views/user/thesis/list";
	}
	
	@GetMapping("/thesis/deposit")
	public String formDeposit(Model model) {
		Thesis thesis = new Thesis();
		model.addAttribute("thesis", thesis);
		return "views/user/thesis/deposit";
	}
	
	@PostMapping("/thesis/deposit")
	public String upload(@Valid @ModelAttribute("thesis") Thesis thesis,
			@RequestParam("file") MultipartFile file,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "views/user/thesis/deposit";
		}
		
		this.filesStorageService.save(file);
		String pathFile= this.getFilePath(file);
		thesis.setFilePath(pathFile);
		this.thesisService.create(thesis);
		return "redirect:/user/thesis";
	}
	
	@GetMapping("/library")
	public String library(Model model) {
		List<Thesis> thesis = this.thesisService.list();
		model.addAttribute("thesis", thesis);
		return "views/user/library";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "views/user/profile";
	}
	
	
	private String getFilePath(MultipartFile file) {
		return "/files/"+ com.app.utils.Helper.
				replaceSpaceByDash(file.getOriginalFilename());
	}
}
