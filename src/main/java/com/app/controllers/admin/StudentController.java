package com.app.controllers.admin;

import com.app.beans.UpdateStudentRequest;
import com.app.models.Grade;
import com.app.models.Sector;
import com.app.models.Student;
import com.app.models.User;
import com.app.services.GradeService;
import com.app.services.SectorService;
import com.app.services.StudentService;
import com.app.services.UserService;
import com.app.utils.Helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/students")
public class StudentController {

    private static final String MAIN_PATH = "views/admin/students";

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private SectorService sectorService;

    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentService.list();
        model.addAttribute("students", students);
        model.addAttribute("title", "Liste des etudiants");
        return MAIN_PATH + "/list";
    }

    @GetMapping("/edit/{studentId}")
    public String editStudentForm(@PathVariable("studentId") Long id, Model model) {
        Student studentToEdit = studentService.findById(id);

        if (studentToEdit == null) {
            return "redirect:/admin/students";
        }

        UpdateStudentRequest student = Helper.studentToUpdateStudentRequest(studentToEdit);
        List<Sector> sectors = sectorService.list();
        List<Grade> grades = gradeService.list();

        model.addAttribute("student", student);
        model.addAttribute("grades", grades);
        model.addAttribute("sectors", sectors);
        model.addAttribute("title", "Modification d'un etudiant");
        return MAIN_PATH + "/edit";
    }

    @PostMapping("/update")
    public String updateStudent(@Valid @ModelAttribute("student") UpdateStudentRequest requestStudent,
                               BindingResult result, Model model, RedirectAttributes rd) {
       

        if (result.hasErrors()) {
            List<Sector> sectors = sectorService.list();
            List<Grade> grades = gradeService.list();
            model.addAttribute("grades", grades);
            model.addAttribute("sectors", sectors);
            return MAIN_PATH + "/edit";
        }

        Student student = Helper.updateStudentRequestToStudent(requestStudent);
        rd.addFlashAttribute("success", "Les informations de l'étudiant ayant le numéro " + requestStudent.getId() + " ont été mises à jour avec succès!");
        studentService.update(student);
        return "redirect:/admin/students";
    }

    @GetMapping("/{userId}/activate")
    public String activateUser(@PathVariable("userId") Long id, RedirectAttributes rd) {
        User user = userService.findById(id);
        if (user != null) {
            String message = "Le compte de l'étudiant " + user.getFirstname() + " " + user.getLastname() + " a été activé";
            userService.activateUser(user.getId());
            rd.addFlashAttribute("success", message);
        }
        return "redirect:/admin/students";
    }

    @GetMapping("/{userId}/deactivate")
    public String deactivateUser(@PathVariable("userId") Long id, RedirectAttributes rd) {
        User user = userService.findById(id);
        if (user != null) {
            String message = "Le compte de l'étudiant " + user.getFirstname() + " " + user.getLastname() + " a été désactivé";
            userService.deactivateUser(id);
            rd.addFlashAttribute("success", message);
        }
        return "redirect:/admin/students";
    }
}
