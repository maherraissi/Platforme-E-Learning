package glsia.elearning.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Utilisation de la stratégie d'auto-génération des identifiants
    private int id;

    private String name;  // Utilisation de l'encapsulation des attributs avec private

    // Liaisons avec les entités Examen, Lesson, Inscription
    @OneToMany(mappedBy = "cours", cascade = CascadeType.ALL)
    private List<Examen> examen;

    @OneToMany(mappedBy = "cours", cascade = CascadeType.ALL)
    private List<Lesson> lessons;


  
}

    

