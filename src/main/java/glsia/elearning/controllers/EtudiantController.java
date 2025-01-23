package glsia.elearning.controllers;

import glsia.elearning.models.*;
import glsia.elearning.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/etudiant")
public class EtudiantController {

    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private Question_ExamRepository questionExamRepository;

    @Autowired
    private ResultRepository resultRepository;

    // Page d'accueil de l'étudiant
    @GetMapping("/home")
    public String etudiantHome(Model model) {
        // Ajoutez des données au modèle si nécessaire
        model.addAttribute("message", "Bienvenue, Étudiant !");
        return "etudiant_home"; // Vue de la page d'accueil de l'étudiant
    }

    // Consulter la liste des cours
    @GetMapping("/cours")
    public String getAllCours(Model model) {
        List<Cours> coursList = coursRepository.findAll();
        model.addAttribute("cours", coursList);
        return "cours"; // Vue pour afficher tous les cours
    }

    // Consulter la liste des leçons
    @GetMapping("/lessons")
    public String getAllLessons(Model model) {
        List<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("lessons", lessons);
        return "lessons"; // Vue pour afficher toutes les leçons
    }

    // Consulter la liste des examens
    @GetMapping("/exams")
    public String getAllExams(Model model) {
        List<Examen> exams = examenRepository.findAll();
        model.addAttribute("exams", exams);
        return "exams"; // Vue pour afficher tous les examens
    }

    // Consulter la liste des questions d'examen
    @GetMapping("/questions")
    public String getAllQuestions(Model model) {
        List<Question_Exam> questions = questionExamRepository.findAll();
        model.addAttribute("questions", questions);
        return "questions"; // Vue pour afficher toutes les questions
    }

    // Consulter les résultats de l'étudiant
    @GetMapping("/results")
    public String getStudentResults(Model model) {
        // Supposons que l'ID de l'étudiant est disponible dans la session
        // int studentId = (int) session.getAttribute("studentId");
        int studentId = 1; // Remplacez par l'ID de l'étudiant connecté

        List<Result> results = resultRepository.findByStudentId(studentId);
        model.addAttribute("results", results);
        return "results"; // Vue pour afficher les résultats de l'étudiant
    }
}