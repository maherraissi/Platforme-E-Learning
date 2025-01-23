package glsia.elearning.controllers;

import glsia.elearning.models.*;
import glsia.elearning.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private Question_ExamRepository questionExamRepository;

    @Autowired
    private ResultRepository resultRepository;

    // Page d'accueil de l'administrateur
    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        // Ajoutez des données au modèle si nécessaire
        model.addAttribute("message", "Bienvenue, Admin !");
        return "admin_dashboard"; // Vue de la page d'accueil du professeur
    }

    // Gestion des cours

    @GetMapping("/cours")
    public String getAllCours(Model model) {
        List<Cours> coursList = coursRepository.findAll();
        model.addAttribute("cours", coursList);
        return "liste_cours"; // Vue pour afficher tous les cours
    }

    @GetMapping("/cours/new")
    public String showNewCoursForm(Model model) {
        model.addAttribute("cours", new Cours());
        return "new_cours"; // Vue pour ajouter un nouveau cours
    }

    @PostMapping("/cours")
    public String addCours(@ModelAttribute Cours cours) {
        coursRepository.save(cours);
        return "redirect:/cours"; // Redirige vers la liste des cours après l'ajout
    }

    @GetMapping("/cours/edit/{id}")
    public String showUpdateCoursForm(@PathVariable int id, Model model) {
        Optional<Cours> existingCours = coursRepository.findById(id);
        if (existingCours.isPresent()) {
            model.addAttribute("cours", existingCours.get());
            return "update_cours"; // Vue pour modifier un cours
        } else {
            return "redirect:/cours";
        }
    }

    @PostMapping("/cours/edit/{id}")
    public String updateCours(@PathVariable int id, @ModelAttribute Cours updatedCours) {
        Optional<Cours> existingCours = coursRepository.findById(id);
        if (existingCours.isPresent()) {
            updatedCours.setId(id);
            coursRepository.save(updatedCours);
            return "redirect:/cours";
        } else {
            return "redirect:/cours";
        }
    }

    @GetMapping("/cours/delete/{id}")
    public String deleteCours(@PathVariable int id) {
        coursRepository.deleteById(id);
        return "redirect:/cours"; // Redirige vers la liste des cours après suppression
    }

    // Gestion des leçons

    @GetMapping("/lessons")
    public String getAllLessons(Model model) {
        List<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("lessons", lessons);
        return "lessons"; // Vue pour afficher toutes les leçons
    }

    @GetMapping("/lessons/new")
    public String showNewLessonForm(Model model) {
        model.addAttribute("lesson", new Lesson());
        return "new_lesson"; // Vue pour ajouter une nouvelle leçon
    }

    @PostMapping("/lessons")
    public String addLesson(@ModelAttribute Lesson lesson) {
        lessonRepository.save(lesson);
        return "redirect:/lessons"; // Redirige vers la liste des leçons après l'ajout
    }

    @GetMapping("/lessons/edit/{id}")
    public String showUpdateLessonForm(@PathVariable int id, Model model) {
        Optional<Lesson> existingLesson = lessonRepository.findById(id);
        if (existingLesson.isPresent()) {
            model.addAttribute("lesson", existingLesson.get());
            return "update_lesson"; // Vue pour modifier une leçon
        } else {
            return "redirect:/lessons";
        }
    }

    @PostMapping("/lessons/edit/{id}")
    public String updateLesson(@PathVariable int id, @ModelAttribute Lesson updatedLesson) {
        Optional<Lesson> existingLesson = lessonRepository.findById(id);
        if (existingLesson.isPresent()) {
            updatedLesson.setId(id);
            lessonRepository.save(updatedLesson);
            return "redirect:/lessons";
        } else {
            return "redirect:/lessons";
        }
    }

    @GetMapping("/lessons/delete/{id}")
    public String deleteLesson(@PathVariable int id) {
        lessonRepository.deleteById(id);
        return "redirect:/lessons"; // Redirige vers la liste des leçons après suppression
    }

    // Gestion des examens

    @GetMapping("/exams")
    public String getAllExams(Model model) {
        List<Examen> exams = examenRepository.findAll();
        model.addAttribute("exams", exams);
        return "liste_exams"; // Vue pour afficher tous les examens
    }

    @GetMapping("/exams/new")
    public String showNewExamForm(Model model) {
        List<Cours> courses = coursRepository.findAll();
        model.addAttribute("exam", new Examen());
        model.addAttribute("courses", courses);
        return "new_exam"; // Vue pour ajouter un examen
    }

    @PostMapping("/exams")
    public String addExam(@ModelAttribute Examen exam) {
        examenRepository.save(exam);
        return "redirect:/exams"; // Redirige vers la liste des examens après l'ajout
    }

    @GetMapping("/exams/edit/{id}")
    public String showUpdateExamForm(@PathVariable Long id, Model model) {
        Optional<Examen> existingExam = examenRepository.findById(id);
        if (existingExam.isPresent()) {
            List<Cours> courses = coursRepository.findAll();
            model.addAttribute("exam", existingExam.get());
            model.addAttribute("courses", courses);
            return "update_exam"; // Vue pour modifier un examen
        } else {
            return "redirect:/exams";
        }
    }

    @PostMapping("/exams/edit/{id}")
    public String updateExam(@PathVariable Long id, @ModelAttribute Examen updatedExam) {
        Optional<Examen> existingExam = examenRepository.findById(id);
        if (existingExam.isPresent()) {
            updatedExam.setId(id);
            examenRepository.save(updatedExam);
            return "redirect:/exams";
        } else {
            return "redirect:/exams";
        }
    }

    @GetMapping("/exams/delete/{id}")
    public String deleteExam(@PathVariable Long id) {
        examenRepository.deleteById(id);
        return "redirect:/exams"; // Redirige vers la liste des examens après suppression
    }

    // Gestion des questions d'examen

    @GetMapping("/questions")
    public String getAllQuestions(Model model) {
        List<Question_Exam> questions = questionExamRepository.findAll();
        model.addAttribute("questions", questions);
        return "liste_questions"; // Vue pour afficher toutes les questions
    }

    @GetMapping("/questions/new")
    public String showNewQuestionForm(Model model) {
        List<Examen> exams = examenRepository.findAll();
        model.addAttribute("question", new Question_Exam());
        model.addAttribute("exams", exams);
        return "new_question"; // Vue pour ajouter une nouvelle question
    }

    @PostMapping("/questions")
    public String addQuestion(@ModelAttribute Question_Exam question) {
        questionExamRepository.save(question);
        return "redirect:/questions"; // Redirige vers la liste des questions après l'ajout
    }

    @GetMapping("/questions/edit/{id}")
    public String showUpdateQuestionForm(@PathVariable int id, Model model) {
        Optional<Question_Exam> existingQuestion = questionExamRepository.findById(id);
        if (existingQuestion.isPresent()) {
            List<Examen> exams = examenRepository.findAll();
            model.addAttribute("question", existingQuestion.get());
            model.addAttribute("exams", exams);
            return "update_question"; // Vue pour modifier une question
        } else {
            return "redirect:/questions";
        }
    }

    @PostMapping("/questions/edit/{id}")
    public String updateQuestion(@PathVariable int id, @ModelAttribute Question_Exam updatedQuestion) {
        Optional<Question_Exam> existingQuestion = questionExamRepository.findById(id);
        if (existingQuestion.isPresent()) {
            updatedQuestion.setId(id);
            questionExamRepository.save(updatedQuestion);
            return "redirect:/questions";
        } else {
            return "redirect:/questions";
        }
    }

    @GetMapping("/questions/delete/{id}")
    public String deleteQuestion(@PathVariable int id) {
        questionExamRepository.deleteById(id);
        return "redirect:/questions"; // Redirige vers la liste des questions après suppression
    }

    // Gestion des résultats

    @GetMapping("/results")
    public String getAllResults(Model model) {
        List<Result> results = resultRepository.findAll();
        model.addAttribute("results", results);
        return "liste_result"; // Vue pour afficher tous les résultats
    }

    @GetMapping("/results/new")
    public String showNewResultForm(Model model) {
        List<Examen> exams = examenRepository.findAll();
        model.addAttribute("result", new Result());
        model.addAttribute("exams", exams);
        return "new_result"; // Vue pour ajouter un résultat
    }

    @PostMapping("/results")
    public String addResult(@ModelAttribute Result result) {
        resultRepository.save(result);
        return "redirect:/results"; // Redirige vers la liste des résultats après l'ajout
    }

    @GetMapping("/results/edit/{id}")
    public String showUpdateResultForm(@PathVariable int id, Model model) {
        Optional<Result> existingResult = resultRepository.findById(id);
        if (existingResult.isPresent()) {
            List<Examen> exams = examenRepository.findAll();
            model.addAttribute("result", existingResult.get());
            model.addAttribute("exams", exams);
            return "admin_update_result"; // Vue pour modifier un résultat
        } else {
            return "redirect:/results";
        }
    }

    @PostMapping("/results/edit/{id}")
    public String updateResult(@PathVariable int id, @ModelAttribute Result updatedResult) {
        Optional<Result> existingResult = resultRepository.findById(id);
        if (existingResult.isPresent()) {
            updatedResult.setId(id);
            resultRepository.save(updatedResult);
            return "redirect:/admin/results";
        } else {
            return "redirect:/results";
        }
    }

    @GetMapping("/results/delete/{id}")
    public String deleteResult(@PathVariable int id) {
        resultRepository.deleteById(id);
        return "redirect:/results"; // Redirige vers la liste des résultats après suppression
    }
}