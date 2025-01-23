package glsia.elearning.repository;

import glsia.elearning.models.Question_Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Question_ExamRepository extends JpaRepository<Question_Exam, Integer> {
}
