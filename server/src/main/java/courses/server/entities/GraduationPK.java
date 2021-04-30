package courses.server.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;

public class GraduationPK implements Serializable {
    private int idStudent;
    private int idActivity;

    @Column(name = "ID_STUDENT", nullable = false)
    @Id
    public int getIdStudent() {
        return idStudent;
    }

    public GraduationPK setIdStudent(int idStudent) {
        this.idStudent = idStudent;
        return this;
    }

    @Column(name = "ID_ACTIVITY", nullable = false)
    @Id
    public int getIdActivity() {
        return idActivity;
    }

    public GraduationPK setIdActivity(int idActivity) {
        this.idActivity = idActivity;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GraduationPK that = (GraduationPK) o;

        if (idStudent != that.idStudent) return false;
        if (idActivity != that.idActivity) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idStudent;
        result = 31 * result + idActivity;
        return result;
    }
}
