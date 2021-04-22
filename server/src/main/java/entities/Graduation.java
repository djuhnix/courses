package entities;

public class Graduation {
    private Student student;
    private Activity activity;
    private int grade;

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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
