package glsia.elearning.security.services;

import glsia.elearning.models.User;
import glsia.elearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    // Récupérer tous les utilisateurs
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Approuver un utilisateur
    public void approveUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        user.setApproved(true);
        userRepository.save(user);
    }

    // Supprimer un utilisateur
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}