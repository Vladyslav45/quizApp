package com.example.quiz.controller;

import com.example.quiz.model.Subject;
import com.example.quiz.service.IAdminPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private IAdminPanelService iAdminPanelService;

    @GetMapping(value = "/add")
    public String formAddSubject(Model model){
        model.addAttribute("subject", new Subject());
        return "admin/formAddSubject";
    }

    @PostMapping(value = "/add")
    public String addSubject(@ModelAttribute Subject subject){
        iAdminPanelService.addSubject(subject);
        return "redirect:/home";
    }

//    @PostMapping(value = "/addFromFile")
//    public String addFromFile(@RequestParam MultipartFile multipartFile, Model model){
//    }
}
