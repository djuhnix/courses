package entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import javax.persistence.Entity;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String passwordHash;
    private String firstname;
    private String lastname;
    private String telephone;
    private String adresse1;
    private String adresse2;
    private String ville;
    private String cp;
    private String nom;
    private String prenom;

    public User() {
    }

    public User(String firstname, String lastname, String telephone, String email, String adresse1, String adresse2, String ville, String cp) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephone = telephone;
        this.email = email;
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.ville = ville;
        this.cp = cp;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    @Basic
    @Column(name = "EMAIL", nullable = false, length = 50)
    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public User setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public User setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public User setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    @Basic
    @Column(name = "TELEPHONE", nullable = false, length = 25)
    public String getTelephone() {
        return telephone;
    }

    public User setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    @Basic
    @Column(name = "ADRESSE1", nullable = false, length = 25)
    public String getAdresse1() {
        return adresse1;
    }

    public User setAdresse1(String adresse1) {
        this.adresse1 = adresse1;
        return this;
    }

    @Basic
    @Column(name = "ADRESSE2", nullable = true, length = 25)
    public String getAdresse2() {
        return adresse2;
    }

    public User setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
        return this;
    }

    @Basic
    @Column(name = "VILLE", nullable = false, length = 25)
    public String getVille() {
        return ville;
    }

    public User setVille(String ville) {
        this.ville = ville;
        return this;
    }

    @Basic
    @Column(name = "CP", nullable = false, length = 25)
    public String getCp() {
        return cp;
    }

    public User setCp(String cp) {
        this.cp = cp;
        return this;
    }

    @Basic
    @Column(name = "NOM", nullable = false, length = 25)
    public String getNom() {
        return nom;
    }

    public User setNom(String nom) {
        this.nom = nom;
        return this;
    }

    @Basic
    @Column(name = "PRENOM", nullable = false, length = 25)
    public String getPrenom() {
        return prenom;
    }

    public User setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (telephone != null ? !telephone.equals(user.telephone) : user.telephone != null) return false;
        if (adresse1 != null ? !adresse1.equals(user.adresse1) : user.adresse1 != null) return false;
        if (adresse2 != null ? !adresse2.equals(user.adresse2) : user.adresse2 != null) return false;
        if (ville != null ? !ville.equals(user.ville) : user.ville != null) return false;
        if (cp != null ? !cp.equals(user.cp) : user.cp != null) return false;
        if (nom != null ? !nom.equals(user.nom) : user.nom != null) return false;
        if (prenom != null ? !prenom.equals(user.prenom) : user.prenom != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (adresse1 != null ? adresse1.hashCode() : 0);
        result = 31 * result + (adresse2 != null ? adresse2.hashCode() : 0);
        result = 31 * result + (ville != null ? ville.hashCode() : 0);
        result = 31 * result + (cp != null ? cp.hashCode() : 0);
        return result;
    }
}
