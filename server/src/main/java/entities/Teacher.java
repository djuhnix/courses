package entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Teacher extends User{
    private String NUMEN;
    private int id;
    private String numen;

    public Teacher(String nom, String prenom, String telephone, String email, String adresse1, String adresse2, String ville, String cp, String NUMEN) {
        super(nom, prenom, telephone, email, adresse1, adresse2, ville, cp);
        this.NUMEN = NUMEN;
    }

    public String getNUMEN() {
        return NUMEN;
    }

    public void setNUMEN(String NUMEN) {
        this.NUMEN = NUMEN;
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

    @Basic
    @Column(name = "NUMEN", nullable = false, length = 26)
    public String getNumen() {
        return numen;
    }

    public _Dummy_ setNumen(String numen) {
        this.numen = numen;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (id != teacher.id) return false;
        if (numen != null ? !numen.equals(teacher.numen) : teacher.numen != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (numen != null ? numen.hashCode() : 0);
        return result;
    }
}
