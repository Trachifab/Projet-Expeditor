package fr.eni.expeditor.service;

import fr.eni.expeditor.entity.Collaborateur;
import fr.eni.expeditor.entity.Commande;
import fr.eni.expeditor.entity.Etat;

import javax.ejb.Stateless;
import javax.management.relation.Role;

/**
 * Cet EJB gére les traitments annexes
 */
@Stateless
public class GestionMetierBean extends AbstractService {

    /**
     * Récupére le role d'un collaborateur
     * @param user
     * @return role
     */
    public Role retournerRole(Collaborateur user) {

        //return getEntityManager().rechercherParIdentifiant(Collaborateur.class, user).getRole();
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

    public Etat rechercherParIdentifiant(String code)
    {

        return getEntityManager().find(Etat.class, code);

    }

}
