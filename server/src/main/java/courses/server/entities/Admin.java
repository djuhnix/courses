package courses.server.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("admin")
@Data
public class Admin extends User{

    @Id @Column(name = "ID", nullable = false)
    private int id;

    public Admin(String nom, String prenom, String telephone, String email, String adresse1, String adresse2, String ville, String cp) {
        super(nom, prenom, telephone, email, adresse1, adresse2, ville, cp);
    }

    public Admin() {

    }
}
