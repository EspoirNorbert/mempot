package com.app.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@GetMapping({"/dashboard" , ""})
	public String home() {
		return "views/user/dashboard";
	}
}
