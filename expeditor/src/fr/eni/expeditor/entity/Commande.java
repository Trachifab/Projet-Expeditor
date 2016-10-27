package fr.eni.expeditor.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "COMMANDE.RECUPERER.DERNIERE.COMMANDE.EN.ATTENTE", query = "select c from Commande c where c.etat.code = 'ATTE' order by c.dateCommande"),
        @NamedQuery(name = "COMMANDE.CHANGER.ETAT", query = "update Commande c set c.etat = :etat where c.id = :id"),
        @NamedQuery(name = "COMMANDE.ASSIGNER.COLLABORATEUR", query = "update Commande c set c.collaborateur = :collaborateur where c.id = :id"),
        @NamedQuery(name = "COMMANDE.RECUPERER.COMMANDE.EN.COURS.POUR.EMPLOYE", query = "select c from Commande c where c.collaborateur.id = :col_id and c.etat.code = :codeRole"),
        @NamedQuery(name="listerCommandesManager", query="Select c from Commande c where c.etat.code IN :etats order by c.numero"),
        @NamedQuery(name = "COMMANDE.RECUPERER.STATISTIQUES.DU.JOUR", query="Select c from Commande c where c.dateTraitement > :dateDuJour"),
        @NamedQuery(name = "COMMANDE.LIBERER.COMMANDE", query = "update Commande c set c.etat.code = 'ATTE', c.collaborateur = :collabo where c.id = :id")
})
public class Commande {

    @Id
    private Integer numero;

    private Date dateCommande;

    private Date dateTraitement;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "commande",cascade=CascadeType.ALL)
    private List<LigneCommande> lignesCommande;

    @ManyToOne
    private Collaborateur collaborateur;

    @ManyToOne
    private Etat etat;

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Collaborateur getCollaborateur() {
        return collaborateur;
    }

    public void setCollaborateur(Collaborateur collaborateur) {
        this.collaborateur = collaborateur;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Date getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(Date dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public List<LigneCommande> getLignesCommande() {
        return lignesCommande;
    }

    public void setLignesCommande(List<LigneCommande> lignesCommande) {
        this.lignesCommande = lignesCommande;
    }

    @Override
    public String toString() {
        return "Commande n°" + numero +
                ", effectuée le " + dateCommande +
                ". Etat : " + etat;
    }
}
