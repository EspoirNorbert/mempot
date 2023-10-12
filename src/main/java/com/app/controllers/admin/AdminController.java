package com.app.controllers.admin;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@GetMapping("/dashboard")
	public String dashbord(org.springframework.ui.Model model,Authentication authentication) {
		model.addAttribute("auth", authentication);
		return "views/dashboard";
	}
	
}
