package courses.server.entities;

import courses.server.security.RolesEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.STRING, length=20)
@DiscriminatorValue("user")
@Data
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Basic @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;

    private String password;

    @Basic @Column(name = "PASSWORD_HASH", nullable = false)
    private String passwordHash;

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private RolesEnum role = RolesEnum.USER;

    @Basic @Column(name = "SALT", nullable = false)
    private String salt;

    @Basic @Column(name = "VALIDATED", nullable = false)
    private boolean validated = false;

    @Basic @Column(name = "TELEPHONE", length = 25)
    private String telephone = null;

    @Basic @Column(name = "ADRESSE1", length = 25)
    private String adresse1 = null;

    @Basic @Column(name = "ADRESSE2", length = 25)
    private String adresse2 = null;

    @Basic @Column(name = "VILLE", length = 25)
    private String ville = null;

    @Basic @Column(name = "CP", length = 25)
    private String cp = null;

    @Basic @Column(name = "NOM", nullable = false, length = 25)
    private String nom = null;

    @Basic @Column(name = "PRENOM", nullable = false, length = 25)
    private String prenom = null;

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
