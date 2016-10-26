package fr.eni.expeditor.service;

import fr.eni.expeditor.entity.Commande;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

/**
 * Service sous forme d'EJB permattant de gérer les entités Commandes
 */
@Stateless
public class GestionCommandeBean extends AbstractService {
    private static Logger LOGGER = Logger.getLogger(GestionCommandeBean.class.getName());

    /**
     * Ajoute une nouvelle commande
     *
     * @param commande
     */
    public void ajouter(Commande commande) {

        getEntityManager().merge(commande);

    }

    /**
     * Renvoie toutes les Commandes
     *
     * @return la liste de toutes les Commandes
     */
    public List<Commande> rechercherToutes() {

        return (List<Commande>) consulter(Commande.class);
    }

    /**
     * Renvoie la liste des commandes en attente ou en cours de traitement
     * @return La liste des commandes avec l'état ATTE ou ENCO
     */
    public List<Commande> listerCommandesManager() {
        Query q = getEntityManager().createNamedQuery("listerCommandesManager");
        List<String> etats = Arrays.asList("ATTE", "ENCO");
        q.setParameter("etats", etats);
        try {
            return (List<Commande>) q.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

    /**
     * Renvoie une commande
     *
     * @param numero
     * @return
     */
    public Commande rechercherParNumero(Integer numero) {

        return getEntityManager().find(Commande.class, numero);
    }

    /**
     * Supprime une commande par son numero
     *
     * @param numero
     */
    public void supprimerParNumero(Integer numero) {

        getEntityManager().remove(numero);
    }

}
