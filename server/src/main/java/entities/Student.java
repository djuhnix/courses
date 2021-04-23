package entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student extends User{
    private int INE;
    private Promotion promo;
    private int id;
    private int ine;
    private int idPromotion;

    public Student(String nom, String prenom, String telephone, String email, String adresse1, String adresse2, String ville, String cp, int INE, Promotion promo) {
        super(nom, prenom, telephone, email, adresse1, adresse2, ville, cp);
        this.INE = INE;
        this.promo = promo;
    }

    public int getINE() {
        return INE;
    }

    public void setINE(int INE) {
        this.INE = INE;
    }

    public Promotion getPromo() {
        return promo;
    }

    public void setPromo(Promotion promo) {
        this.promo = promo;
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
    @Column(name = "INE", nullable = false)
    public int getIne() {
        return ine;
    }

    public _Dummy_ setIne(int ine) {
        this.ine = ine;
        return this;
    }

    @Basic
    @Column(name = "ID_PROMOTION", nullable = false)
    public int getIdPromotion() {
        return idPromotion;
    }

    public _Dummy_ setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != student.id) return false;
        if (ine != student.ine) return false;
        if (idPromotion != student.idPromotion) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + ine;
        result = 31 * result + idPromotion;
        return result;
    }
}
