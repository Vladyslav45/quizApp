package com.example.quiz.controller;

import com.example.quiz.model.Question;
import com.example.quiz.model.Subject;
import com.example.quiz.model.ThemeSubject;
import com.example.quiz.service.IAdminPanelService;
import com.example.quiz.service.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private IAdminPanelService iAdminPanelService;

    @Autowired
    private IQuizService IQuizService;

    @GetMapping(value = "/addSubject")
    public String formAddSubject(Model model){
        model.addAttribute("subject", new Subject());
        return "admin/formAddSubject";
    }

    @PostMapping(value = "/addSubject")
    public String addSubject(@ModelAttribute Subject subject){
        iAdminPanelService.addSubject(subject);
        return "redirect:/";
    }

    @GetMapping(value = "/addThemeSubject")
    public String formToAddThemeSubject(Model model){
        List<Subject> subjects = IQuizService.getAllSubjects();

        model.addAttribute("subjectList", subjects);
        model.addAttribute("themeSubject", new ThemeSubject());
        return "admin/formAddThemeSubjects";
    }

    @GetMapping(value = "/answers")
    public String formMenuAnswers(){
        return "admin/menuAnswers";
    }

    @GetMapping(value = "/addAnswers")
    public String formAddAnswers(Model model){
        model.addAttribute("subjectList", IQuizService.getAllSubjects());
        model.addAttribute("question", new Question());
        return "admin/formAddAnswers";
    }

    @PostMapping(value = "/addFileAnswers")
    public String addFileAnswers(@RequestBody MultipartFile multipartFile, Model model){
        if (multipartFile.isEmpty()){
            model.addAttribute("messageerror", "File isn't found");
            return "redirect:/answers";
        }
        iAdminPanelService.addListAnswers(multipartFile);
        return "redirect:/";
    }

    @PostMapping(value = "/addThemeSubject")
    public String addThemeSubject(@ModelAttribute ThemeSubject themeSubject){
        iAdminPanelService.addThemeSubject(themeSubject);
        return "redirect:/";
    }

    @PostMapping(value = "/addAnswers")
    public String addAnswers(@ModelAttribute Question question){
        iAdminPanelService.addAnswersForTHemeSubject(question);
        return "redirect:/";
    }

//    @PostMapping(value = "/addFromFile")
//    public String addFromFile(@RequestParam MultipartFile multipartFile, Model model){
//    }
}
