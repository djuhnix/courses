package courses.server.entities;

import courses.server.security.RolesEnum;
import jakarta.persistence.*;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.STRING,length=20)
@DiscriminatorValue("user")
@Data
public class User {
    @Id @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Basic @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;

    private String passwordHash;

    @Basic @Column(name = "ROLES", nullable = false)
    private RolesEnum[] roles;

    @Basic @Column(name = "TELEPHONE", nullable = false, length = 25)
    private String telephone;

    @Basic @Column(name = "ADRESSE1", nullable = false, length = 25)
    private String adresse1;

    @Basic @Column(name = "ADRESSE2", nullable = true, length = 25)
    private String adresse2;

    @Basic @Column(name = "VILLE", nullable = false, length = 25)
    private String ville;

    @Basic @Column(name = "CP", nullable = false, length = 25)
    private String cp;

    @Basic @Column(name = "NOM", nullable = false, length = 25)
    private String nom;

    @Basic @Column(name = "PRENOM", nullable = false, length = 25)
    private String prenom;

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
