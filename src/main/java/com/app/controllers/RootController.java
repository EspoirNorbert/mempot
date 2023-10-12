package com.app.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

	@GetMapping("/")
	public String index() {
		return "views/home";
	}
	
	@GetMapping("/dashboard")
	public String dashbord(org.springframework.ui.Model model,Authentication authentication) {
		model.addAttribute("auth", authentication);
		return "views/dashboard";
	}
	
}
