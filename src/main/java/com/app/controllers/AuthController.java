package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.beans.RegisterRequest;
import com.app.config.CustomUserDetailService;
import com.app.models.Grade;
import com.app.models.Sector;
import com.app.models.Student;
import com.app.models.User;
import com.app.services.GradeService;
import com.app.services.SectorService;
import com.app.services.UserService;
import com.app.utils.Helper;

import jakarta.validation.Valid;

@Controller
public class AuthController {

	@Autowired private GradeService gradeService;
	@Autowired private SectorService sectorService;
	@Autowired private UserService userService;
	@Autowired private CustomUserDetailService authService;

	@GetMapping("/register")
	public String registerForm(Model model) {
		RegisterRequest student = new RegisterRequest();
		List<Sector> sectors = sectorService.list();
		List<Grade> grades = gradeService.list();

		model.addAttribute("student", student);
		model.addAttribute("grades", grades);
		model.addAttribute("sectors", sectors);
		model.addAttribute("title", "Crée un compte");
		return "views/register";
	}

	@PostMapping("/save")
	public String register(@Valid @ModelAttribute("student") RegisterRequest registerRequest,BindingResult bindingResult,RedirectAttributes reditAttributes) {

		if (bindingResult.hasErrors()) {
			return "views/register";
		}

		Student student = Helper.convertRegisterFormToStudent(registerRequest);

		this.userService.createStudent(student);

		reditAttributes
		.addFlashAttribute("success", 
				"Vous etes inscrit avec success "
						+ "! vous pouvez vous connecter des maintenant !");

		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login() {
		return "views/login";
	}

	@PostMapping("/profile")
	public String updateProfile(@Valid @ModelAttribute("currentUser") User user, 
			BindingResult results, RedirectAttributes rd) {

		String viewAccordingProfile = 
				user.getRoles().get(0).getName().equals("USER") ? "user" : "admin";

		if (results.hasErrors()) {
			return "views/"+ viewAccordingProfile + "/profile";
		}

		user.setId(authService.getCurrentUser().getId());
		userService.save(user);
		rd.addFlashAttribute("success", "Votre profile a été mise à jour avec success !");
		return "redirect:/"+viewAccordingProfile+"/profile";	
	}
}
