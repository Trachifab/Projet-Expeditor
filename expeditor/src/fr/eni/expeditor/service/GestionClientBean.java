package fr.eni.expeditor.service;

import fr.eni.expeditor.entity.Client;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by d1502doreyf on 25/10/2016.
 */
@Stateless
public class GestionClientBean extends AbstractService {

    public Client rechercherParIdentifiant(Integer identite) {
        return getEntityManager().find(Client.class, identite);
    }

    public void enregistrerClient(Client client) {
        getEntityManager().merge(client);
    }

    public Client rechercherParIdExterneSinonCreation(Client client) {
        Query q = getEntityManager().createNamedQuery("rechercherParIdExterneSinonCreation");
        q.setParameter("idExterne", client.getIdExterne());
        try {
            client = (Client) q.getSingleResult();
        } catch (NoResultException nre) {
            enregistrerClient(client);
        }
        return client;
    }

}
