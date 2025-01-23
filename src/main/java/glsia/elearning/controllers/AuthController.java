package glsia.elearning.controllers;

import glsia.elearning.exception.TokenRefreshException;
import glsia.elearning.payload.request.SignupRequest;
import glsia.elearning.payload.request.TokenRefreshRequest;
import glsia.elearning.payload.response.MessageResponse;
import glsia.elearning.payload.response.TokenRefreshResponse;
import glsia.elearning.repository.RoleRepository;
import glsia.elearning.repository.UserRepository;
import glsia.elearning.security.jwt.JwtUtils;
import glsia.elearning.security.services.RefreshTokenService;
import glsia.elearning.security.services.UserDetailsImpl;
import glsia.elearning.models.ERole;
import glsia.elearning.models.Role;
import glsia.elearning.models.User;
import glsia.elearning.payload.request.LoginRequest;
import glsia.elearning.models.RefreshToken;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  RefreshTokenService refreshTokenService;

  // Afficher la page d'inscription
  @GetMapping("/signup")
  public String getSignupPage() {
    return "signup";
  }

  // Afficher la page de connexion
  @GetMapping("/signin")
  public String getSigninPage() {
    return "signin";
  }

  // Inscription d'un utilisateur
  @PostMapping("/signup")
  public RedirectView registerUser(
          @RequestParam String username,
          @RequestParam String email,
          @RequestParam String password,
          @RequestParam String nom,
          @RequestParam String prenom,
          @RequestParam String telephone,
          @RequestParam(required = false) Set<String> role, // Rôle facultatif
          RedirectAttributes redirectAttributes
  ) {
    // Vérifier si l'utilisateur existe déjà
    if (userRepository.existsByUsername(username)) {
      redirectAttributes.addFlashAttribute("message", "Error: Username is already taken!");
      return new RedirectView("/api/auth/signup");
    }

    if (userRepository.existsByEmail(email)) {
      redirectAttributes.addFlashAttribute("message", "Error: Email is already in use!");
      return new RedirectView("/api/auth/signup");
    }

    // Créer un nouvel utilisateur
    User user = new User(username, email, encoder.encode(password), nom, prenom, telephone);
    user.setApproved(false); // Par défaut, l'utilisateur n'est pas approuvé

    // Assigner un rôle
    Set<Role> roles = assignRoles(role);
    user.setRoles(roles);

    userRepository.save(user);

    redirectAttributes.addFlashAttribute("message", "User registered successfully!");
    return new RedirectView("/api/auth/signin");
  }

  // Connexion d'un utilisateur
  @PostMapping("/signin")
  public RedirectView authenticateUser(
          @RequestParam String username,
          @RequestParam String password,
          RedirectAttributes redirectAttributes
  ) {
    try {
      // Authentifier l'utilisateur
      Authentication authentication = authenticationManager
              .authenticate(new UsernamePasswordAuthenticationToken(username, password));

      // Définir l'authentification dans le contexte de sécurité
      SecurityContextHolder.getContext().setAuthentication(authentication);

      // Récupérer les détails de l'utilisateur
      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

      // Récupérer les rôles de l'utilisateur
      List<String> roles = userDetails.getAuthorities().stream()
              .map(item -> item.getAuthority())
              .collect(Collectors.toList());

      // Rediriger en fonction du rôle
      if (roles.contains("ROLE_PROFESSOR")) {
        return new RedirectView("/professor/home"); // Rediriger vers la route du ProfessorController
      } else if (roles.contains("ROLE_ETUDIANT")) {
        return new RedirectView("/etudiant/home"); // Rediriger vers la route de l'étudiant
      } else if (roles.contains("ROLE_ADMIN")) {
        return new RedirectView("/admin/dashboard"); // Rediriger vers la route de l'administrateur
      } else {
        redirectAttributes.addFlashAttribute("message", "Error: Unauthorized role!");
        return new RedirectView("/api/auth/signin");
      }
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", "Error: Invalid credentials!");
      return new RedirectView("/api/auth/signin");
    }
  }

  // Méthode utilitaire pour assigner des rôles
  private Set<Role> assignRoles(Set<String> roles) {
    Set<Role> assignedRoles = new HashSet<>();

    if (roles == null || roles.isEmpty()) {
      // Rôle par défaut : ETUDIANT
      Role defaultRole = roleRepository.findByName(ERole.ROLE_ETUDIANT)
              .orElseThrow(() -> new RuntimeException("Error: Default role (ETUDIANT) is not found."));
      assignedRoles.add(defaultRole);
    } else {
      // Assigner les rôles spécifiés
      for (String role : roles) {
        switch (role.toLowerCase()) {
          case "professor":
            Role profRole = roleRepository.findByName(ERole.ROLE_PROFESSOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role PROFESSOR is not found."));
            assignedRoles.add(profRole);
            break;
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role ADMIN is not found."));
            assignedRoles.add(adminRole);
            break;
          default:
            Role etudiantRole = roleRepository.findByName(ERole.ROLE_ETUDIANT)
                    .orElseThrow(() -> new RuntimeException("Error: Role ETUDIANT is not found."));
            assignedRoles.add(etudiantRole);
        }
      }
    }

    return assignedRoles;
  }
}