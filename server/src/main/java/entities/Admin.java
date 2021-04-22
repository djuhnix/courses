package entities;

public class Admin extends User{
    public Admin(String nom, String prenom, String telephone, String email, String adresse1, String adresse2, String ville, String cp) {
        super(nom, prenom, telephone, email, adresse1, adresse2, ville, cp);
    }
}
