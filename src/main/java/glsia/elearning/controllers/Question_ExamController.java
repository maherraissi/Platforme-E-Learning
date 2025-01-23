package glsia.elearning.controllers;

import glsia.elearning.models.Question_Exam;
import glsia.elearning.security.services.Question_ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/questions")
public class Question_ExamController {

    private final Question_ExamService questionExamService;

    @Autowired
    public Question_ExamController(Question_ExamService questionExamService) {
        this.questionExamService = questionExamService;
    }

    // Liste des questions
    @GetMapping
    public String listQuestions(Model model) {
        model.addAttribute("questions", questionExamService.getAllQuestions());
        return "liste_question";
    }

    // Ajouter une nouvelle question
    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("question", new Question_Exam());
        return "new_question";
    }

    @PostMapping("/new")
    public String addNewQuestion(@ModelAttribute("question") Question_Exam questionExam) {
        questionExamService.saveQuestion(questionExam);
        return "redirect:/questions";
    }

    // Modifier une question existante
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Question_Exam questionExam = questionExamService.getQuestionById(id);
        model.addAttribute("question", questionExam);
        return "update_question";
    }

    @PostMapping("/edit/{id}")
    public String updateQuestion(@PathVariable("id") int id, @ModelAttribute("question") Question_Exam questionExam) {
        questionExam.setId(id);
        questionExamService.saveQuestion(questionExam);
        return "redirect:/questions";
    }

    // Supprimer une question
    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable("id") int id) {
        questionExamService.deleteQuestion(id);
        return "redirect:/questions";
    }
}
