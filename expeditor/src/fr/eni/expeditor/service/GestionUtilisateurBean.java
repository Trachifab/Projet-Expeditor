package fr.eni.expeditor.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.eni.expeditor.entity.Utilisateur;

@Stateless
public class GestionUtilisateurBean extends AbstractService {

	public void ajouter(Utilisateur user) {

		getEntityManager().merge(user);

	}

	public List<Utilisateur> getAll() {
		Query query = getEntityManager().createQuery("SELECT u FROM Utilisateur u");

		return query.getResultList();

	}

}
