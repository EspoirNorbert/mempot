package com.app.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.models.Sector;
import com.app.services.SectorService;

@Controller
@RequestMapping("/admin/sectors")
public class SectorController {
	
	private static String MAIN_PATH = "views/admin/sectors";

	@Autowired
	private SectorService sectorService;
	
	@GetMapping
	public String listGrade(Model model) {
		List<Sector> sectors  = sectorService.list();
		model.addAttribute("sectors", sectors);
		return MAIN_PATH + "/list";
	}
	
	@GetMapping("/new")
	public String displayNewGradeForm(Model model) {
		Sector sector = new Sector();
		model.addAttribute("sector", sector);
		return MAIN_PATH + "/new";
	}
	
	@PostMapping("/new")
	public String createNewGrade(@ModelAttribute("sector") Sector sector , 
			BindingResult bindingResult ) {
		
		if (bindingResult.hasErrors()) {
			return MAIN_PATH + "/new";
		}
		
		this.sectorService.save(sector);	
		return "redirect:/admin/sectors";
	}
	
	@GetMapping("/edit/{sectorId}")
	public String displayEditGradeForm(@PathVariable("sectorId") Long id,Model model) {
		
		Sector sector = sectorService.findById(id);

		if (sector != null) {
			model.addAttribute("sector", sector);
			model.addAttribute("title", "Édition région " + id);
		}
		return MAIN_PATH + "/edit";
	}
	
	@GetMapping("/detail/{sectorId}")
	public String displayDetailGrade(@PathVariable("sectorId") Long id,Model model) {
		
		Sector sector = sectorService.findById(id);

		if (sector != null) {
			model.addAttribute("sector", sector);
			model.addAttribute("title", "Édition région " + id);
		}
		return MAIN_PATH + "/detail";
	}
	
	@PostMapping("/update")
	public String updateGrade(@ModelAttribute("sector") Sector sector) {
		System.out.println(sector);
		this.sectorService.save(sector);
		return "redirect:/admin/sectors";
	}
	
	@GetMapping("/delete/{sectorId}")
	public String deleteGrade(@PathVariable("sectorId") Long id ) {
		this.sectorService.delete(id);
		return "redirect:/admin/sectors";
	}
}
