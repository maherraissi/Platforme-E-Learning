package glsia.elearning.controllers;

import glsia.elearning.models.Examen;
import glsia.elearning.models.Cours;
import glsia.elearning.repository.ExamenRepository;
import glsia.elearning.repository.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/exams")
public class ExamenController {

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private CoursRepository coursRepository;

    // Lister tous les examens
    @GetMapping
    public String getAllExams(Model model) {
        List<Examen> exams = examenRepository.findAll();
        model.addAttribute("exams", exams);
        return "liste_exam"; // Vue pour afficher tous les examens
    }

    // Afficher le formulaire d'ajout d'examen
    @GetMapping("/new")
    public String showNewExamForm(Model model) {
        List<Cours> courses = coursRepository.findAll(); // Récupère tous les cours
        model.addAttribute("exam", new Examen()); // Crée un nouvel objet Examen
        model.addAttribute("courses", courses); // Passe la liste des cours à la vue
        return "new_exam"; // Vue pour ajouter un examen
    }

    // Ajouter un nouvel examen
    @PostMapping
    public String addExam(@ModelAttribute Examen exam) {
        examenRepository.save(exam); // Enregistre l'examen dans la base de données
        return "redirect:/exams"; // Redirige vers la liste des examens après l'ajout
    }

    // Afficher le formulaire de modification d'examen
    @GetMapping("/edit/{id}")
    public String showUpdateExamForm(@PathVariable Long id, Model model) {
        Optional<Examen> existingExam = examenRepository.findById(id);
        if (existingExam.isPresent()) {
            List<Cours> courses = coursRepository.findAll();
            model.addAttribute("exam", existingExam.get());
            model.addAttribute("courses", courses);
            return "update_exam"; // Vue pour modifier un examen
        } else {
            return "redirect:/exams"; // Si l'examen n'existe pas, rediriger vers la liste
        }
    }

    // Modifier un examen
    @PostMapping("/edit/{id}")
    public String updateExam(@PathVariable Long id, @ModelAttribute Examen updatedExam) {
        Optional<Examen> existingExam = examenRepository.findById(id);
        if (existingExam.isPresent()) {
            updatedExam.setId(id); // Garder l'id de l'examen existant
            examenRepository.save(updatedExam); // Enregistrer l'examen modifié
            return "redirect:/exams"; // Rediriger vers la liste des examens
        } else {
            return "redirect:/exams";
        }
    }

    // Supprimer un examen
    @GetMapping("/delete/{id}")
    public String deleteExam(@PathVariable Long id) {
        examenRepository.deleteById(id); // Supprimer l'examen par ID
        return "redirect:/exams"; // Rediriger vers la liste des examens après suppression
    }
}