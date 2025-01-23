package glsia.elearning.repository;

import glsia.elearning.models.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursRepository extends JpaRepository<Cours, Integer> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}
