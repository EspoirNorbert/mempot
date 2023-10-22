package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.app.config.CustomUserDetailService;
import com.app.models.User;
import com.app.utils.Helper;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@ControllerAdvice
public class GlobalController {

	@Autowired private CustomUserDetailService userService;
	
	@ModelAttribute("currentUser")
	public void getCurrentUser(Model model, org.apache.coyote.Request request) {
		User auth = userService.getCurrentUser();
		if (auth != null) {
			model.addAttribute("userRole", userService.currentUserRole());
			model.addAttribute("userAvatar", Helper.getDefautlAvatar(auth.getFirstname(), auth.getLastname()));		
			model.addAttribute("currentUser", auth);
		}
	}
	
	@ModelAttribute("requestURI")
	public String requestURI(final HttpServletRequest request) {
	   return request.getRequestURI();
	}
}
