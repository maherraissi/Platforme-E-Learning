package glsia.elearning.repository;

import glsia.elearning.models.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {

    // Trouver toutes les consultations pour un cours donné
    List<Consultation> findByCoursId(int coursId);

    // Trouver toutes les consultations pour un utilisateur donné
    List<Consultation> findByUserId(int userId);

    // Trouver toutes les consultations après une certaine date
    List<Consultation> findByConsultationDateAfter(Date date);

    // Trouver toutes les consultations avant une certaine date
    List<Consultation> findByConsultationDateBefore(Date date);
}