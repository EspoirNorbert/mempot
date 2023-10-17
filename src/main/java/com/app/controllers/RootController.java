package com.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

	@GetMapping({"/home","/"})
	public String index() {
		return "views/home";
	}
	
}
