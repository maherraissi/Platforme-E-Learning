package glsia.elearning.security.services;

import glsia.elearning.models.Cours;
import glsia.elearning.repository.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursService {

    private final CoursRepository coursRepository;

    @Autowired
    public CoursService(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    // Ajouter un nouveau cours
    public Cours ajouterCours(Cours cours) {
        return coursRepository.save(cours);
    }

    // Modifier un cours existant
    public Cours modifierCours(int id, Cours coursDetails) {
        Cours cours = coursRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cours non trouvé pour cet ID : " + id));

        cours.setName(coursDetails.getName());
        // Vous pouvez ajouter plus de mise à jour ici si nécessaire

        return coursRepository.save(cours);
    }

    // Supprimer un cours
    public void supprimerCours(int id) {
        Cours cours = coursRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cours non trouvé pour cet ID : " + id));

        coursRepository.delete(cours);
    }

    // Consulter tous les cours
    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }

    // Consulter un cours par son ID
    public Cours getCoursById(int id) {
        return coursRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cours non trouvé pour cet ID : " + id));
    }

    public List<Cours> getAllCourses() {
        return coursRepository.findAll();
    }

    public Cours addCourse(Cours cours) {
        return coursRepository.save(cours);
    }

    public Cours updateCourse(Long id, Cours cours) {
        Cours existingCourse = coursRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Course not found for this id: " + id));

        existingCourse.setName(cours.getName());
        // You can add more updates here if needed

        return coursRepository.save(existingCourse);
    }

    public void deleteCourse(Long id) {
        Cours existingCourse = coursRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Course not found for this id: " + id));

        coursRepository.delete(existingCourse);
    }
}
