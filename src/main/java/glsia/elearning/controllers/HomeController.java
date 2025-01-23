package glsia.elearning.controllers;

import glsia.elearning.models.Cours;
import glsia.elearning.repository.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CoursRepository courseRepository;

    /**
     * Affiche la page d'accueil avec la liste des cours disponibles.
     *
     * @param model Le modèle pour passer des données à la vue.
     * @return Le nom de la vue Thymeleaf (home.html).
     */
    @GetMapping("/")
    public String homePage(Model model) {

        // Retourner le nom de la vue Thymeleaf (home.html)
        return "home";
    }
}