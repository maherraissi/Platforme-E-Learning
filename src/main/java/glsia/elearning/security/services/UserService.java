package glsia.elearning.security.services;

import glsia.elearning.models.User;
import glsia.elearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Find all users
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // Find user by ID
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    // Find user by email
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Save or update a user
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        return userRepository.save(user);
    }

    // Delete a user by ID
    public void deleteUserById(int id) {
        userRepository.deleteById((long) id);
    }

    // Authenticate a user
    public boolean authenticate(String email, String rawPassword) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent() && passwordEncoder.matches(rawPassword, user.get().getPassword());
    }
}
