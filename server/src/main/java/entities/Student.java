package entities;

public class Student extends User{
    private int INE;
    private Promotion promo;

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


}
