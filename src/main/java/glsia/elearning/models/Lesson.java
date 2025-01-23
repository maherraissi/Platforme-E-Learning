package glsia.elearning.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int id;

    public String name;
    public String description;
    public String url;
    public int duration;
    public LocalDateTime startAt;
    public LocalDateTime endAt;

    @ManyToOne
    @JoinColumn(name = "id_course",nullable = false)
    private Cours cours;
}
