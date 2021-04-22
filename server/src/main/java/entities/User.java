package entities;

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

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

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

    public String getTelephone() {
        return telephone;
    }

    public User setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getAdresse1() {
        return adresse1;
    }

    public User setAdresse1(String adresse1) {
        this.adresse1 = adresse1;
        return this;
    }

    public String getAdresse2() {
        return adresse2;
    }

    public User setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
        return this;
    }

    public String getVille() {
        return ville;
    }

    public User setVille(String ville) {
        this.ville = ville;
        return this;
    }

    public String getCp() {
        return cp;
    }

    public User setCp(String cp) {
        this.cp = cp;
        return this;
    }
}
