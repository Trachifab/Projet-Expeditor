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
        if (client.getId() == null) {
            client.setIdExterne(client.getRaisonSociale());

            getEntityManager().persist(client);
        } else {
            client = getEntityManager().merge(client);
        }
    }

    public Client rechercherParIdentifiantExterne(Client client) {
        Query q = getEntityManager().createNamedQuery("rechercherClientParIdentifiantExterne");
        q.setParameter("idExterne", client.getIdExterne());
        try {
            return (Client) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }

    }

}
