package fr.eni.expeditor.service;

import fr.eni.expeditor.entity.Collaborateur;
import fr.eni.expeditor.entity.Commande;
import fr.eni.expeditor.entity.Etat;

import javax.management.relation.Role;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

/**
 * Cet EJB gére les traitments annexes
 */
public class GestionMetierBean extends AbstractService {

    /**
     * Récupére le role d'un collaborateur
     * @param user
     * @return role
     */
    public Role retournerRole(Collaborateur user) {

        //return getEntityManager().find(Collaborateur.class, user).getRole();
        return null; //lol
    }

    /**
     * Récupére l'etat d'une commande
     * @param commande
     * @return etat
     */
    public Etat retournerEtat(Commande commande) {

        return getEntityManager().find(Commande.class, commande).getEtat();
    }

}
