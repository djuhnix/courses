package entities;

public class Promotion {
    private String formation;
    private int grade;

    public Promotion(String formation, int grade) {
        this.formation = formation;
        this.grade = grade;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
