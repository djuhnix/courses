package courses.server.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@IdClass(GraduationPK.class)
public class Graduation extends Aggregator{

    @ManyToOne
    private Student student;

    @ManyToOne
    private Activity activity;

    @Basic @Column(name = "grade", nullable = false)
    private int grade;

    @Id @Column(name = "ID_STUDENT", nullable = false)
    private int idStudent;

    @Id @Column(name = "ID_ACTIVITY", nullable = false)
    private int idActivity;

    public Graduation(Student student, Activity activity, int grade) {
        this.student = student;
        this.activity = activity;
        this.grade = grade;
    }

    public Graduation() {
    }
}
