package fr.eni.expeditor.entity;

import javax.persistence.*;

@Entity
@NamedQueries(
        {
                @NamedQuery(name = "CollaborateurLoginExists", query = "Select count(collabo) from Collaborateur collabo where collabo.email = :email"),
                @NamedQuery(name = "SelectCollaborateurByLoginMotDePasse", query = "Select collabo from Collaborateur collabo where collabo.email = :email and collabo.motDePasse = :motDePasse")
        }
)
public class Collaborateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    private String prenom;

    @Column(unique = true)
    private String email;

    private String motDePasse;

    @ManyToOne
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public String toString() {
        return prenom + " " + nom;
    }

}
