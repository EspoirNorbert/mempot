package com.app.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@GetMapping({"/dashboard" , ""})
	public String home() {
		return "views/admin/dashboard";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "views/admin/profile";
	}
	
}
