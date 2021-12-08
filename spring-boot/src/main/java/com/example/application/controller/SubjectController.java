package com.example.application.controller;

import com.example.application.dal.model.Subject;
import com.example.application.dal.model.User;
import com.example.application.dto.student.StudentCreateDTO;
import com.example.application.dto.student.StudentUpdateDTO;
import com.example.application.dto.subject.SubjectCreateDTO;
import com.example.application.dto.subject.SubjectUpdateDTO;
import com.example.application.service.StudentService;
import com.example.application.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subjects")
    public String getSubjectsPage(Model model) {
        List<Subject> subjects = subjectService.getSubjects();
        model.addAttribute("subjects", subjects);

        return "subjects/list";
    }

    @GetMapping("/subjects/create")
    public String getCreateSubjectPage() {
        return "subjects/create";
    }

    @PostMapping(path = "/subjects/create")
    public String createSubject(@Valid SubjectCreateDTO subjectCreateDTO, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "subjects/create";
        }

        subjectService.createSubject(subjectCreateDTO);

        List<Subject> subjects = subjectService.getSubjects();
        model.addAttribute("subjects", subjects);

        return "redirect:/subjects";
    }

    @GetMapping("/subjects/update/{id}")
    public String getUpdateSubjectPage(@PathVariable Integer id, Model model) {
        Optional<Subject> subject = subjectService.getSubjectById(id);

        if (!subject.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subject not found");
        }

        model.addAttribute("subject", subject.get());

        return "subjects/update";
    }

    @PostMapping(path = "/subjects/update/{id}")
    public String updateSubject(@PathVariable("id") int id, @Valid SubjectUpdateDTO subjectUpdateDTO, BindingResult result, Model model){
        if (result.hasErrors()) {
            return this.getUpdateSubjectPage(id, model);
        }

        subjectService.updateSubject(id, subjectUpdateDTO);

        List<Subject> subjects = subjectService.getSubjects();
        model.addAttribute("subjects", subjects);

        return "redirect:/subjects";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteSubject(@PathVariable("id") int id, Model model) {
        subjectService.deleteSubject(id);

        List<Subject> subjects = subjectService.getSubjects();
        model.addAttribute("subjects", subjects);

        return "redirect:/subjects";
    }
}