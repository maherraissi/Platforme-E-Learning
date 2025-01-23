package glsia.elearning.repository;

import glsia.elearning.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    // Trouver les leçons associées à un cours spécifique
    List<Lesson> findByCoursId(int coursId);

    // Trouver les leçons programmées après une certaine date
    List<Lesson> findByStartAtAfter(LocalDateTime startAt);
}
