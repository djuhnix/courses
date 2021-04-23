package entities;

import jakarta.persistence.*;

@Entity
@IdClass(GraduationPK.class)
public class Graduation {
    private Student student;
    private Activity activity;
    private int grade;
    private int idStudent;
    private int idActivity;

    public Graduation(Student student, Activity activity, int grade) {
        this.student = student;
        this.activity = activity;
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Basic
    @Column(name = "grade", nullable = false)
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Id
    @Column(name = "ID_STUDENT", nullable = false)
    public int getIdStudent() {
        return idStudent;
    }

    public Graduation setIdStudent(int idStudent) {
        this.idStudent = idStudent;
        return this;
    }

    @Id
    @Column(name = "ID_ACTIVITY", nullable = false)
    public int getIdActivity() {
        return idActivity;
    }

    public Graduation setIdActivity(int idActivity) {
        this.idActivity = idActivity;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Graduation that = (Graduation) o;

        if (grade != that.grade) return false;
        if (idStudent != that.idStudent) return false;
        if (idActivity != that.idActivity) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idStudent;
        result = 31 * result + idActivity;
        result = 31 * result + grade;
        return result;
    }
}
