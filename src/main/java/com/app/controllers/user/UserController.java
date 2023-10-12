package com.app.controllers.user;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@GetMapping("/dashboard")
	public String dashbord(org.springframework.ui.Model model,Authentication authentication) {
		model.addAttribute("auth", authentication);
		return "views/user/dashboard";
	}
}
