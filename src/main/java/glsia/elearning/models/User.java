package glsia.elearning.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(	name = "users",
		uniqueConstraints = {
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email")
		})
public class  User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

    @NotBlank
	@Size(max = 120)
	private String nom;

	@NotBlank
	@Size(max = 120)
	private String prenom;


	@NotBlank
	@Size(max = 120)
	private String telephone;


	private boolean isApproved = false;



	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id"))



	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(String username, String email, String password , String nom , String prenom  , String telephone ) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.isApproved = false;
	}

	public User(String name, String prenom, String email, String password, String phone, ERole eRole) {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() { return nom; }

	public void setNom(String nom) { this.nom = nom; }







	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> role) {
		this.roles = role;
	}


	// Explicit setter for isApproved field
	public void setIsApproved(boolean b) {
		this.isApproved = b;
	}
	public boolean getisApproved() {
		return isApproved;
	}

	public void setApproved(boolean approved) {
		this.isApproved = approved;
	}

}
