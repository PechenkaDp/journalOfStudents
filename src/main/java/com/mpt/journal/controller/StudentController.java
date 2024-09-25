package com.mpt.journal.controller;

import com.mpt.journal.model.StudentModel;
import com.mpt.journal.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.findAllStudents());
        return "studentList";
    }

    @PostMapping("/students/add")
    public String addStudent(@RequestParam String name, @RequestParam String lastName, @RequestParam String firstName, @RequestParam String middleName) {
        StudentModel student = new StudentModel(0, name, lastName, firstName, middleName);
        studentService.addStudent(student);
        return "redirect:/students";
    }

    @PostMapping("/students/update")
    public String updateStudent(@RequestParam int id, @RequestParam String name, @RequestParam String lastName, @RequestParam String firstName, @RequestParam String middleName) {
        StudentModel student = new StudentModel(id, name, lastName, firstName, middleName);
        studentService.updateStudent(student);
        return "redirect:/students";
    }

    @PostMapping("/students/delete")
    public String deleteStudent(@RequestParam int id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @PostMapping("/students/deleteMultiple")
    public String deleteMultipleStudents(@RequestParam List<Integer> ids) {
        studentService.deleteMultipleStudents(ids);
        return "redirect:/students";
    }

    @GetMapping("/students/search")
    public String searchStudents(@RequestParam String searchTerm, Model model) {
        model.addAttribute("students", studentService.searchStudents(searchTerm));
        return "studentList";
    }

    @GetMapping("/students/filter")
    public String filterStudents(@RequestParam(required = false) String name, @RequestParam(required = false) String lastName, @RequestParam(required = false) String firstName, Model model) {
        model.addAttribute("students", studentService.filterStudents(name, lastName, firstName));
        return "studentList";
    }
}
