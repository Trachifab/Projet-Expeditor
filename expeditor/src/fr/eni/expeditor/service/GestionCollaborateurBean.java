package fr.eni.expeditor.service;

import fr.eni.expeditor.entity.Collaborateur;
import fr.eni.expeditor.exception.ConnexionException;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Service sous forme d'EJB permattant de gérer les entités Collaborateurs
 */
@Stateless
public class GestionCollaborateurBean extends AbstractService {
    private static Logger LOGGER = Logger.getLogger(GestionCollaborateurBean.class.getName());

    /**
     * Ajoute ou modifie un utilisateur
     * @param user
     */
    public void enregistrerCollaborateur(Collaborateur user) {
        if (user.getId() == null) {
            getEntityManager().persist(user);
        } else {
            user = getEntityManager().merge(user);
        }
    }

    /**
     * Renvoie tous les collaborateurs
     * @return la liste de tous les collaborateurs
     */
    public List<Collaborateur> rechercherTous(){

        Query query = getEntityManager().createQuery("SELECT c FROM Collaborateur c WHERE c.dateArchive is null");
        return query.getResultList();
    }

    /**
     * Renvoie un collaborateur
     * @param identite
     * @return
     */
    public Collaborateur rechercherParIdentifiant(Integer identite){

        return getEntityManager().find(Collaborateur.class, identite);
    }

    /**
     * Rnvoie un collaborateur par son login
     * @param email
     * @return
     */
    public Collaborateur rechercherParLogin(String email){
        return null;
    }

    /**
     * Supprime un collaborateur par son identifiant
     * @param identifiant
     */
    public void supprimerParIdentifiant(Integer identifiant){

        getEntityManager().remove(identifiant);
    }

    /**
     * Supprime un collaborateur par son login
     * @param login
     */
    public void supprimerParLogin(String login) {

        getEntityManager().remove(login);
    }

    /**
     *
     * @param email
     * @return
     */
    public Boolean collaborateurExiste(String email) {
        LOGGER.info("Recherche du collaborateur dont l'identifiant est " + email);
        Query q = getEntityManager().createNamedQuery("CollaborateurLoginExists");
        q.setParameter("email", email);
        // On fait != 0 pour "caster" le résultat de type "Long" en "Boolean"
        return ((Long)q.getSingleResult()) != 0;
    }

    /**
     *
     * @param email
     * @param motDePasse
     * @return
     * @throws ConnexionException
     */
    public Collaborateur rechercherParLoginMotDePasse(String email, String motDePasse) throws ConnexionException {
        LOGGER.info(String.format("Tentative de connexion de l'utilisateur '%s'", email));
        Query q = getEntityManager().createNamedQuery("SelectCollaborateurByLoginMotDePasse");
        q.setParameter("email", email);
        q.setParameter("motDePasse", motDePasse);
        try{
            return (Collaborateur)q.getSingleResult();
        }
        catch (NoResultException nre){
            throw new ConnexionException();
        }
    }
/*
    public String recupererStatistiquesCollaboratreur(){



    }
    */
}
