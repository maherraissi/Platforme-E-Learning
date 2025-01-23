package glsia.elearning.repository;

import glsia.elearning.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    List<Result> findByExamenId(int examenId);


    List<Result> findByStudentId(int studentId);

}
