package fr.eni.expeditor.service;

import fr.eni.expeditor.entity.LigneCommande;

import javax.ejb.Stateless;

/**
 * Created by d1502doreyf on 25/10/2016.
 */
@Stateless
public class GestionLigneCommandeBean extends AbstractService {

    /**
     * Ajoute une nouvelle ligne commande
     *
     * @param ligneCommande
     */
    public void ajouter(LigneCommande ligneCommande) {

        getEntityManager().merge(ligneCommande);

    }

}
