package glsia.elearning.security.services;

import glsia.elearning.models.Examen;
import glsia.elearning.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamenService {

    @Autowired
    private ExamenRepository examenRepository;

    public List<Examen> getAllExams() {
        return examenRepository.findAll();
    }

    public Examen addExam(Examen examen) {
        return examenRepository.save(examen);
    }

    public Examen updateExam(Long id, Examen examen) {
        Optional<Examen> existingExamen = examenRepository.findById(id);
        if (existingExamen.isPresent()) {
            examen.setId(id);
            return examenRepository.save(examen);
        }
        return null;
    }

    public boolean deleteExam(Long id) {
        Optional<Examen> examen = examenRepository.findById(id);
        if (examen.isPresent()) {
            examenRepository.delete(examen.get());
            return true;
        }
        return false;
    }

    public List<Examen> getAllExamens() {
        return examenRepository.findAll();
    }

    public Examen addExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    public Examen updateExamen(Long id, Examen examen) {
        Optional<Examen> existingExamen = examenRepository.findById(id);
        if (existingExamen.isPresent()) {
            examen.setId(id);
            return examenRepository.save(examen);
        }
        return null;
    }

    public void deleteExamen(Long id) {
        examenRepository.deleteById(id);
    }
}
