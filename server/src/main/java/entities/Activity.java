package entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Activity {
    @Id @GeneratedValue
    private Integer id;
    private String name;

    @Basic @Column(name = "START_DATE", nullable = false)
    private Date start;

    @Basic @Column(name = "END_DATE", nullable = false)
    private Date end;

    @Basic @Column(name = "SUBJECT", nullable = false)
    private String subject;

    @ManyToOne
    private Promotion promo;

    @ManyToOne
    private Teacher teacher;
    @OneToMany(mappedBy = "activity")
    private List<Graduation> graduations;

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
        this.promo = promo;
        this.teacher = teacher;
    }

    public Activity() {
    }
}
