package courses.server.entities;

import courses.utils.JsonUtils;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("student")
public class Student extends User {
    @Basic @Column(name = "INE", nullable = false)
    private int ine;

    @Basic @Column(name = "ID_PROMOTION", nullable = false)
    private int idPromotion = 0;

    @ManyToOne
    private Promotion promotion;

    public Student(String nom, String prenom, String telephone, String email, String adresse1, String adresse2, String ville, String cp, int ine, Promotion promotion) {
        super(nom, prenom, telephone, email, adresse1, adresse2, ville, cp);
        this.ine = ine;
        this.promotion = promotion;
    }

    public static Student getFrom(Object o) {
        return JsonUtils.mapObject(o, Student.class);
    }

    public Student() {
    }
}
