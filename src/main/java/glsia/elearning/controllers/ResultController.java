package glsia.elearning.controllers;

import glsia.elearning.models.Result;
import glsia.elearning.models.Examen;
import glsia.elearning.repository.ResultRepository;
import glsia.elearning.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private ExamenRepository examenRepository;

    // Lister tous les résultats
    @GetMapping
    public String getAllResults(Model model) {
        List<Result> results = resultRepository.findAll();
        model.addAttribute("results", results);
        return "liste_result"; // Vue pour afficher tous les résultats
    }

    // Afficher le formulaire d'ajout de résultat
    @GetMapping("/new")
    public String showNewResultForm(Model model) {
        List<Examen> exams = examenRepository.findAll(); // Récupère tous les examens
        model.addAttribute("result", new Result()); // Crée un nouvel objet Result
        model.addAttribute("exams", exams); // Passe la liste des examens à la vue
        return "new_result"; // Vue pour ajouter un résultat
    }

    // Ajouter un nouveau résultat
    @PostMapping
    public String addResult(@ModelAttribute Result result) {
        resultRepository.save(result); // Enregistre le résultat dans la base de données
        return "redirect:/results"; // Redirige vers la liste des résultats après l'ajout
    }

    // Afficher le formulaire de modification de résultat
    @GetMapping("/edit/{id}")
    public String showUpdateResultForm(@PathVariable int id, Model model) {
        Optional<Result> existingResult = resultRepository.findById(id);
        if (existingResult.isPresent()) {
            List<Examen> exams = examenRepository.findAll();
            model.addAttribute("result", existingResult.get());
            model.addAttribute("exams", exams);
            return "update_result"; // Vue pour modifier un résultat
        } else {
            return "redirect:/results"; // Si le résultat n'existe pas, rediriger vers la liste
        }
    }

    // Modifier un résultat
    @PostMapping("/edit/{id}")
    public String updateResult(@PathVariable int id, @ModelAttribute Result updatedResult) {
        Optional<Result> existingResult = resultRepository.findById(id);
        if (existingResult.isPresent()) {
            updatedResult.setId(id); // Garder l'id du résultat existant
            resultRepository.save(updatedResult); // Enregistrer le résultat modifié
            return "redirect:/results"; // Rediriger vers la liste des résultats
        } else {
            return "redirect:/results";
        }
    }

    // Supprimer un résultat
    @GetMapping("/delete/{id}")
    public String deleteResult(@PathVariable int id) {
        resultRepository.deleteById(id); // Supprimer le résultat par ID
        return "redirect:/results"; // Rediriger vers la liste des résultats après suppression
    }
}