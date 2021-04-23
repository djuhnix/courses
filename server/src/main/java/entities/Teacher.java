package entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("teacher")
@Data
public class Teacher extends User {
    @Id @Column(name = "ID", nullable = false)
    private int id;

    @Basic @Column(name = "NUMEN", nullable = false, length = 26)
    private String numen;

    @OneToMany(mappedBy = "teacher")
    private List<Activity> activities;

    public Teacher(String nom, String prenom, String telephone, String email, String adresse1, String adresse2, String ville, String cp, String numen) {
        super(nom, prenom, telephone, email, adresse1, adresse2, ville, cp);
        this.numen = numen;
    }

    public Teacher() {
    }
}
