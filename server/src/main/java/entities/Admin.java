package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Admin extends User{
    private int id;

    public Admin(String nom, String prenom, String telephone, String email, String adresse1, String adresse2, String ville, String cp) {
        super(nom, prenom, telephone, email, adresse1, adresse2, ville, cp);
    }

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public _Dummy_ setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (id != admin.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
