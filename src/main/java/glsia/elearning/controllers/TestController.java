package glsia.elearning.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Content.";
	}

	@GetMapping("/admin/usersApprove")
	@PreAuthorize("hasRole('ADMIN')")
	public String approveUsersAccess() {
		return "not approved users content.";
	}

	@GetMapping("/professor")
	@PreAuthorize("hasRole('PROFESSOR')")
	public String professorAccess() {
		return "Professor Board.";
	}

	@GetMapping("/etudiant")
	@PreAuthorize("hasRole('ETUDUIANT')")
	public String etudiantAccess() {
		return "Etudiant Board.";
	}
}
