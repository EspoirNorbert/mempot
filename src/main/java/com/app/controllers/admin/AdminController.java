package com.app.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.models.Grade;
import com.app.models.Sector;
import com.app.models.Student;
import com.app.models.Thesis;
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
	
		List<Sector> sectors = this.sectorService.getLatestSectors();
		List<Grade> grades = this.gradeService.getLatestGrades();
		List<Student> students = this.studentService.getLatestStudents();
		List<Thesis> thesis = this.thesisService.getLatestThesis();
		
		model.addAttribute("title", "Tableau de bord");
		model.addAttribute("totalSector", totalSector);
		model.addAttribute("totalGrade", totalGrade);
		model.addAttribute("totalStudent", totalStudent);
		model.addAttribute("totalThesis", totalThesis);
		
		model.addAttribute("sectors", sectors);
		model.addAttribute("grades", grades);
		model.addAttribute("students", students);
		model.addAttribute("thesis", thesis);
		
		return "views/admin/dashboard";
	}
	
	@GetMapping("/profile")
	public String profile(Model model) {
		model.addAttribute("title", "Profile");
		return "views/admin/profile";
	}
}
