package com.app.controllers.admin;

import com.app.models.Sector;
import com.app.services.SectorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/sectors")
public class SectorController {

    private static final String MAIN_PATH = "views/admin/sectors";
    private static final String SUCCESS_MESSAGE = "Une nouvelle filière a été créée avec succès !";

    @Autowired
    private SectorService sectorService;

    @GetMapping
    public String listSectors(Model model) {
        List<Sector> sectors = sectorService.list();
        model.addAttribute("sectors", sectors);
        return MAIN_PATH + "/list";
    }

    @GetMapping("/new")
    public String displayNewSectorForm(Model model) {
        model.addAttribute("sector", new Sector());
        return MAIN_PATH + "/new";
    }

    @PostMapping("/new")
    public String createNewSector(@Valid @ModelAttribute("sector") Sector sector, BindingResult bindingResult, RedirectAttributes rd) {
        if (bindingResult.hasErrors()) {
            return MAIN_PATH + "/new";
        }

        sectorService.save(sector);
        rd.addFlashAttribute("success", SUCCESS_MESSAGE);
        return "redirect:/admin/sectors";
    }

    @GetMapping("/edit/{sectorId}")
    public String displayEditSectorForm(@PathVariable("sectorId") Long id, Model model) {
        Sector sector = sectorService.findById(id);
        if (sector != null) {
            model.addAttribute("sector", sector);
        }
        return MAIN_PATH + "/edit";
    }

    @GetMapping("/detail/{sectorId}")
    public String displayDetailSector(@PathVariable("sectorId") Long id, Model model) {
        Sector sector = sectorService.findById(id);
        if (sector != null) {
            model.addAttribute("sector", sector);
        }
        return MAIN_PATH + "/detail";
    }

    @PostMapping("/update")
    public String updateSector(@Valid @ModelAttribute("sector") Sector sector, BindingResult result, RedirectAttributes rd) {
        if (result.hasErrors()) {
            return MAIN_PATH + "/edit";
        }

        sectorService.save(sector);
        rd.addFlashAttribute("success", "La filière " + sector.getId() + " a été modifiée avec succès");
        return "redirect:/admin/sectors";
    }

    @GetMapping("/delete/{sectorId}")
    public String deleteSector(@PathVariable("sectorId") Long id, RedirectAttributes rd) {
        if (sectorService.findById(id).getStudents().isEmpty()) {
            sectorService.delete(id);
            rd.addFlashAttribute("success", "La filière portant l'identifiant " + id + " a été supprimée avec succès!");
        } else {
            rd.addFlashAttribute("danger", "Suppression impossible, cette filière contient encore des étudiants");
        }
        return "redirect:/admin/sectors";
    }
}
