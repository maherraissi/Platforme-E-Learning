package glsia.elearning.controllers;

import glsia.elearning.models.Lesson;
import glsia.elearning.models.Cours;
import glsia.elearning.repository.LessonRepository;
import glsia.elearning.repository.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CoursRepository coursRepository;

    // Lister toutes les leçons
    @GetMapping
    public String getAllLessons(Model model) {
        List<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("lessons", lessons);
        return "liste_lesson"; // Vue pour afficher toutes les leçons
    }

    // Afficher le formulaire d'ajout de leçon
    @GetMapping("/new")
    public String showNewLessonForm(Model model) {
        List<Cours> courses = coursRepository.findAll(); // Récupère tous les cours
        model.addAttribute("lesson", new Lesson()); // Crée un nouvel objet Lesson
        model.addAttribute("courses", courses); // Passe la liste des cours à la vue
        return "new_lesson"; // Vue pour ajouter une leçon
    }

    // Ajouter une nouvelle leçon
    @PostMapping
    public String addLesson(Lesson lesson) {
        lessonRepository.save(lesson); // Enregistre la leçon dans la base de données
        return "redirect:/lessons"; // Redirige vers la liste des leçons après l'ajout
    }

    // Afficher le formulaire de modification de leçon
    @GetMapping("/edit/{id}")
    public String showUpdateLessonForm(@PathVariable int id, Model model) {
        Optional<Lesson> existingLesson = lessonRepository.findById(id);
        if (existingLesson.isPresent()) {
            List<Cours> courses = coursRepository.findAll();
            model.addAttribute("lesson", existingLesson.get());
            model.addAttribute("courses", courses);
            return "update_lesson"; // Vue pour modifier une leçon
        } else {
            return "redirect:/lessons"; // Si la leçon n'existe pas, rediriger vers la liste
        }
    }

    // Modifier une leçon
    @PostMapping("/edit/{id}")
    public String updateLesson(@PathVariable int id, Lesson updatedLesson) {
        Optional<Lesson> existingLesson = lessonRepository.findById(id);
        if (existingLesson.isPresent()) {
            updatedLesson.setId(id); // Garder l'id de la leçon existante
            lessonRepository.save(updatedLesson); // Enregistrer la leçon modifiée
            return "redirect:/lessons"; // Rediriger vers la liste des leçons
        } else {
            return "redirect:/lessons";
        }
    }

    // Supprimer une leçon
    @GetMapping("/delete/{id}")
    public String deleteLesson(@PathVariable int id) {
        lessonRepository.deleteById(id); // Supprimer la leçon par ID
        return "redirect:/lessons"; // Rediriger vers la liste des leçons après suppression
    }
}
