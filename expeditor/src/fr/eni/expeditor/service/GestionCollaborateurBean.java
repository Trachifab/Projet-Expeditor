package fr.eni.expeditor.service;

import javax.ejb.Stateless;

import fr.eni.expeditor.entity.Collaborateur;

import java.util.List;

/**
 * Service sous forme d'EJB permattant de gérer les entités Collaborateurs
 */
@Stateless
public class GestionCollaborateurBean extends AbstractService {

    /**
     * Ajoute un nouvel utilisateur
     * @param user
     */
    public void ajouter(Collaborateur user) {

        getEntityManager().merge(user);

    }

    /**
     * Renvoie tous les collaborateurs
     * @return la liste de tous les collaborateurs
     */
    public List<Collaborateur> rechercherTous(){

        return (List<Collaborateur>) consulter(Collaborateur.class);
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
     * @param login
     * @return
     */
    public Collaborateur rechercherParLogin(String login){

        return getEntityManager().find(Collaborateur.class, login);
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
}
