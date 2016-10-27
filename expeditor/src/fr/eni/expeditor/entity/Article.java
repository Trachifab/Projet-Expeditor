package fr.eni.expeditor.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries(
        {
                @NamedQuery(name = "rechercherArticleParIdentifiantExterne", query = "select a from Article a where a.idExterne= :idExterne")
        }
)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String idExterne;

    private String libelle;

    @Lob
    private String description;

    private Integer poids;

    private Date dateArchive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdExterne() {
        return idExterne;
    }

    public void setIdExterne(String idExterne) {
        this.idExterne = idExterne;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPoids() {
        return poids;
    }

    public void setPoids(Integer poid) {
        this.poids = poid;
    }

    public Date getDateArchive() {
        return dateArchive;
    }

    public void setDateArchive(Date dateArchive) {
        this.dateArchive = dateArchive;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", idExterne='" + idExterne + '\'' +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                ", poids=" + poids +
                ", dateArchive=" + dateArchive +
                '}';
    }
}
