package com.app.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.models.Thesis;
import com.app.services.ThesisService;

@Controller
@RequestMapping("/admin/thesis")
public class ThesisController {
	
	private static String MAIN_PATH = "views/admin/thesis";

	@Autowired
	private ThesisService thesisService;
	
	@GetMapping
	public String list(Model model) {
		List<Thesis> thesis  = thesisService.list();
		
		model.addAttribute("thesis", thesis);
		return MAIN_PATH + "/list";
	}
	
	
}
