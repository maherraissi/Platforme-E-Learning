package glsia.elearning.repository;

import glsia.elearning.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  // Find users by username
  Optional<User> findByUsername(String username);

  // Check if a user exists by username
  Boolean existsByUsername(String username);

  // Check if a user exists by email
  Boolean existsByEmail(String email);

  // Find users by email
  Optional<User> findByEmail(String email);

  // Find users by role using JPQL
  @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
  List<User> findByRole(@Param("roleName") String roleName);
}