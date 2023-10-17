package com.app.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.services.GradeService;
import com.app.services.SectorService;
import com.app.services.StudentService;
import com.app.services.ThesisService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired private SectorService sectorService;
	@Autowired private GradeService gradeService;
	@Autowired private StudentService studentService;
	@Autowired private ThesisService thesisService;

	@GetMapping({"/dashboard" , ""})
	public String home(Model model) {
		Long totalSector = this.sectorService.count();
		Long totalGrade = this.gradeService.count();
		Long totalStudent = this.studentService.count();
		Long totalThesis = this.thesisService.count();
		
		model.addAttribute("totalSector", totalSector);
		model.addAttribute("totalGrade", totalGrade);
		model.addAttribute("totalStudent", totalStudent);
		model.addAttribute("totalThesis", totalThesis);
		
		return "views/admin/dashboard";
	}
	
	@GetMapping("/profil")
	public String profile() {
		return "views/admin/profile";
	}
}
