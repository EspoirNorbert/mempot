package com.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	
	@GetMapping("/register")
	public String register() {
		return "views/register";
	}
	
	@GetMapping("/login")
	public String login() {
		return "views/login";
	}
	
	/*
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/login";
	}*/
}
