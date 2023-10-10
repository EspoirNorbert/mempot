package com.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

	@GetMapping("/")
	public String index() {
		return "views/home";
	}
	
	@GetMapping("/dashboard")
	public String dashbord() {
		return "views/dashboard";
	}
}