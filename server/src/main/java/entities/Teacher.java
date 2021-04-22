package entities;

public class Teacher extends User{
    private String NUMEN;

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
}
