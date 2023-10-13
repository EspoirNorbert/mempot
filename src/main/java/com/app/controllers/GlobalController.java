package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.app.config.CustomUserDetailService;
import com.app.models.User;

@Controller
@ControllerAdvice
public class GlobalController {

	@Autowired private CustomUserDetailService userService;
	
	@ModelAttribute("currentUser")
	public void getCurrentUser(Model model) {
		System.out.println("Get current User is called !!!!!!");
		User auth = userService.getCurrentUser();
		if (auth != null) {
			model.addAttribute("currentUser", auth);
			System.out.println("Current User set !!!!!!");
		}
	}
}
