package com.app.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.models.Thesis;
import com.app.services.ThesisService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired private ThesisService thesisService;
	
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
	public String deposit(Model model) {
		return "views/user/thesis/deposit";
	}
	
	@GetMapping("/library")
	public String library(Model model) {
		List<Thesis> thesis = this.thesisService.list();
		model.addAttribute("thesis", thesis);
		return "views/user/library";
	}
}
