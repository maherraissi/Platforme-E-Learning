package glsia.elearning.controllers;

import glsia.elearning.models.Cours;
import glsia.elearning.repository.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cours")
public class CoursController {

    @Autowired
    private CoursRepository coursRepository;

    // Lister tous les cours
    @GetMapping
    public String getAllCours(Model model) {
        List<Cours> coursList = coursRepository.findAll();
        model.addAttribute("cours", coursList);
        return "liste_cours";
    }

    // Afficher le formulaire pour ajouter un nouveau cours
    @GetMapping("/new")
    public String gererCours(Model model) {
        // Ajouter la liste des cours
        List<Cours> coursList = coursRepository.findAll();
        model.addAttribute("coursList", coursList);

        // Ajouter un nouvel objet Cours pour le formulaire d'ajout
        model.addAttribute("cours", new Cours());

        return "gerer_cours"; // Le nom de la vue Thymeleaf
    }

    // Ajouter un cours
    @PostMapping
    public String addCours(@ModelAttribute Cours cours) {
        coursRepository.save(cours);
        return "redirect:/cours";
    }

    // Afficher le formulaire pour modifier un cours
    @GetMapping("/edit/{id}")
    public String showUpdateCoursForm(@PathVariable int id, Model model) {
        Optional<Cours> existingCours = coursRepository.findById(id);
        if (existingCours.isPresent()) {
            model.addAttribute("cours", existingCours.get());
            return "update_cours";
        } else {
            return "redirect:/cours";
        }
    }

    // Modifier un cours
    @PostMapping("/update/{id}")
    public String updateCours(@PathVariable int id, @ModelAttribute Cours updatedCours) {
        Optional<Cours> existingCours = coursRepository.findById(id);

        if (existingCours.isPresent()) {
            Cours cours = existingCours.get();
            cours.setName(updatedCours.getName());
            coursRepository.save(cours);
        }
        return "redirect:/cours";
    }

    // Supprimer un cours
    @GetMapping("/delete/{id}")
    public String deleteCours(@PathVariable int id) {
        if (coursRepository.existsById(id)) {
            coursRepository.deleteById(id);
        }
        return "redirect:/cours";
    }

    // Afficher la page de paiement pour un cours donné
    @GetMapping("/payer/{id}")
    public String showPaymentPage(@PathVariable int id, Model model) {
        Optional<Cours> cours = coursRepository.findById(id);
        if (cours.isPresent()) {
            model.addAttribute("cours", cours.get());
            return "paimentcours";
        } else {
            return "redirect:/cours";
        }
    }
}
