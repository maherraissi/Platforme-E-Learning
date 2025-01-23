package glsia.elearning.security.services;

import glsia.elearning.models.Lesson;

import glsia.elearning.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    // Ajouter une leçon
    public Lesson ajouterLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    // Mettre à jour une leçon (accepte un objet Lesson avec l'ID déjà défini)
    public Lesson mettreAJourLesson(Lesson lesson) {
        if (lessonRepository.existsById(lesson.getId())) {
            return lessonRepository.save(lesson);
        } else {
            return null; // Si la leçon n'existe pas
        }
    }

    // Supprimer une leçon
    public boolean supprimerLesson(int id) {
        if (lessonRepository.existsById(id)) {
            lessonRepository.deleteById(id);
            return true;
        }
        return false; // Si la leçon n'existe pas
    }

    // Trouver les leçons d'un cours
    public List<Lesson> trouverLessonsParCoursId(int coursId) {
        return lessonRepository.findByCoursId(coursId);
    }

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Lesson addLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public Lesson updateLesson(Long id, Lesson lesson) {
        if (lessonRepository.existsById(Math.toIntExact(id))) {
            lesson.setId(Math.toIntExact(id));
            return lessonRepository.save(lesson);
        } else {
            return null;
        }
    }

    public void deleteLesson(Long id) {
        lessonRepository.deleteById(Math.toIntExact(id));
    }
}
