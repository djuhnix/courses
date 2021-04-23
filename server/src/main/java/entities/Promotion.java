package entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Promotion {
    private String formation;
    private int grade;
    private int id;

    public Promotion(String formation, int grade) {
        this.formation = formation;
        this.grade = grade;
    }

    public _Dummy_ setFormation(int formation) {
        this.formation = formation;
        return this;
    }

    @Basic
    @Column(name = "FORMATION", nullable = false)
    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    @Basic
    @Column(name = "GRADE", nullable = false)
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
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

        Promotion promotion = (Promotion) o;

        if (grade != promotion.grade) return false;
        if (id != promotion.id) return false;
        if (formation != null ? !formation.equals(promotion.formation) : promotion.formation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (formation != null ? formation.hashCode() : 0);
        result = 31 * result + grade;
        return result;
    }
}
