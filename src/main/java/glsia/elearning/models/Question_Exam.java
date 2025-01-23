package glsia.elearning.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Question_Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int id;
    public String question;

    @ManyToOne
    @JoinColumn(name = "id_exam",nullable = false)
    private Examen examen;
}
