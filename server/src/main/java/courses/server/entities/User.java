package courses.server.entities;

import courses.server.security.RolesEnum;
import jakarta.persistence.*;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.STRING, length=20)
@DiscriminatorValue("user")
@Data
public class User extends Aggregator{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id = 0;

    @Basic @Column(name = "EMAIL", nullable = false, length = 50)
    private String email = "";

    private String passwordHash = "";

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private RolesEnum role = RolesEnum.USER;

    @Basic @Column(name = "SALT", nullable = false)
    private String salt = "";

    @Basic @Column(name = "TELEPHONE", length = 25)
    private String telephone = "";

    @Basic @Column(name = "ADRESSE1", length = 25)
    private String adresse1 = "";

    @Basic @Column(name = "ADRESSE2", length = 25)
    private String adresse2 = "";

    @Basic @Column(name = "VILLE", length = 25)
    private String ville = "";

    @Basic @Column(name = "CP", length = 25)
    private String cp = "";

    @Basic @Column(name = "NOM", nullable = false, length = 25)
    private String nom = "";

    @Basic @Column(name = "PRENOM", nullable = false, length = 25)
    private String prenom = "";

    public User() {
    }

    public User(String prenom, String nom, String telephone, String email, String adresse1, String adresse2, String ville, String cp) {
        this.prenom = prenom;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.ville = ville;
        this.cp = cp;
    }
}
