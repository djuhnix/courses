package courses.server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Activity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Basic @Column(name = "START_DATE", nullable = false)
    private Date start;

    @Basic @Column(name = "END_DATE", nullable = false)
    private Date end;

    @Basic @Column(name = "SUBJECT", nullable = false)
    private String subject;

    @ManyToOne
    private Promotion promotion;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy = "activity", cascade=CascadeType.PERSIST)
    private List<Graduation> graduations;

    @OneToMany(mappedBy = "activity", cascade=CascadeType.PERSIST)
    private List<Exercise> exercises;

    @Basic @Column(name = "ID_PROMOTION", nullable = false)
    private int idPromotion;

    @Basic
    @Column(name = "ID_TEACHER", nullable = false)
    private int idTeacher;

    public Activity(String name, Date start, Date end, String subject, Promotion promo, Teacher teacher) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.subject = subject;
        this.promotion = promo;
        this.teacher = teacher;
    }

    public Activity() {
    }
}
