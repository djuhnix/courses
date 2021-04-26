package courses.server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Promotion {
    @Id @GeneratedValue @Column(name = "ID", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "promotion")
    private List<Activity> activities;

    @OneToMany(mappedBy = "promotion")
    private List<Student> students;

    @Basic @Column(name = "FORMATION", nullable = false)
    private String formation;

    @Basic @Column(name = "GRADE", nullable = false)
    private int grade;

    public Promotion(String formation, int grade) {
        this.formation = formation;
        this.grade = grade;
    }

    public Promotion() {
    }
}
