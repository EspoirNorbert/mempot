package com.app.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class RootController {

	public String index() {
		return "index";
	}
}
